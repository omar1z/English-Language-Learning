package Observer;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DataClass.DataBaseConnection;
import DataClass.Lesson;
import DataClass.User;
import VariablePool.ColorPool;

public class ExerciseObserver {
	private Lesson lesson;
	private ArrayList<Integer> finishedExercise = new ArrayList<>();
	private User user;
	public ExerciseObserver(Lesson lesson, User user) {
		this.lesson = lesson;
		this.user = user;
	}
	public void notifyObserver(int finishedExerciseId) {
		finishedExercise.add(finishedExerciseId);
		Statement s = DataBaseConnection.getStatment();
		try {
			ResultSet r = s.executeQuery("select count(exercise_id) from exercises where lesson_id = "+lesson.getLessonId());
			r.next();
			int count = r.getInt(1);
			if(finishedExercise.size() == count) {
				Color c=null;
				if(lesson.getLessonId()>=1 && lesson.getLessonId()<=7) c = ColorPool.getColor("dPink");
				else if(lesson.getLessonId()>=8 && lesson.getLessonId()<=14) c= ColorPool.getColor("dGreen");
				else if(lesson.getLessonId()>=15 && lesson.getLessonId()<=21) c=ColorPool.getColor("dAdv");
				lesson.setFinished(user, c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
