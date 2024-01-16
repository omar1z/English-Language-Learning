package DataClass;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Observer.DataSetChangedObserver;
import Observer.ExerciseObserver;
// default class for Exercises that represent Fill the blank exercise
public class Exercise implements Mark,DataBaseItem {
    private final int exerciseId;
    private final String title;
    private final String instruction;
    private boolean finished;
    private Color backgroundColor;
    private final String[] lines;
    private final String type;
    ExerciseObserver observer;
    //observer to notify GUI Component when exercise is finished
    private DataSetChangedObserver componentObserver;
    public Exercise(int exerciseId, String title, String instruction,String type,boolean finished,Color background, String[] lines,
    		ExerciseObserver observer) {
        this.exerciseId = exerciseId;
        this.title = title;
        this.instruction = instruction;
        this.lines = lines;
        this.type = type;
        this.backgroundColor = background;
        this.finished = finished;
        this.observer = observer;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public String getTitle() {
        return title;
    }

    public String getInstruction() {
        return instruction;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public String[] getLines() {
        return lines;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(float mark,Lesson lesson, User user, Color backgroundColor) {
    	if(!finished) { 
    	this.backgroundColor = backgroundColor;
         Statement s = DataBaseConnection.getStatment();
         try {
            int finishedExercise= s.executeUpdate(
                     "Insert into finishedExercises(mark,user_id,exercise_id) values(" + mark + "," + user.getId() + ","
                            + this.getExerciseId() + ");");
            
            if (finishedExercise > 0) {
                finished = true;
                observer.notifyObserver(this.exerciseId);
                componentObserver.notifyDataSetChanged(backgroundColor);
             
            }
                 
         } catch (SQLException e) {
             e.printStackTrace();
         }
    	}
    }
    public String getType() {
    	return type;
    }

	@Override
	public float getMark(User user) {
        Statement s = DataBaseConnection.getStatment();

        float mark = 0;
        try {
            ResultSet markSet = s
                    .executeQuery("Select mark from FinishedExercises where user_id=" + user.getId() + " and exercise_id = "
                            + getExerciseId() + ";");
            markSet.next();
            mark = markSet.getFloat("mark");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mark;
    }
	
	public void setComponentObserver(DataSetChangedObserver observer) {
		this.componentObserver = observer;
	}
}
