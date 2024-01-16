package PanelPackage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

import DataClass.User;
import VariablePool.ColorPool;
import VariablePool.FontPool;

import java.awt.image.BufferedImage;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Flow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EngLDesign extends JPanel{
    //LevelsFrame lv = new LevelsFrame();
    JPanel info = new JPanel();
    private JLabel i1,i2,i3,i4,i5;
    private ImageIcon I1,I2,I3,I4,I5;
    private JPanel user = new JPanel();
    private JLabel name = new JLabel();
    private JLabel gmail = new JLabel();
    private JLabel my = new JLabel();
    private JPanel program = new JPanel();
    
    
    
    private JButton b1 = new JButton();
    private JButton b2 = new JButton();
    private JButton b3 = new JButton();
    private JButton b4 = new JButton();

    private JLabel l1 = new JLabel();
    private JLabel l2 = new JLabel();
    private JLabel l3 = new JLabel();
    private JLabel l4 = new JLabel();

    private JPanel bigP = new JPanel();
    private JPanel smallp1 = new JPanel();
    private JPanel smallp2 = new JPanel();
    private JPanel smallp3 = new JPanel();
    private JPanel smallp4 = new JPanel();
    

    

    public EngLDesign( User userInfo){
        bigP.setLayout(new GridLayout(2,4,10,30));
        bigP.setPreferredSize(new Dimension(100,200));
        bigP.setBounds(390, 310, 700, 400);
        bigP.setBackground(Color.WHITE);
        this.setLayout(null);
        program.setPreferredSize(new Dimension(100,200));
        program.setBounds(300, 255, 900, 50);
        program.setBackground(new Color(121, 177, 194));
        program.setLayout(new FlowLayout(FlowLayout.CENTER));
        my.setText("MY PROGRAMS");
        my.setFont(new Font("Segoe UI", Font.PLAIN+Font.ITALIC, 35));
        my.setHorizontalTextPosition(JLabel.CENTER);
        my.setForeground(Color.WHITE);
        program.add(my);
        info.setPreferredSize(new Dimension(100,200));
        info.setBounds(300, 75, 900, 170);
        info.setBorder(new LineBorder(Color.WHITE, 10));
        info.setLayout(new FlowLayout(FlowLayout.LEFT,120,30));
        info.setBackground(new Color(255,255,255));
        name.setText(userInfo.getFullName());
        name.setFont(new Font("Segoe UI", Font.PLAIN+Font.ITALIC, 30));
        name.setForeground(new Color(121, 177, 194));
        gmail.setText(userInfo.getGmail());
        gmail.setFont(new Font("Arial", Font.ITALIC, 25));
        user.setLayout(new GridLayout(2,1,10,40));
        user.add(name);
        user.add(gmail);
        user.setBackground(Color.WHITE);
        user.setBounds(200,0,200,200);
        I1 = new ImageIcon("Images\\p.png");
        i1 = new JLabel(I1);
        info.add(i1);
        info.add(user);
        
        this.add(info);
        this.add(program);
        smallp1.setLayout(new FlowLayout());
        I2 = new ImageIcon("Images\\icons8-grammar-100.png");
        i2 = new JLabel(I2);
        I3 = new ImageIcon("Images\\icons8-reading-96.png");
        i3 = new JLabel(I3);
        I4 = new ImageIcon("Images\\icons8-writing-96.png");
        i4 = new JLabel(I4);
        I5 = new ImageIcon("Images\\icons8-speaking-96.png");
        i5 = new JLabel(I5);
        b1.setText("Start");
        b1.setBackground(Color.WHITE);
        
        b2.setText("Start");
        b2.setBackground(Color.WHITE);
        b3.setText("Start");
        b3.setBackground(Color.WHITE);
        b4.setText("Start");
        b4.setBackground(Color.WHITE);

        smallp2.setLayout(new FlowLayout());
        smallp3.setLayout(new FlowLayout());
        smallp4.setLayout(new FlowLayout());
        l1.setText("Grammar Lessons");
        l1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
        l2.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
        l3.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));
        l4.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 24));

        l2.setText("Reading Practices");
        l3.setText("Practical Writing");
        l4.setText("Active Speaking");
        
        l1.setForeground(new Color(121, 177, 194));
        l2.setForeground(new Color(121, 177, 194));
        l3.setForeground(new Color(121, 177, 194));
        l4.setForeground(new Color(121, 177, 194));
        smallp1.setBackground(Color.white);
        smallp2.setBackground(Color.white);
        smallp3.setBackground(Color.white);
        smallp4.setBackground(Color.white);
        smallp1.add(i2);
        smallp1.add(l1);
        smallp1.add(b1);
        smallp2.add(i3);
        smallp2.add(l2);
        smallp2.add(b2);
        smallp3.add(i4);
        smallp3.add(l3);
        smallp3.add(b3);
        smallp4.add(i5);
        smallp4.add(l4);
        smallp4.add(b4);
        bigP.add(smallp1);
        bigP.add(smallp2);
        bigP.add(smallp3);
        bigP.add(smallp4);

        this.add(bigP);
        b1.setBackground(new Color(121, 177, 194));
        b1.setFont(FontPool.getFont("normal"));
        b2.setEnabled(false);
        b2.setFont(FontPool.getFont("normal"));
        b3.setEnabled(false);
        b3.setFont(FontPool.getFont("normal"));
        b4.setEnabled(false);
        b4.setFont(FontPool.getFont("normal"));
        this.setBackground(Color.white);
    }
    
    public JButton getButtonStart() {
    	return b1;
    }
}
