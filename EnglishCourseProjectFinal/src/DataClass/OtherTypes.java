package DataClass;

import java.awt.Color;

import Observer.ExerciseObserver;
// represet exercise
public class OtherTypes extends Exercise {

    private String words;

    public OtherTypes(int exerciseId, String title, String instruction, String type,boolean finished, Color background,String[] lines, String wordes,ExerciseObserver observer) {
        super(exerciseId, title, instruction,type,finished,background,lines,observer);
        this.words = wordes;
    }

    public String[] getWords() {
    	String[] word=words.split(",");
        return word;
    }
}
