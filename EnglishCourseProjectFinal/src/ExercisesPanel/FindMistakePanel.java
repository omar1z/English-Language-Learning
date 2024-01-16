package ExercisesPanel;

import javax.swing.*;
import javax.swing.border.Border;

import VariablePool.FontPool;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class FindMistakePanel extends JPanel {
	ArrayList<JTextField> gabs;
	
	public FindMistakePanel(String[] lines, String[] word) {
		gabs = new ArrayList<JTextField>();
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		for(int i=0,j=0;i<lines.length;i++) {
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
				temp.setText(word[j]);
				
				temp.setBorder(BorderFactory.createEmptyBorder());
				temp.setFont(FontPool.getFont("normal"));
				temp.setBackground(Color.white);
				temp.setFocusable(false);
				temp.addMouseListener(new FindMistakeListener());
				temp.setEditable(false);
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
			this.add(panel);
		}
	}


class FindMistakeListener extends MouseAdapter{

	@Override
	public void mousePressed(MouseEvent e) {
		JTextField t =(JTextField) e.getSource();
		Border border = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black);
		t.setBorder(border);
		t.setColumns(t.getText().length());
		t.setFocusable(true);
		t.setEditable(true);
		t.revalidate();
		JPanel p =(JPanel) t.getParent();
		p.revalidate();
		
	}
	
}

public ArrayList<JTextField> getUserAwnser(){
	return gabs;
}
}
