package DisplayLessonContent;
import javax.swing.*;

import DataClass.*;
import DisplayLessonContent.GenericPanel.MouseHilightListener;
import VariablePool.FontPool;

import java.awt.*;
import java.util.ArrayList;
public class LessonPageContent extends JPanel {
	GenericPanel<Lesson> lessonPanel;
	GenericPanel<Exercise> exercisePanel;
	RuleButton ruleButton;
	ArrayList<Exercise> exerciseList = null;
	Rule rule ;
	public LessonPageContent(ArrayList<Lesson> lessons) {
		lessonPanel = new GenericPanel<>(lessons);
		this.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		this.add(lessonPanel);
		this.setBackground(Color.white);
	}
	public void addExerciseToPanel(ArrayList<Exercise> exercises,Rule rule) {
		this.exerciseList = exercises;
		this.rule = rule;
		this.exercisePanel = new GenericPanel<>(exercises);
		this.ruleButton = new RuleButton(rule);
		exercisePanel.add(ruleButton,0);
		this.add(exercisePanel);
		this.revalidate();
		this.repaint();
	}
	public void removePanel() {
		this.remove(exercisePanel);
		
	}
	public  GenericPanel<Lesson> getLessonPanel() {
		return lessonPanel;
	}
	public GenericPanel<Exercise> getExercisePanel(){
		return exercisePanel;
	}
	public RuleButton getRuleButton() {
		return ruleButton;
	}
	public ArrayList<Exercise> getExerciseList(){
		return exerciseList;
	}
	public Rule getRule() {
		return rule;
	}

}
