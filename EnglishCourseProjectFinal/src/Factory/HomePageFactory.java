package Factory;

import HomeLevels.HeaderLevelsPanel;
import HomeLevels.LevelPanel;
import Momento.Momento;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import DataClass.*;
import ExercisesPanel.MainPanel;


import javax.swing.*;

public class HomePageFactory extends FrameFactory {
	JFrame frame ;
	ArrayList<Level> levels ;
	public HomePageFactory(JFrame frame,ArrayList<Level> levels) {
		super(frame);
		this.levels = levels;
		this.frame = frame;
	}

	@Override
	protected void frameFactore() {
		
		HeaderLevelsPanel header = new HeaderLevelsPanel();
		LevelPanel  content = new LevelPanel(levels);
		MainPanel<LevelPanel> mainPanel = new MainPanel(header,content);
		Momento.push((JPanel) frame.getContentPane().getComponent(0));
		frame.getContentPane().removeAll();
		frame.add(mainPanel);
		frame.revalidate();
		frame.repaint();
		
	}
	
}
