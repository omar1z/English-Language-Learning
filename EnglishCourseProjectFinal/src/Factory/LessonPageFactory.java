package Factory;

import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import DataClass.*;
import DisplayLessonContent.GenericPanel;
import DisplayLessonContent.LessonPageContent;
import DisplayLessonContent.LessonPageHeader;
import ExercisesPanel.MainPanel;
import Momento.Momento;

public class LessonPageFactory extends FrameFactory {
	private JFrame frame;
	private ArrayList<Lesson> lessons;
	public LessonPageFactory(JFrame frame, ArrayList<Lesson> lessons) {
		super(frame);
		this.frame = frame;
		this.lessons = lessons;
		
	}

	@Override
	protected void frameFactore() {
		
		LessonPageHeader header = new LessonPageHeader();
		LessonPageContent content = new LessonPageContent(lessons);
		MainPanel<LessonPageContent> mainPanel = new MainPanel(header,content);
		Momento.push((JPanel) frame.getContentPane().getComponent(0));
		frame.getContentPane().removeAll();
		frame.add(mainPanel);
		frame.revalidate();
		frame.repaint();
		
	}

}
