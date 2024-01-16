package DisplayLessonContent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import DataClass.Exercise;
import DataClass.Rule;
import VariablePool.ColorPool;
import VariablePool.FontPool;
import VariablePool.MouseEventClass;

public class RuleButton extends JButton{
	private Rule rule;
	public RuleButton(Rule rule) {
		this.rule = rule;
		this.setText(rule.getTitle());
		this.setForeground(Color.black);
		this.setBackground(rule.getBackgroundColor());
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		this.setPreferredSize(new Dimension((screenWidth/2 -50),50));
		this.setFont(FontPool.getFont("normal"));
		this.addMouseListener(new MouseHilightListener(Color.white));
	}
protected class MouseHilightListener extends MouseEventClass{
		
		public MouseHilightListener(Color background) {
			super(background);
		}
		public void mouseEntered(MouseEvent e) {
			JButton b = (JButton) e.getSource();
			b.setBackground(Color.white);
			b.setForeground(ColorPool.getColor("foreGround"));
		}
		public void mouseExited(MouseEvent e) {
		JButton b = (JButton) e.getSource();
		b.setBackground(rule.getBackgroundColor());
		b.setForeground(Color.black);
		
		}
		public void mousePressed(MouseEvent e) {
			JButton b = (JButton) e.getSource();
			b.setBackground(rule.getBackgroundColor());
			b.setForeground(Color.black);
		}
		
	}

}
