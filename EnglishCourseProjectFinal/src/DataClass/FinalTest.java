package DataClass;

import java.awt.Color;
import java.util.HashMap;

import Observer.ExerciseObserver;

public class FinalTest extends Exercise {

	HashMap<Integer,String> options ;
	
	public FinalTest(int exeId, String title, String instruction,String type,boolean finished,Color background, String[] lines, HashMap<Integer,String> options,ExerciseObserver observer) {
		super(exeId,title,instruction,type,finished,background,lines,observer);
		this.options = options;
	}
	public HashMap<Integer,String> getOptions(){
		return options;
	}
}
