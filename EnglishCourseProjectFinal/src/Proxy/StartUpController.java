package Proxy;

import javax.swing.*;

import Controller.HomePageController;
import DataClass.*;
import PanelPackage.EngLDesign;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class StartUpController{

    private JFrame frame;
    private JPanel contentPanel;
    private EngLDesign engLDesign;

    public StartUpController(User user,JFrame frame) {
         this.frame = frame;
         contentPanel = new JPanel(new BorderLayout());
         engLDesign = new EngLDesign(user);
         contentPanel.add(engLDesign,BorderLayout.CENTER);
         this.frame.getContentPane().removeAll();
         this.frame.add(contentPanel,BorderLayout.CENTER);
         this.frame.revalidate();
         this.frame.repaint();
         engLDesign.getButtonStart().addActionListener(new ActionListener() {

             public void actionPerformed(ActionEvent actionEvent) {
                 HomePageController c = new HomePageController(frame,user);
             }
         });
         
     
    }

    
    
}
