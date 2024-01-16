package HomeLevels;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import DataClass.*;
import VariablePool.ColorPool;
import VariablePool.FontPool;
import VariablePool.MouseEventClass;
public class LevelPanel extends JPanel {
	JPanel posterPanel;
	JPanel levelsPanel;
	ArrayList<JButton> levelButton = new ArrayList<>();
	
	public LevelPanel(ArrayList<Level> list) {
		levelsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,50,0));
		for(Level l : list) {
			ImageIcon i = new ImageIcon(l.getImagePath());
			JButton b = new JButton(l.getLevelDescription(),i);
			b.setHorizontalTextPosition(JButton.CENTER);
			b.setVerticalTextPosition(JButton.BOTTOM);
			b.setBackground(new Color(255,255,255));
			b.setPreferredSize(new Dimension(200,200));
			b.setForeground(ColorPool.getColor(l.getLevelColor()));
			b.setFont(FontPool.getFont("level"));
			b.addMouseListener(new MouseEventClass(ColorPool.getColor(l.getLevelColor())));
			levelsPanel.add(b);
			levelButton.add(b);
		}
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		posterPanel = new Poster();
		posterPanel.setBackground(Color.white);
		levelsPanel.setBackground(Color.white);
		this.add(posterPanel);
		this.add(levelsPanel);
	}
	
	
	public JPanel getPosterPanel() {
		return posterPanel;
	}
	public ArrayList<JButton> getLevelsButton(){
		return levelButton;
	}
	public JPanel getLevelPanel() {
		return levelsPanel;
	}

}

class Poster extends JPanel {
	String text = "Challenges are what make life interesting and overcoming them is what makes life meaningful";
	Timer t;
	Poster panel;
	ArrayList<String> qoute = new ArrayList<>();
	public Poster() {
		panel = this;
		Statement s = DataBaseConnection.getStatment();
		Random random = new Random();
		int randomNumber = random.nextInt(50);
		try {
			ResultSet set = s.executeQuery("select * from qoute;");
			while(set.next()) qoute.add(set.getString("qoute"));
			text = qoute.get(randomNumber);
			t = new Timer(5000, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
						Random random = new Random();
						int randomNumber = random.nextInt(50);
						
						text = qoute.get(randomNumber);
						panel.repaint();
						panel.revalidate();
					
					
				}
				
			});
			t.start();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    Font font = new Font("Arial",Font.BOLD+ Font.ITALIC, 26);
	    this.setFont(font);
	    FontMetrics fontMetrics = g.getFontMetrics(font);
	    int stringWidth = fontMetrics.stringWidth(text);
	    int x = (this.getWidth() - stringWidth) / 2;
	    int y = this.getHeight() / 2; 
	    g.setColor(new Color(121, 177, 194));
	    g.drawString(text, x, y);
	    
	   
	}

}
