package VariablePool;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class MouseEventClass extends MouseAdapter{
	Color background;
	Color originalColor = new Color(255,255,255);
	public MouseEventClass(Color background) {
		this.background = background;
	}
	public void mouseEntered(MouseEvent e) {
		JButton b = (JButton) e.getSource();
		b.setBackground(background);
		b.setForeground(originalColor);
	}
	public void mouseExited(MouseEvent e) {
		JButton b = (JButton) e.getSource();
		b.setBackground(new Color(255,255,255));
		b.setForeground(background);
	}
	public void mousePressed(MouseEvent e) {
		JButton b = (JButton) e.getSource();
		b.setBackground(new Color(255,255,255));
		b.setForeground(background);
	}
}