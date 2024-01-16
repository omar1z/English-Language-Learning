package ExercisesPanel;
import javax.swing.*;
import javax.swing.border.Border;

import DataClass.Exercise;
import DataClass.FinalTest;
import DataClass.Lesson;
import DataClass.User;
import FlyWeight.Elementary;
import FlyWeight.Intermediate;
import VariablePool.FontPool;

import java.awt.*;
import java.util.*;
public class FinalTestPanel extends JPanel{
	ArrayList<JComponent> userAwnser = new ArrayList<>();
	
	public FinalTestPanel(String[] lines, HashMap<Integer,String> options) {
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.setBackground(Color.white);
		for(int i =0 ; i<lines.length;i++) {
			JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			panel.setBackground(Color.white);
			String[] labels = lines[i].split("\\*");
			if(labels.length == 2) {
				JLabel firstHalf = new JLabel(labels[0]);
				firstHalf.setBackground(Color.white);
				firstHalf.setOpaque(true);
				firstHalf.setFont(FontPool.getFont("normal"));
				
				JLabel secondHalf = new JLabel(labels[1]);
				secondHalf.setBackground(Color.white);
				secondHalf.setOpaque(true);
				secondHalf.setFont(FontPool.getFont("normal"));
				if(options.get(i) != null) {
					String[] option = options.get(i).split(",");
					JComboBox<String> c = new JComboBox<>(option);
					c.setBackground(Color.white);
					c.setFont(FontPool.getFont("normal"));
					panel.add(firstHalf);
					panel.add(c);
					panel.add(secondHalf);
					userAwnser.add(c);
				}
				else {
					JTextField gab = new JTextField(20);
					Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black);
					gab.setBorder(bottomBorder);
					gab.setBackground(Color.white);
					gab.setFont(FontPool.getFont("normal"));
					panel.add(firstHalf);
					panel.add(gab);
					panel.add(secondHalf);
					userAwnser.add(gab);
				}
			}
			else {
				JLabel label = new JLabel(lines[i]);
				label.setBackground(Color.white);
				label.setOpaque(true);
				label.setFont(FontPool.getFont("normal"));
				panel.add(label);
			}
			this.add(panel);
		}
	}
	public ArrayList<JComponent> getUserAwnser(){
		return userAwnser;
	}
	
}
