package DataClass;

import java.awt.Color;

import Observer.ExerciseObserver;

public class OptionList extends Exercise {
    private String[] options;

    public OptionList(int exerciseId, String title, String instruction,String type,boolean finished,Color background,String[] lines, String[] options,ExerciseObserver observer) {
        super(exerciseId, title, instruction,type,finished,background, lines,observer);
        this.options = options;
    }

    public String[] getOptions() {
        return options;
    }
}
