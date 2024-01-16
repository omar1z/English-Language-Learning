package Decorator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import DataClass.DataBaseItem;
import DataClass.Lesson;
import VariablePool.ColorPool;
import VariablePool.FontPool;
import VariablePool.MouseEventClass;

public class BaseHeader implements Header {
	private JPanel header;
	private DataBaseItem item;
	private Lesson lesson;
	JLabel ex;
	JButton back;
	GroupLayout layout;
	public BaseHeader(Lesson lesson, DataBaseItem item) {
		this.lesson = lesson;
		this.item = item;
		header = new JPanel();
		layout = new GroupLayout(header);
	}
	public void createPanel() {
		back = new JButton(lesson.getTitle(),new ImageIcon(lesson.getHeaderImage()));
		ex = new JLabel(item.getTitle());
		back.setBackground(Color.white);
		back.setHorizontalTextPosition(JButton.CENTER);
		back.setVerticalTextPosition(JButton.BOTTOM);
		back.setFocusable(false);
		back.setFont(FontPool.getFont("normal"));
		back.setBackground(Color.white);
		back.setPreferredSize(new Dimension(75,75));
		if(lesson.getLessonId() >= 1 && lesson.getLessonId()<= 7) {
			back.setForeground(ColorPool.getColor("elemColor"));
			back.addMouseListener(new MouseEventClass(ColorPool.getColor("elemColor")));
			ex.setForeground(ColorPool.getColor("elemColor"));
		}
		else if(lesson.getLessonId() >= 8 && lesson.getLessonId()<= 14) {
			back.setForeground(ColorPool.getColor("itermColor"));
			back.addMouseListener(new MouseEventClass(ColorPool.getColor("itermColor")));
			ex.setForeground(ColorPool.getColor("itermColor"));
		}
		else if(lesson.getLessonId() >= 15 && lesson.getLessonId()<= 21) {
			back.setForeground(ColorPool.getColor("advColor"));
			back.addMouseListener(new MouseEventClass(ColorPool.getColor("advColor")));
			ex.setForeground(ColorPool.getColor("advColor"));
		}
		
		ex.setVerticalTextPosition(JLabel.CENTER);
		ex.setHorizontalTextPosition(JLabel.CENTER);
		ex.setPreferredSize(new Dimension(75,75));
		ex.setFont(FontPool.getFont("normal"));
		
		layout.setAutoCreateGaps(true); // Enable auto gaps between components
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(back)
                        .addGap(10)
                        .addComponent(ex)
                    
            );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                   // .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(back)
                            .addComponent(ex)
                        )
                    
            );
        header.setLayout(layout);
		
        header.setBackground(Color.white);
		
	}
	public JPanel getPanel(){
		this.createPanel();
		return header;
	}
	public JButton getBackButton() {
		return back;
	}
	public JLabel getLabel(){
		return ex;
	}

}
