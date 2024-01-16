package Factory;

import DataClass.Lesson;
import DataClass.Rule;
import Decorator.BaseHeader;
import Decorator.Header;
import ExercisesPanel.ChooseWordPanel;
import ExercisesPanel.MainPanel;
import ExercisesPanel.RuleContent;
import Momento.Momento;
import Decorator.AddNextDecorator;
import VariablePool.ColorPool;
import VariablePool.FontPool;

import java.awt.*;
import javax.swing.*;
public class RuleFactory extends FrameFactory {
	private Rule rule;
	private JFrame frame;
	private Lesson lesson;
	public RuleFactory(JFrame frame,Lesson lesson) {
		super(frame);
		this.frame = frame;
		this.rule = rule;
		this.lesson = lesson;
	}

	@Override
	protected void frameFactore() {
		Header header = new AddNextDecorator(new BaseHeader(lesson,lesson.getRule()));
		JLabel label = new JLabel(lesson.getRule().getInstruction());
		label.setBackground(ColorPool.getColor("lPink"));
		label.setFont(FontPool.getFont("thin"));
		label.setForeground(Color.black);
		label.setOpaque(true);
		RuleContent ruleContent =new RuleContent(lesson.getRule().getParagraphs());
		JScrollPane scroll = new JScrollPane(ruleContent);
		JViewport view = scroll.getViewport();
		view.setForeground(Color.white);
		scroll.revalidate();
		
		MainPanel<RuleContent> mainPanel = new MainPanel(header.getPanel(),ruleContent,label);
		Momento.push((JPanel)frame.getContentPane().getComponent(0));
		frame.getContentPane().removeAll();
		frame.add(mainPanel);
		frame.revalidate();
		frame.repaint();
		
	}

}
