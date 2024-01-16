package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import DataClass.Exercise;
import DataClass.Lesson;
import DataClass.Rule;
import DataClass.User;
import ExercisesPanel.MainPanel;
import ExercisesPanel.RuleContent;
import Factory.*;
import Momento.Momento;


public class RuleController {
	private JFrame frame;
	private Lesson lesson;
	private JPanel header;
	ArrayList<Exercise> exercises;
	public RuleController(JFrame frame , Lesson lesson,ArrayList<Exercise> exercises,User user) {
		this.frame = frame;
		this.lesson = lesson;
		FrameFactory factory = new RuleFactory(frame, lesson);
		factory.displayFrame();
		MainPanel<RuleContent> mainPanel = (MainPanel<RuleContent>) frame.getContentPane().getComponent(0);
		header = mainPanel.getHeaderPanel();
		JButton backButton = (JButton) header.getComponent(0);
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				JPanel panel = Momento.pop();
				panel.revalidate();
				frame.add(panel);
				frame.revalidate();
				frame.repaint();
			}
			
		});
		
		JButton next = (JButton) header.getComponent(2);
		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					ExerciseController c = new ExerciseController(frame,lesson,exercises,0,user);
					Momento.pop();
				}
			
		});
		
	}
	

}
