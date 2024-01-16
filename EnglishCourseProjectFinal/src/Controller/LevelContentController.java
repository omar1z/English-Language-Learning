package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import DataClass.*;
import DisplayLessonContent.GenericPanel;
import DisplayLessonContent.LessonPageContent;
import DisplayLessonContent.LessonPageHeader;
import ExercisesPanel.MainPanel;
import Factory.*;
import FlyWeight.Advanced;
import FlyWeight.Elementary;
import FlyWeight.Intermediate;
import Momento.Momento;

import javax.swing.*;
public class LevelContentController {
	private JFrame frame;
	private ArrayList<Lesson> lessons;
	private ArrayList<Exercise> exercises = null;
	private Lesson lesson;
	private LessonPageContent contentPanel;
	private LessonPageHeader headerPanel;
	private boolean exercisePanelExist = false;
	private User user;
	private LevelContentController controller;
	public LevelContentController(JFrame frame,ArrayList<Lesson> lessons,User user) {
		this.frame = frame;
		this.lessons = lessons;
		this.user = user;
		this.controller = this;
		FrameFactory factory = new LessonPageFactory(frame, lessons);
		factory.displayFrame();
		
		MainPanel<LessonPageContent> mainPanel = (MainPanel<LessonPageContent>) frame.getContentPane().getComponent(0);
		contentPanel = mainPanel.getContentPanel();
		headerPanel = (LessonPageHeader) mainPanel.getHeaderPanel();
		JButton homeButton = headerPanel.getHomeButton();
		homeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.getContentPane().removeAll();
				JPanel panel = Momento.pop();
				panel.revalidate();
				frame.add(panel);
				frame.revalidate();
				frame.repaint();
				
			}
			
		});
		GenericPanel<Lesson> lessonPanel = contentPanel.getLessonPanel();
		ArrayList<JButton> buttons = lessonPanel.getButtonList();
		for(JButton button : buttons) {
			button.addActionListener(new DisplayExerciseEvent() );
		}
		
		
	}
	class DisplayExerciseEvent implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			JButton b = (JButton) e.getSource();
			exercises = null;
			lesson = lessons.stream().filter(l -> l.getTitle().equals(b.getText()))
					.findFirst()
					.orElse(null);
			if(lesson.getLessonId() >= 1 && lesson.getLessonId()<= 7) {
				exercises = Elementary.getExercises(lesson.getTitle(),user);
			}
			else if(lesson.getLessonId() >= 8 && lesson.getLessonId()<= 14) {
				exercises = Intermediate.getExercises(lesson.getTitle(), user);
			}
			else if(lesson.getLessonId()>=15 && lesson.getLessonId()<=21) {
				exercises = Advanced.getExercises(lesson.getTitle(), user);
			}
			
			if(! exercisePanelExist) {
				contentPanel.addExerciseToPanel(exercises,lesson.getRule());
				exercisePanelExist = true;
				controller.addExerciseActionListener();
			}
			else {
				contentPanel.removePanel();
				contentPanel.addExerciseToPanel(exercises,lesson.getRule());
				contentPanel.revalidate();
				contentPanel.repaint();
				controller.addExerciseActionListener();
				
			}
			
			
		}
		
	}
	public void addExerciseActionListener() {
		GenericPanel<Exercise> panel = contentPanel.getExercisePanel();
		JButton ruleButton = (JButton) panel.getComponent(0);
		ruleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RuleController c = new RuleController(frame,lesson,exercises,user);
				
			}
			
		});
		ArrayList<JButton> buttons = panel.getButtonList();
		for(int i =0 ;i<buttons.size();i++) {
			JButton b = buttons.get(i);
			b.addActionListener(new ButtonExerciseListener(i));
		}
	}
	
	class ButtonExerciseListener implements ActionListener{
		int i;
		public ButtonExerciseListener(int i) {
			this.i = i;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			ExerciseController controller = new ExerciseController(frame,lesson,exercises,i,user);
			
		}
		
	}

}
