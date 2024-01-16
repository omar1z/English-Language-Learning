package FlyWeight;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import Container.MyHashMap;
import DataClass.DataBaseConnection;
import DataClass.Exercise;
import DataClass.FinalTest;
import DataClass.Lesson;
import DataClass.OptionList;
import DataClass.OtherTypes;
import DataClass.Rule;
import DataClass.User;
import Observer.ExerciseObserver;
import VariablePool.ColorPool;

public class Intermediate {
private static ArrayList<Lesson> lessonList = null;
private static HashMap<String,ArrayList<Exercise>> exerciseList = new HashMap<>();
private static HashMap<String,ExerciseObserver> observerSet = new HashMap<>();

private Intermediate() {
	
}
public static synchronized ArrayList<Lesson> getLessons(User user){
	if(lessonList == null) {
		lessonList = new ArrayList<>();
		Statement s = DataBaseConnection.getStatment();
		try {
			ResultSet result = s.executeQuery("select lesson_id,title, header_image from Lessons "
					+"where level_id = 2 order by lesson_id;");
			while(result.next()) {
				int lessonId = result.getInt("lesson_id");
				String title = result.getString("title");
				String headerImage = result.getString("header_image");
				
				ResultSet ruleSet = DataBaseConnection.getStatment().executeQuery("select * from Rules where lesson_id = "+ lessonId +";");
				ruleSet.next();
				int ruleId = ruleSet.getInt("rule_id");
				String ruleInstruction =  ruleSet.getString("instruction");
				Rule rule = new Rule(ruleId,ruleInstruction);
				ResultSet paragraphs = DataBaseConnection.getStatment().executeQuery("Select title, content from paragraphs "
						+ "where rule_id = " + ruleId+ " order by paragraph;");
				MyHashMap<String,String> hash = new MyHashMap<>();
				while(paragraphs.next()) {
					hash.put(paragraphs.getString("title"),
							paragraphs.getString("content"));
				}
				ResultSet finished = DataBaseConnection.getStatment().executeQuery("Select * from finishedLessons where user_id = "
				+ user.getId() + " and lesson_id = "+ lessonId +";" );
				rule.setParagraphs(hash);
				boolean finish = finished.next();
				Lesson lesson = new Lesson(lessonId,title,headerImage,rule,finish,
						finish?ColorPool.getColor("dGreen"):ColorPool.getColor("lGreen"));
				if(! finish) observerSet.put(lesson.getTitle(), new ExerciseObserver(lesson,user));
				lessonList.add(lesson);
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	
	return lessonList;
}
public static synchronized ArrayList<Exercise> getExercises(String lessonTitle, User user){
	
	if(exerciseList.get(lessonTitle) == null) {
		Statement s = DataBaseConnection.getStatment();
		try {
			ResultSet result = s.executeQuery("Select * from Exercises inner join Lessons on "
					+ "Exercises.lesson_id = Lessons.lesson_id  where level_id = 2 and Lessons.title = '"+ lessonTitle+"' order by exercise_id;");
			ArrayList<Exercise> temp = new ArrayList<>();
			
			while(result.next()) {
				int exeId = result.getInt("exercise_id");
				String title = result.getString("title");
				String instruction = result.getString("instruction");
				String type = result.getString("type");
				ArrayList<String> lines = new ArrayList<>();
				Exercise ex = null;
				
				
				
				
				if(type.equals("List")) {
					ResultSet lineResult = DataBaseConnection.getStatment().executeQuery("select * from Line where exercise_id = "+ exeId+ " order by line_number ;");
					ArrayList<String> options = new ArrayList<>();
					
					while(lineResult.next()) {
						
						String line_Content = lineResult.getString("line_content");
						String lineType = lineResult.getString("line_type");
						if(lineType != null &&lineType != "" && lineType.equals("List")) {
							String lineOption = lineResult.getString("line_options");
							System.out.println(lineOption);
							options.add(lineOption);
						}
						
						lines.add(line_Content);
					}
				ResultSet finishedSet = DataBaseConnection.getStatment().executeQuery("select * from FinishedExercises where exercise_id = "+ exeId+
						" and user_id = "+ user.getId());
				boolean finished = finishedSet.next();
				 ex = new OptionList(exeId,title,instruction,type,
						 finished,finished?ColorPool.getColor("dGreen"):ColorPool.getColor("lGreen"),lines.toArray(new String[0]),options.toArray(new String[0]),observerSet.get(lessonTitle));	
				
				}
				
				
				
				
				else if(type.equals("FinalTest")) {
					ResultSet lineResult = DataBaseConnection.getStatment().executeQuery("select * from Line where exercise_id = "+ exeId+ " order by line_number;");
					HashMap<Integer, String> options = new HashMap<>();
					int i = 0;
					while(lineResult.next()) {
						String line_content = lineResult.getString("line_content");
						String lineType = lineResult.getString("line_type");
						if(lineType!=null && lineType.equals("List")) {
							String lineOption = lineResult.getString("line_options");
							options.put(i, lineOption);
						}
						lines.add(line_content);
						i++;
					}
					ResultSet finishedSet = DataBaseConnection.getStatment().executeQuery("select * from FinishedExercises where exercise_id = "+ exeId
							+" and user_id = "+ user.getId()+ " ;");
				    boolean finished = finishedSet.next();
				    ex = new FinalTest(exeId,title,instruction,type,finished,
				    		finished?ColorPool.getColor("dGreen"):ColorPool.getColor("lGreen"),(String[])lines.toArray(new String[0]),options,observerSet.get(lessonTitle));
					
				}
				
				
				
				
				else if(type.equals("Gap")) {
					ResultSet lineResult = DataBaseConnection.getStatment().executeQuery("select * from Line where exercise_id = "+ exeId+ " order by line_number");
					while(lineResult.next()) {
						String line_content = lineResult.getString("line_content");
						lines.add(line_content);
					}
					ResultSet finishedSet = DataBaseConnection.getStatment().executeQuery("select * from FinishedExercises where exercise_id = "+ exeId
							+" and user_id = "+ user.getId());
					boolean finished = finishedSet.next();
					ex = new Exercise(exeId,title,instruction, type,finished,
							finished?ColorPool.getColor("dGreen"):ColorPool.getColor("lGreen"),
							lines.toArray(new String[0]),observerSet.get(lessonTitle));	
				}
				
				
				
				
				else {
					ResultSet lineResult = DataBaseConnection.getStatment().executeQuery("select * from Line where exercise_id = "+ exeId+ " order by line_number ;");
					String words = result.getString("options");
					while(lineResult.next()) {
						String line_content = lineResult.getString("line_content");
						lines.add(line_content);
				    }
					lineResult = DataBaseConnection.getStatment().executeQuery("select * from FinishedExercises where exercise_id = "+ exeId
							+" and user_id = "+ user.getId()+ " ;");
					boolean finished = lineResult.next();
					ex = new OtherTypes(exeId,title, instruction,type, finished,
							finished?ColorPool.getColor("dGreen"):ColorPool.getColor("lGreen"),
							lines.toArray(new String[0]),words,observerSet.get(lessonTitle));
				}
				temp.add(ex);
				if(ex.isFinished()) {
					ExerciseObserver observer = observerSet.get(lessonTitle);
					if(observer != null)
						observer.notifyObserver(exeId);
				}
				
			}
			
			exerciseList.put(lessonTitle,temp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	return exerciseList.get(lessonTitle);
}

}
