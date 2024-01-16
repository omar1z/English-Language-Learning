package ExercisesPanel;

import java.awt.*;

import javax.swing.*;
// this class represent each View in our application
// each View Contain header and content panel
public class MainPanel<T extends JPanel> extends JPanel{
	private JPanel headerPanel;
	private T contentPanel;
	private JPanel containerPanel;
	public MainPanel(JPanel headerPanel, T contentPanel) {
		this.contentPanel = contentPanel;
		this.headerPanel = headerPanel;
		JScrollPane s=new JScrollPane(contentPanel);
		this.setLayout(new BorderLayout());
		this.add(headerPanel,BorderLayout.NORTH);
		this.add(s,BorderLayout.CENTER);
		
		
	}
	public MainPanel(JPanel headerPanel, T contentPanel,JLabel label) {
		this.contentPanel = contentPanel;
		this.headerPanel = headerPanel;
		containerPanel = new JPanel(new BorderLayout());
		containerPanel.add(label,BorderLayout.NORTH);
		JScrollPane s=new JScrollPane(contentPanel);
		containerPanel.add(s,BorderLayout.CENTER);
		this.setLayout(new BorderLayout());
		this.add(headerPanel,BorderLayout.NORTH);
		this.add(containerPanel,BorderLayout.CENTER);
		
		
	}
	public JPanel getHeaderPanel() {
		return headerPanel;
	}
	public T getContentPanel() {
		return contentPanel;
	}

}
