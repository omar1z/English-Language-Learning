package HomeLevels;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

import VariablePool.ColorPool;
import VariablePool.FontPool;
import VariablePool.MouseEventClass;
public class HeaderLevelsPanel extends JPanel{
	private JButton progress;
	private JButton signOut;
	
	public HeaderLevelsPanel() {
		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		ImageIcon i = new ImageIcon("Images/pWhite1.png");
		progress = new JButton("Progress", i);
		progress.setHorizontalTextPosition(JButton.CENTER);
		progress.setVerticalTextPosition(JButton.BOTTOM);
		progress.setBackground(Color.WHITE);
		progress.setForeground(ColorPool.getColor("elemColor"));
		progress.setFont(FontPool.getFont("normal"));
		this.setBackground(Color.WHITE);
		this.setBorder(new LineBorder(Color.gray,1));
		progress.addMouseListener(new MouseEventClass(ColorPool.getColor("elemColor")));
		
		ImageIcon sign = new ImageIcon("Images/signOut.png");
		signOut = new JButton("Sign Out", sign);
		signOut.setHorizontalTextPosition(JButton.CENTER);
		signOut.setVerticalTextPosition(JButton.BOTTOM);
		signOut.setBackground(Color.WHITE);
		signOut.setFont(FontPool.getFont("normal"));
		signOut.setForeground(ColorPool.getColor("elemColor"));
		signOut.addMouseListener(new MouseEventClass(ColorPool.getColor("elemColor")));
		this.setBorder(BorderFactory.createEmptyBorder());
		this.add(progress);
		this.add(signOut);
		
		
	}
	public JButton getProgressButton() {
		return progress;
	}
	public JButton getSignOutButton() {
		return signOut;
	}

}
