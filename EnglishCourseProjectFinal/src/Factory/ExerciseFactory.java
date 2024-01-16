package Factory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import DataClass.Exercise;
import DataClass.FinalTest;
import DataClass.Lesson;
import DataClass.OptionList;
import DataClass.OtherTypes;
import Decorator.*;
import ExercisesPanel.*;
import Momento.Momento;
import VariablePool.ColorPool;
import VariablePool.FontPool;
public class ExerciseFactory extends FrameFactory{

	private JFrame frame;
	private Lesson lesson;
	private Exercise exercise;
	public ExerciseFactory(JFrame frame,Lesson lesson,Exercise exercise) {
		super(frame);
		this.frame = frame;
		this.lesson = lesson;
		this.exercise = exercise;
		
	}

	@Override
	protected void frameFactore() {
		Header header = new AddMarkingDecorator(new AddPreviousDicorator(new AddNextDecorator(new BaseHeader(lesson,exercise))));
		JLabel label = new JLabel(exercise.getInstruction());
		label.setBackground(ColorPool.getColor("lPink"));
		label.setFont(FontPool.getFont("thin"));
		label.setForeground(Color.black);
		label.setOpaque(true);
		switch(exercise.getType()) {
		case "Gap":
			ExFillBlankPanel content = new ExFillBlankPanel(exercise.getLines());
			MainPanel<ExFillBlankPanel> mainPanel = new MainPanel(header.getPanel(),content,label);
			Momento.push((JPanel)frame.getContentPane().getComponent(0));
			frame.getContentPane().removeAll();
			frame.add(mainPanel);
			frame.revalidate();
			frame.repaint();
			break;
		case "List":
			OptionList ex = (OptionList) exercise;
			ExListContentPanel content1 = new ExListContentPanel(ex.getLines(),ex.getOptions());
			MainPanel<ExListContentPanel> mainPanel1 = new MainPanel(header.getPanel(),content1,label);
			Momento.push((JPanel)frame.getContentPane().getComponent(0));
			frame.getContentPane().removeAll();
			frame.add(mainPanel1);
			frame.revalidate();
			frame.repaint();
			break;
		case "Mistake":
			OtherTypes ex1=(OtherTypes) exercise;
			FindMistakePanel content2 = new FindMistakePanel(ex1.getLines(),ex1.getWords());
			MainPanel<FindMistakePanel> mainPanel2 = new MainPanel(header.getPanel(),content2,label);
			Momento.push((JPanel)frame.getContentPane().getComponent(0));
			frame.getContentPane().removeAll();
			frame.add(mainPanel2);
			frame.revalidate();
			frame.repaint();
			break;
		case "DragDrop":
			OtherTypes ex2=(OtherTypes) exercise;
			ExDnD content5 = new ExDnD(ex2.getLines(),ex2.getWords());
			MainPanel<ExDnD> mainPanel5 = new MainPanel(header.getPanel(),content5,label);
			Momento.push((JPanel)frame.getContentPane().getComponent(0));
			frame.getContentPane().removeAll();
			frame.add(mainPanel5);
			frame.revalidate();
			frame.repaint();
			break;
		case "FinalTest":
			FinalTest ex3=(FinalTest) exercise;
			FinalTestPanel content3 =new FinalTestPanel(ex3.getLines(),ex3.getOptions());
			MainPanel<FinalTestPanel> mainPanel3 = new MainPanel(header.getPanel(),content3,label);
			Momento.push((JPanel)frame.getContentPane().getComponent(0));
			frame.getContentPane().removeAll();
			frame.add(mainPanel3);
			frame.revalidate();
			frame.repaint();
			break;
		case "OptionWrite":
			OtherTypes ex4=(OtherTypes) exercise;
			ChooseWordPanel content4 = new ChooseWordPanel(ex4.getLines(),ex4.getWords());
			MainPanel<ChooseWordPanel> mainPanel4 = new MainPanel(header.getPanel(),content4,label);
			Momento.push((JPanel)frame.getContentPane().getComponent(0));
			frame.getContentPane().removeAll();
			frame.add(mainPanel4);
			frame.revalidate();
			frame.repaint();
			break;
		default:
			System.out.println("hello");
			break;
		}
		
		
		
	}

}
