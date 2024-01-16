package ExercisesPanel;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

import VariablePool.ColorPool;
import VariablePool.FontPool;

public class ExFillBlankPanel extends JPanel{
	ArrayList<JTextField> userAwnser = new ArrayList<>();
	public ExFillBlankPanel(String[] lines) {
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.setBackground(Color.white);
		
		for(int i=0;i<lines.length;i++) {
			
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
				
				Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black);
				JTextField gab = new JTextField(10);
				gab.setBorder(bottomBorder);
				gab.setFont(FontPool.getFont("normal"));
				userAwnser.add(gab);
				panel.add(firstHalf);
				panel.add(gab);
				panel.add(secondHalf);
				
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
	
	
	public ArrayList<JTextField> getArrayList(){
		return userAwnser;
	}

}
