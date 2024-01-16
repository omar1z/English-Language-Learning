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

public class AddMarkingDecorator extends HeaderDecorator {
	private JButton marking;
	private JButton restart;
	private JPanel panel;
	public AddMarkingDecorator(Header header) {
		super(header);
		marking = new JButton("marking",new ImageIcon("Images//marking.png"));
		marking.setVerticalTextPosition(JButton.BOTTOM);
		marking.setHorizontalTextPosition(JButton.CENTER);
		marking.setBackground(Color.white);
		marking.setPreferredSize(new Dimension(50,50));
		restart = new JButton("restart",new ImageIcon("Images//restart.png"));
		
		restart.setVerticalTextPosition(JButton.BOTTOM);
		restart.setHorizontalTextPosition(JButton.CENTER);
		restart.setBackground(Color.white);
		restart.setPreferredSize(new Dimension(50,50));
		restart.addMouseListener(new MouseEventClass(ColorPool.getColor("elemColor")));
		marking.addMouseListener(new MouseEventClass(ColorPool.getColor("elemColor")));
		restart.setFont(FontPool.getFont("normal"));
		marking.setFont(FontPool.getFont("normal"));
		restart.setForeground(ColorPool.getColor("elemColor"));
		marking.setForeground(ColorPool.getColor("elemColor"));
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
                        .addGap((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()) - ((int)panel.getComponent(0).getWidth()+
                        		(int)panel.getComponent(1).getSize().getWidth() + (int)marking.getSize().getWidth()+(int)restart.getSize().getWidth()+
                        		(int)panel.getComponent(2).getWidth()+(int)panel.getComponent(3).getWidth() + 750))
                        .addComponent(marking)
                        .addComponent(restart)
                        .addComponent(panel.getComponent(3))
                        .addComponent(panel.getComponent(2))
                       
                        
                    
            );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(panel.getComponent(0))
                            .addComponent(panel.getComponent(1))
                            .addComponent(marking)
                            .addComponent(restart)
                            .addComponent(panel.getComponent(3))
                            .addComponent(panel.getComponent(2))
                            
                        )
                    
            );
        panel.setLayout(layout);
	}

	@Override
	public JPanel getPanel() {
		this.createPanel();
		
		return panel;
	}

}
