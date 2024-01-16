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

public class AddPreviousDicorator extends HeaderDecorator{
	
	private JButton previous;
	private JPanel panel;
	public AddPreviousDicorator(Header header) {
		super(header);
		previous = new JButton("previous",new ImageIcon("Images//previousIcon.png"));
		previous.setHorizontalTextPosition(JButton.CENTER);
		previous.setVerticalTextPosition(JButton.BOTTOM);
		previous.setBackground(Color.white);
		previous.setPreferredSize(new Dimension(50,50));
		previous.setPreferredSize(new Dimension(50,50));
		previous.addMouseListener(new MouseEventClass(ColorPool.getColor("elemColor")));
		 previous.setFont(FontPool.getFont("normal"));
		 previous.setForeground(ColorPool.getColor("elemColor"));
	}
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
                        .addComponent(previous)
                        .addComponent(panel.getComponent(2))
                    
            );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(panel.getComponent(0))
                            .addComponent(panel.getComponent(1))
                            .addComponent(previous)
                            .addComponent(panel.getComponent(2))
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
