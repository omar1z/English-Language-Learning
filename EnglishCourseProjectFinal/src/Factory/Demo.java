package Factory;
import Controller.*;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import DataClass.Exercise;
import DataClass.Lesson;
import DataClass.Level;
import DataClass.OptionList;
import DataClass.User;
import FlyWeight.*;
public class Demo {
	public static void main(String[] args) {
		JFrame f = new JFrame();
		JButton b = new JButton("ok");
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			HomePageController	controller = new HomePageController(f, new User(4,"",""));
				
			}
			
		});
		JPanel p = new JPanel(new FlowLayout());
		p.add(b);
		f.add(p);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		f.setVisible(true);
		

	}


}
