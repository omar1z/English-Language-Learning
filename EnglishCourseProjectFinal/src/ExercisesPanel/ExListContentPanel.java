package ExercisesPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import VariablePool.ColorPool;
import VariablePool.FontPool;

public class ExListContentPanel extends JPanel {
	ArrayList<JComboBox> options=new ArrayList<>();
	public ExListContentPanel(String[] lines, String[]  optionsContent) {
		
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		for(int i=0,j = 0;i<lines.length;i++) {
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
			JComboBox temp = new JComboBox<String>(optionsContent[j].split(","));
			temp.setBackground(Color.white);
			temp.setFont(FontPool.getFont("normal"));
			options.add(temp);
			panel.add(firstHalf);
			panel.add(temp);
			panel.add(secondHalf);
			j++;
			}
			else {
				JLabel l = new JLabel(lines[i]);
				l.setBackground(Color.white);
				l.setOpaque(true);
				l.setFont(FontPool.getFont("normal"));
				panel.add(l);
			}
			
			
			this.add(panel);
		}
	}
	public ArrayList<JComboBox> getUserAnswer() {
		return options;
	}
	

}
