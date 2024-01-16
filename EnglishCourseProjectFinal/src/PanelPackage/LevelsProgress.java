package PanelPackage;
import javax.swing.*;

import Container.MyHashMap;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.*;

public class LevelsProgress extends JPanel {

    String level = "eeee";
    JPanel pane;
    JButton[] buttons = new JButton[3]; 
    ImageIcon I1 = new ImageIcon();
    ImageIcon I2 = new ImageIcon();
    ImageIcon I3 = new ImageIcon();
    MyHashMap<String, Integer> hashm;
    ProgressBar pb;
    int c = 1;
    
    // MarkGrade contentPanel
    public LevelsProgress(MyHashMap<String,Integer> hashMap) {
        this.hashm = hashMap;
        pane = new JPanel();
        this.setLayout(new BorderLayout());
        pane.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 40));
        pane.setBackground(Color.white);
        Color pistachioColor = new Color(147, 197, 114);
        Color purColor = new Color(100, 50, 200);
    
        this.setBackground(Color.WHITE);
        I1 = new ImageIcon("Images\\e.png");
        buttons[0] = new JButton(I1);
        buttons[0].setText("ELEMENTARY");
        buttons[0].setFocusable(false);
        buttons[0].setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));
        buttons[0].setBackground(Color.red);
        buttons[0].addMouseListener(new MouseAdapter() {
            
            @Override
            public void mousePressed(MouseEvent e) {
               
                buttons[0].setBackground(Color.red);
                buttons[0].setForeground(Color.WHITE);
                buttons[2].setBackground(Color.WHITE);
                buttons[2].setForeground(purColor);
                buttons[1].setBackground(Color.WHITE);
                buttons[1].setForeground(pistachioColor);
                
                
            }

        });
        
        buttons[0].setForeground(Color.WHITE);

        
        pane.add(buttons[0]);

        I2 = new ImageIcon("Images\\i.png");
        buttons[1] = new JButton(I2);
        buttons[1].setText("INTERMEDIATE");
        buttons[1].setBackground(Color.WHITE);
        buttons[1].setFocusable(false);
        buttons[1].setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));
        
            buttons[1].addMouseListener(new MouseAdapter() {
            
            @Override
            public void mousePressed(MouseEvent e) {
                
                    buttons[1].setForeground(Color.WHITE);
                    buttons[1].setBackground(pistachioColor);
                    buttons[0].setBackground(Color.WHITE);
                    buttons[0].setForeground(Color.red);
                    buttons[2].setBackground(Color.WHITE);
                    buttons[2].setForeground(purColor);
                
                
            }
            
        });
        
        
        pane.add(buttons[1]);

        I3 = new ImageIcon("Images\\a.png");
        buttons[2] = new JButton(I3);
        buttons[2].setText("ADVANCED");
        buttons[2].setBackground(Color.WHITE);
        buttons[2].setFocusable(false);
        buttons[2].setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));
        
        buttons[2].setForeground(purColor);
        buttons[2].addMouseListener(new MouseAdapter() {
            
            @Override
            public void mousePressed(MouseEvent e) {
                
                    buttons[2].setBackground(purColor);
                    buttons[2].setForeground(Color.WHITE);
                    buttons[0].setBackground(Color.WHITE);
                    buttons[0].setForeground(Color.red);
                    buttons[1].setBackground(Color.WHITE);
                    buttons[1].setForeground(pistachioColor);
                
                
            }
        });
        pane.add(buttons[2]);
        this.add(pane, BorderLayout.NORTH);
        pb = new ProgressBar(hashMap,Color.red);
        
        this.add(pb, BorderLayout.CENTER);
        //pcontrol = new ProgressController();
        //this.add(pcontrol);

        

        // this.setLayout(new BorderLayout());
        // this.add(this,BorderLayout.NORTH);
        // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Dimension sc = Toolkit.getDefaultToolkit().getScreenSize();
        // int w = (int) sc.getWidth();
        // int h = (int) sc.getHeight();
        // this.setSize(w, h);
        // this.setLocationRelativeTo(null);
        // this.setVisible(true);
    }
    public void replacePanel(MyHashMap<String,Integer> hasho, Color c){
    	this.remove(pb);
    	pb = new ProgressBar(hasho,c);
        this.hashm = hasho;
        
        this.add(pb);
        this.revalidate();
        this.repaint();
    }
    public JButton getElementary(){
        return buttons[0];
    }
    public JButton getIntermediate(){
        return buttons[1];
    }
    public JButton getAdvanced(){
        return buttons[2];
    }
    public ImageIcon getIm1(){
        return I1;
    }
    public ImageIcon getIm2(){
        return I2;
    }
    public ImageIcon getIm3(){
        return I3;
    }
    // public int getMark(){
    //     return mark;
    // }
}