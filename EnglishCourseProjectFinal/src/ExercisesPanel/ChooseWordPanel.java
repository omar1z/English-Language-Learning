package ExercisesPanel;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

import VariablePool.ColorPool;
import VariablePool.FontPool;
public class ChooseWordPanel extends JPanel {
	ArrayList<JTextField> gabs = new ArrayList<>();
	public ChooseWordPanel(String[] lines, String[] options) {
		JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,30,20));
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel,BoxLayout.Y_AXIS));
		optionPanel.setBackground(Color.white);
	    contentPanel.setBackground(Color.white);
	    this.setBackground(ColorPool.getColor("gray"));
	   
	    
	    
	    for(String s : options) {
	    	JLabel label = new JLabel(" "+s+" ");
	    	label.setBackground(Color.white);
	    	label.setOpaque(true);
	    	label.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
	    	label.setFont(FontPool.getFont("normal"));
	    	optionPanel.add(label);
	    }
	    for(int i = 0,j=0;i<lines.length;i++) {
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
			JTextField temp = new JTextField();
			Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black);
			temp = new JTextField(10);
			temp.setBorder(bottomBorder);
			temp.setFont(FontPool.getFont("normal"));
			gabs.add(temp);
			panel.add(firstHalf);
			panel.add(temp);
			panel.add(secondHalf);
			j++;
			
		}
		else {
			JLabel label = new JLabel(lines[i]);
			label.setBackground(Color.white);
			label.setOpaque(true);
			label.setFont(FontPool.getFont("normal"));
			panel.add(label);
		}
		contentPanel.add(panel);
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		this.add(optionPanel);
		this.add(contentPanel);
	    }
	}
	
	public ArrayList<JTextField> getUserAwnser(){
		return gabs;
	}

}
