package DataClass;

import java.awt.Color;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import FlyWeight.Advanced;
import FlyWeight.Elementary;
import FlyWeight.Intermediate;
import Observer.DataSetChangedObserver;


public class Lesson implements DataBaseItem{
    private int lesson_id;
    private String title;
    private String headerImage;
    private Rule rule;
    private boolean finished;
    private Color backgroundColor;
    private DataSetChangedObserver componentObserver;
    public Lesson(int lesson_id, String title, String headerImage,Rule rule, boolean finished, Color background){
        this.lesson_id = lesson_id;
        this.title = title;
        this.headerImage = headerImage;
        this.finished = finished;
        this.rule = rule;
        this.backgroundColor = background;
    }

    public int getLessonId(){
        return lesson_id;
    }

    public String getTitle(){
        return title;
    }

    public String getHeaderImage(){
        return headerImage;
    }

    public Rule getRule(){
        return rule;
    }

    public boolean isFinished(){
        return finished;
    }

    public Color getBackgroundColor(){
        return backgroundColor;
    }

    public void setFinished(User user,Color backgroundColor){
    	if(!finished) {
    	this.backgroundColor = backgroundColor;
        try {
            int finishedLesson = DataBaseConnection.getStatment()
                    .executeUpdate("insert into FinishedLessons (lesson_id,user_id,mark) value(" + this.getLessonId() + ","
                            + user.getId()+","+ +this.getMark(user)+ ");");
            if(finishedLesson >0) {
            	finished = true;
            	componentObserver.notifyDataSetChanged(backgroundColor);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    	}
    }
    public float getMark(User user) {
    	
        Statement s = DataBaseConnection.getStatment();
        ResultSet levelSet;
        float mark = 0;
        ArrayList<Exercise> ex = null;
        try {
            levelSet = s.executeQuery(
                    "select Levels.level_id from Levels inner join Lessons on Levels.level_id = Lessons.level_id where lesson_id = "
                            + getLessonId() + " ;");

            levelSet.next();
            int test = levelSet.getInt("level_id");
         
            switch (test) {
                case 1: 
                        ex = Elementary.getExercises(title, user);
                        break;
                case 2: 
                   
                        ex = Intermediate.getExercises(title, user);
                        break;
                 
                case 3: 
                   
                        ex = Advanced.getExercises(title, user);
                        break;
                default:
                    break;
            }
           
            for (Exercise i : ex) {
                mark += i.getMark(user);
            }
            mark = mark / ex.size();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mark;
    }
    public void setComponentObserver(DataSetChangedObserver observer) {
		this.componentObserver = observer;
	}
}