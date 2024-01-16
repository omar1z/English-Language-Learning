package Controller;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import DataClass.*;
import Decorator.Header;
import DisplayLessonContent.LessonPageContent;
import ExercisesPanel.*;
import Factory.ExerciseFactory;
import Factory.FrameFactory;
import FlyWeight.Advanced;
import FlyWeight.Elementary;
import FlyWeight.Intermediate;
import Momento.Momento;
import VariablePool.ColorPool;
import VariablePool.FontPool;

public class ExerciseController {
	private int i;
	private ArrayList<Exercise> exercises;
	private JFrame frame ;
	private JPanel header;
	private JPanel exercisePanel;
	private Lesson lesson;
	MainPanel<JPanel> mainPanel;
	public ExerciseController(JFrame frame,Lesson lesson, ArrayList<Exercise> exercises, int i,User user) {
		this.frame = frame;
		this.exercises = exercises;
		this.i = i;
		FrameFactory factory = new ExerciseFactory(frame,lesson,exercises.get(i));
		factory.displayFrame();
		mainPanel = (MainPanel) frame.getContentPane().getComponent(0);
		
		header = mainPanel.getHeaderPanel();
		
		JButton backButton = (JButton) header.getComponent(0);
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				JPanel panel = Momento.pop();
				panel.revalidate();
				panel.repaint();
				panel.getComponent(0).repaint();
				JScrollPane content = (JScrollPane)panel.getComponent(1);
				JViewport port = (JViewport) content.getComponent(0);
				LessonPageContent p = (LessonPageContent) port.getComponent(0);
				
				p.getLessonPanel().revalidate();
				p.getLessonPanel().repaint();
				p.getExercisePanel().revalidate();
				p.getExercisePanel().repaint();
				frame.add(panel);
				frame.revalidate();
				frame.repaint();
			}
			
		});
		JButton restart = (JButton) header.getComponent(5);
		restart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				ExerciseController c = new ExerciseController(frame,lesson,exercises,i,user);
				Momento.pop();
				
			}
			
		});
		
		JButton next = (JButton) header.getComponent(2);
		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(i+1 == exercises.size()) {
					frame.getContentPane().removeAll();
					JPanel panel = Momento.pop();
					panel.revalidate();
					frame.add(panel);
					frame.revalidate();
					frame.repaint();
				}
				else {
					ExerciseController c = new ExerciseController(frame,lesson,exercises,i+1,user);
					Momento.pop();
				}
		
			}
			
		});
		JButton previous = (JButton)  header.getComponent(3);
		previous.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(i == 0) {
					RuleController r = new RuleController(frame,lesson,exercises,user);
					Momento.pop();
				}
				else {
					ExerciseController c = new ExerciseController(frame,lesson,exercises,i-1,user);
					Momento.pop();	
				}
			}
			
		});
		JButton marking = (JButton) header.getComponent(4);
		marking.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<? extends JComponent> userAwnser = null;
				JPanel contentPanel = mainPanel.getContentPanel();
				switch (exercises.get(i).getType()) {
				case "Gap" :
					ExFillBlankPanel panel = (ExFillBlankPanel) contentPanel;
					userAwnser = panel.getArrayList();
					break;
				case "List":
					ExListContentPanel panel1 = (ExListContentPanel) contentPanel;
					userAwnser = panel1.getUserAnswer();
					break;
				case "Mistake":
					FindMistakePanel m = (FindMistakePanel) contentPanel;
					userAwnser = m.getUserAwnser();
					break;
				case "DragDrop":
					ExDnD d = (ExDnD) contentPanel;
					userAwnser = d.getUserAwnser();
					break;
				case "OptionWrite":
					ChooseWordPanel c = (ChooseWordPanel) contentPanel;
					userAwnser = c.getUserAwnser();
					break;
				case "FinalTest":
					FinalTestPanel f = (FinalTestPanel) contentPanel;
					userAwnser = f.getUserAwnser();
					break;
					default:
						break;
				}
				Float mark = Correction.correct(lesson, exercises.get(i), user, userAwnser);
				
				Color color = null;
				if(lesson.getLessonId() >= 1 && lesson.getLessonId()<= 7) {
					color = ColorPool.getColor("dPink");
				}
				else if(lesson.getLessonId() >= 8 && lesson.getLessonId()<= 14) {
					color = ColorPool.getColor("dGreen");
				}
				else if(lesson.getLessonId()>=15 && lesson.getLessonId()<=21) {
					color = ColorPool.getColor("dAdv");
				}
				if(! exercises.get(i).isFinished()) exercises.get(i).setFinished(mark, lesson, user, color);
				
				header.remove(marking);
				header.revalidate();
				header.repaint();
				UIManager.put("OptionPane.background", Color.white);
		        UIManager.put("Panel.background", Color.white);
		        UIManager.put("OptionPane.messageFont", FontPool.getFont("normal"));
		        UIManager.put("Button.background", Color.white);
		        JOptionPane.showMessageDialog(null, "<html>Your Grade : <font color='red'>"+mark+""+"&#37;</font></html>", "Custom Dialog", JOptionPane.WARNING_MESSAGE);
			    header.remove(marking);
			}
			
		});
	}
	

}
