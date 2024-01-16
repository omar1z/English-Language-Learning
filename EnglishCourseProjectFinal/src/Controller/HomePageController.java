package Controller;

import HomeLevels.HeaderLevelsPanel;
import HomeLevels.LevelPanel;
import Proxy.LogInController;

import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

import DataClass.*;
import ExercisesPanel.MainPanel;
import Factory.*;
import FlyWeight.*;
public class HomePageController {

	private LevelPanel panel;
	private HeaderLevelsPanel header;
	private ArrayList<Level> levels = new ArrayList<>();
	private JFrame frame;
	
	public HomePageController(JFrame frame, User user) {
		this.frame = frame;
		Statement s = DataBaseConnection.getStatment();
		try {
			ResultSet levelSet = s.executeQuery("Select * from Levels");
			while(levelSet.next()) {
				int levelId = levelSet.getInt("level_id");
				String levelDesc = levelSet.getString("level_description");
				String imagePath = levelSet.getString("image_path");
				String levelColor = levelSet.getString("color");
				
				levels.add(new Level(levelId,levelDesc,imagePath,levelColor));
			}
			FrameFactory factory = new HomePageFactory(frame,levels);
			factory.displayFrame();
			MainPanel<LevelPanel> mainPanel = (MainPanel<LevelPanel>) frame.getContentPane().getComponent(0);
			panel = mainPanel.getContentPanel();
			header =  (HeaderLevelsPanel) mainPanel.getHeaderPanel();
			ArrayList<JButton> buttons = panel.getLevelsButton();
			
			JButton elementry = buttons.get(0);
			elementry.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					LevelContentController lessons = new LevelContentController(frame,Elementary.getLessons(user),user);
					
				}
				
			});
			JButton intermediate = buttons.get(1);
			intermediate.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					LevelContentController lessons = new LevelContentController(frame,Intermediate.getLessons(user),user);
				}
				
			});
			JButton advanced = buttons.get(2);
			advanced.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					LevelContentController lessons = new LevelContentController(frame,Advanced.getLessons(user),user);
					
				
				}
				
			});
			
			JButton progress = (JButton) header.getProgressButton();
			progress.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					ProgressController c = new ProgressController(frame,user);
				}
				
			});
			JButton signOut = (JButton) header.getSignOutButton();
			signOut.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new LogInController();
					frame.dispose();
					
				}
				
			});
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
