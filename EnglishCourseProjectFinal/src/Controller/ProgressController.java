package Controller;

import java.util.ArrayList;
import Container.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import DataClass.DataBaseConnection;
import DataClass.Lesson;
import DataClass.User;
import FlyWeight.*;
import Momento.Momento;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import PanelPackage.*;
import VariablePool.ColorPool;
import VariablePool.MouseEventClass;
public class ProgressController {
    HomePanel hpanel;
    ProgressBar pb; 
    LevelsProgress lev;
    JFrame main;
    //Header hd;
    User user;
    

    public ProgressController(JFrame frame, User user) {
        main = frame;
        hpanel = new HomePanel();
        this.user = user;
        try {
        ArrayList<Lesson> lessons = Elementary.getLessons(user);
       
		MyHashMap<String,Integer> hash11 = new MyHashMap<>();
		    for(Lesson l : lessons) {
		    	Statement s = DataBaseConnection.getStatment();
		    	
					ResultSet mark = s.executeQuery("select * from FinishedLessons where lesson_id = "+l.getLessonId()+" and user_id = "+user.getId());
					if(mark.next()) hash11.put(l.getTitle(), (int)mark.getFloat("mark"));
					else hash11.put(l.getTitle(),0);
		    }
		    lev = new LevelsProgress(hash11);
		    addAction();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		       
		    
		    
		    
       
        JPanel panel = (JPanel)main.getContentPane().getComponent(0);
        Momento.push(panel);
        main.getContentPane().removeAll();
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(hpanel, BorderLayout.NORTH);
        mainPanel.add(lev, BorderLayout.CENTER);
        main.add(mainPanel);
        main.revalidate();
        main.repaint();

        hpanel.getHomePanel().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                main.getContentPane().removeAll();
                
                main.getContentPane().removeAll();
				JPanel panel = Momento.pop();
				panel.revalidate();
				panel.repaint();
				main.add(panel);
				main.revalidate();
				main.repaint();
                
            }
        });
        
        hpanel.getHomePanel().addMouseListener(new MouseEventClass(Color.red));

        lev.getElementary().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
            	 try {
            	        ArrayList<Lesson> lessons = Elementary.getLessons(user);
            	       
            			MyHashMap<String,Integer> hash11 = new MyHashMap<>();
            			    for(Lesson l : lessons) {
            			    	Statement s = DataBaseConnection.getStatment();
            			    	
            						ResultSet mark = s.executeQuery("select * from FinishedLessons where lesson_id = "+l.getLessonId()+" and user_id = "+user.getId());
            						if(mark.next()) hash11.put(l.getTitle(), (int)mark.getFloat("mark"));
            						else hash11.put(l.getTitle(),0);
            			    }
            			    main.getContentPane().removeAll();
        				    lev.replacePanel(hash11,ColorPool.getColor("elemColor"));
        				    lev.revalidate();
        				    lev.repaint();
        				    mainPanel.removeAll();
        				    mainPanel.add(hpanel, BorderLayout.NORTH);
        				    mainPanel.add(lev, BorderLayout.CENTER);
        				    mainPanel.revalidate();
           				    mainPanel.repaint();
//            			    lev = new Levels(hash11);
//            			    addAction();
        				    main.add(hpanel,BorderLayout.NORTH);
        				    main.add(lev,BorderLayout.CENTER);
        				    main.revalidate();
        				    main.repaint();
            					} catch (SQLException e) {
            						// TODO Auto-generated catch block
            						e.printStackTrace();
            					}
				   
              
            }
        });

        lev.getIntermediate().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
             
            	 try {
         	        ArrayList<Lesson> lessons = Intermediate.getLessons(user);
         	       
         			MyHashMap<String,Integer> hash11 = new MyHashMap<>();
         			    for(Lesson l : lessons) {
         			    	Statement s = DataBaseConnection.getStatment();
         			    	
         						ResultSet mark = s.executeQuery("select * from FinishedLessons where lesson_id = "+l.getLessonId()+" and user_id = "+user.getId());
         						if(mark.next()) hash11.put(l.getTitle(), (int)mark.getFloat("mark"));
         						else hash11.put(l.getTitle(),0);
         			    }
         			   main.getContentPane().removeAll();
   				    lev.replacePanel(hash11,ColorPool.getColor("itermColor"));
   				    lev.revalidate();
   				    lev.repaint();
   				    mainPanel.removeAll();
   				    mainPanel.add(hpanel, BorderLayout.NORTH);
   				    mainPanel.add(lev, BorderLayout.CENTER);
   				    mainPanel.revalidate();
   				    mainPanel.repaint();
//         			   lev = new Levels(hash11);
//         			   addAction();
   				    main.add(hpanel,BorderLayout.NORTH);
   				    main.add(lev,BorderLayout.CENTER);
   				    main.revalidate();
   				    main.repaint();
            	 }catch(Exception e) {
             		e.printStackTrace();
             	}
				   
               
               
            }
        });
        lev.getAdvanced().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
            	try {
        	        ArrayList<Lesson> lessons = Advanced.getLessons(user);
        	       
        			MyHashMap<String,Integer> hash11 = new MyHashMap<>();
        			    for(Lesson l : lessons) {
        			    	Statement s = DataBaseConnection.getStatment();
        			    	
        						ResultSet mark = s.executeQuery("select * from FinishedLessons where lesson_id = "+l.getLessonId()+" and user_id = "+user.getId());
        						if(mark.next()) hash11.put(l.getTitle(), (int)mark.getFloat("mark"));
        						else hash11.put(l.getTitle(),0);
        			    }
        			    main.getContentPane().removeAll();
		                lev.replacePanel(hash11,ColorPool.getColor("advColor"));
		                lev.revalidate();
		                lev.repaint();
		                mainPanel.removeAll();
		                mainPanel.add(hpanel, BorderLayout.NORTH);
		                mainPanel.add(lev, BorderLayout.CENTER);
		                mainPanel.revalidate();
	   				    mainPanel.repaint();
//        			    lev = new Levels(hash11);
//        			    addAction();
		                main.add(hpanel,BorderLayout.NORTH);
		                main.add(lev,BorderLayout.CENTER);
		                main.revalidate();
		                main.repaint();
            	}catch(Exception e) {
            		e.printStackTrace();
            	}
		               
			
              
                
            }
        });

        
    

    
        main.setLayout(new BorderLayout());
        Dimension sc = Toolkit.getDefaultToolkit().getScreenSize();
        int w = (int) sc.getWidth();
        int h = (int) sc.getHeight();
        
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        main.setSize(w, h);
        main.setLocationRelativeTo(null);
        main.setVisible(true); 
    }
    
    
    public void addAction() {
    	
    }

}