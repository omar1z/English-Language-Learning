
package FlyWeight;

import java.sql.Statement;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import Container.MyHashMap;
import DataClass.Lesson;
import Observer.ExerciseObserver;
import VariablePool.ColorPool;
import DataClass.*;

public class Advanced {
    private static HashMap<String, ArrayList<Exercise>> exerciseList = new HashMap<>();
    private static ArrayList<Lesson> LessonList = null;
    private static HashMap<String,ExerciseObserver> observerSet  = new HashMap<>();
    private Advanced() {
    }

    public static synchronized ArrayList<Lesson> getLessons(User user) {
        if (LessonList == null) {
            LessonList = new ArrayList<>();
            Statement s = DataBaseConnection.getStatment();
            try {
                ResultSet result = s.executeQuery(
                        "select lesson_id,title,header_image from Lessons where level_id = 3 order by lesson_id");
                while (result.next()) {

                    int lessonId = result.getInt("lesson_id");
                    String title = result.getString("title");
                    String headerImage = result.getString("header_image");

                    ResultSet ruleSet = DataBaseConnection.getStatment()
                            .executeQuery("select * from Rules where lesson_id =" + lessonId + " ;");
                    ruleSet.next();
                    Rule rule = new Rule(ruleSet.getInt("rule_id"), ruleSet.getString("instruction"));
                    ResultSet paragraphs = DataBaseConnection.getStatment()
                            .executeQuery("select title, content from paragraphs where rule_id = "
                                    + ruleSet.getInt("rule_id") + " order by paragraph");
                    MyHashMap<String, String> hash = new MyHashMap<>();
                    while (paragraphs.next()) {
                        hash.put(paragraphs.getString("title"), paragraphs.getString("content"));
                    }
                    ResultSet finished = DataBaseConnection.getStatment()
                            .executeQuery("select * from finishedLessons where user_id = " + user.getId()
                                    + " and lesson_id = " + lessonId + " ;");
                    rule.setParagraphs(hash);
                    boolean finish = finished.next();
                    Lesson lesson = new Lesson(lessonId, title, headerImage, rule, finish,
                    		(finish)?ColorPool.getColor("dAdv"):ColorPool.getColor("lAdv"));
                    if(! finish) observerSet.put(title, new ExerciseObserver(lesson,user));
                    LessonList.add(lesson);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return LessonList;
    }

    public static synchronized ArrayList<Exercise> getExercises(String lessonTitle, User user) {
    	
    	if (exerciseList.get(lessonTitle) == null) {
            Statement s = DataBaseConnection.getStatment();
            
            try {
            	
                ArrayList<Exercise> exercisesArrayList = new ArrayList<>();
                ResultSet resultSet = s.executeQuery(
                		"SELECT * FROM Exercises INNER JOIN Lessons ON Exercises.lesson_id = Lessons.lesson_id WHERE Lessons.level_id = 3"
                                + " and Lessons.title ='"+ lessonTitle +"' order by exercise_id;");
                while (resultSet.next()) {
                	
                    int exercise_id = resultSet.getInt("exercise_id");
                    String title = resultSet.getString("title");
                    String instruction = resultSet.getString("instruction");
                    String type = resultSet.getString("type");
                    String option = resultSet.getString("options");
                    ArrayList<String> lines = new ArrayList<>();
                    ResultSet finished = DataBaseConnection.getStatment()
                            .executeQuery("select * from finishedExercises where user_id =" + user.getId()
                                    + " and exercise_id = " + exercise_id + " ;");
                    boolean finish = finished.next();
                    ColorPool c = new ColorPool();
                    Color backgroundColor;
                    if (finish) {
                        backgroundColor = c.getColor("dAdv");
                    } else
                        backgroundColor = c.getColor("lAdv");

                    if (type.equals("Gap")) {
                        ResultSet lineSet = DataBaseConnection.getStatment().executeQuery(
                                "select * from line where exercise_id = " + exercise_id + " order by line_number ;");
                        while (lineSet.next()) {
                            String lineContent = lineSet.getString("line_content");
                            lines.add(lineContent);
                        }
                        Exercise exercise = new Exercise(exercise_id, title, instruction, type, finish,
                                backgroundColor, lines.toArray(new String[0]),observerSet.get(lessonTitle));
                        exercisesArrayList
                                .add(exercise);
                        if(finish) {
                        	ExerciseObserver observer = observerSet.get(lessonTitle);
                        	if(observer != null) 
                        		observer.notifyObserver(exercise_id);
                        }
                    } else if (type.equals("List")) {
                        ArrayList<String> options = new ArrayList<>();
                        ResultSet lineSet = DataBaseConnection.getStatment().executeQuery(
                                "select * from line where exercise_id = " + exercise_id + " order by line_number ;");
                        while (lineSet.next()) {
                            String lineType = lineSet.getString("line_type");
                            String lineOptions = lineSet.getString("line_options");
                            String lineContent = lineSet.getString("line_content");
                            if (lineType!=null && lineType.equals("List")) {
                                options.add(lineOptions);
                            }
                            lines.add(lineContent);
                        }
                        Exercise exercise =  new OptionList(exercise_id, title, instruction, type, finish, backgroundColor,
                                lines.toArray(new String[0]), options.toArray(new String[0]),observerSet.get(lessonTitle));
                        exercisesArrayList.add(exercise
                               );
                        if(finish) {
                        	ExerciseObserver observer = observerSet.get(lessonTitle);
                        	if(observer != null)
                        		observer.notifyObserver(exercise_id);
                        }
                    } else if (type.equals("FinalTest")) {
                        int i = 0;
                        HashMap<Integer, String> optionHashMap = new HashMap<>();
                        ResultSet lineSet = DataBaseConnection.getStatment().executeQuery(
                                "select * from line where exercise_id = " + exercise_id + " order by line_number ;");
                        while (lineSet.next()) {
                            String lineType = lineSet.getString("line_type");
                            String lineOptions = lineSet.getString("line_options");
                            String lineContent = lineSet.getString("line_content");
                            if (lineType != null && lineType.equals("List")) {
                                optionHashMap.put(i, lineOptions);
                            }
                            lines.add(lineContent);
                            i++;
                        }
                        Exercise exercise = new FinalTest(exercise_id, title, instruction, type, finish, backgroundColor,
                                lines.toArray(new String[0]),optionHashMap,observerSet.get(lessonTitle));
                        exercisesArrayList.add(exercise);
                        if(finish) {
                        	ExerciseObserver observer = observerSet.get(lessonTitle);
                        	if(observer != null)
                        		observer.notifyObserver(exercise_id);
                        }
                    } else {
                        ResultSet lineSet = DataBaseConnection.getStatment().executeQuery(
                                "select * from line where exercise_id = " + exercise_id + " order by line_number ;");
                        while (lineSet.next()) {
                            String lineContent = lineSet.getString("line_content");
                            lines.add(lineContent);
                        }
                        Exercise exercise = new OtherTypes(exercise_id, title, instruction, type, finish, backgroundColor,
                                lines.toArray(new String[0]), option,observerSet.get(lessonTitle));
                        exercisesArrayList.add(exercise
                                );
                        if(finish) {
                        	ExerciseObserver observer = observerSet.get(lessonTitle);
                        	if(observer != null)
                        		observer.notifyObserver(exercise_id);
                        }
                        
                    }
                    
                }
                
                exerciseList.put(lessonTitle, exercisesArrayList);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return exerciseList.get(lessonTitle);
    }

}