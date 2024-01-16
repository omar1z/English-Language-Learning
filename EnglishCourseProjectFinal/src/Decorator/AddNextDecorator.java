package Decorator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import VariablePool.ColorPool;
import VariablePool.FontPool;
import VariablePool.MouseEventClass;

public class AddNextDecorator extends HeaderDecorator {
	private JButton next;
	//private JButton previous;
	private JPanel panel;
	public AddNextDecorator(Header header) {
		super(header);
		next = new JButton("next", new ImageIcon("Images//nextIcon.png"));
		next.setHorizontalTextPosition(JButton.CENTER);
		next.setVerticalTextPosition(JButton.BOTTOM);
		next.setBackground(Color.white);
		
		next.addMouseListener(new MouseEventClass(ColorPool.getColor("elemColor")));
		next.setFont(FontPool.getFont("normal"));
       
        next.setForeground(ColorPool.getColor("elemColor"));
        
		
	}

	@Override
	public void createPanel() {
		panel = super.getHeader().getPanel();
		GroupLayout layout = (GroupLayout) panel.getLayout();
		layout.setAutoCreateGaps(true); 
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addComponent(panel.getComponent(0))
                        .addGap(10) 
                        .addComponent(panel.getComponent(1))
                        .addGap((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth())- 450)
                        //.addComponent(previous)
                        .addComponent(next)
                    
            );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(panel.getComponent(0))
                            .addComponent(panel.getComponent(1))
                        //    .addComponent(previous)
                            .addComponent(next)
                        )
                    
            );
        panel.setLayout(layout);
		
	}

	@Override
	public JPanel getPanel() {
		// TODO Auto-generated method stub
		this.createPanel();
		return panel;
	}


}
