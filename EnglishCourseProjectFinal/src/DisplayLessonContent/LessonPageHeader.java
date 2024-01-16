package DisplayLessonContent;
import java.awt.*;
import javax.swing.*;

import VariablePool.ColorPool;
import VariablePool.MouseEventClass;

public class LessonPageHeader extends JPanel {
    JButton i = new JButton();
    ImageIcon I = new ImageIcon();

    public LessonPageHeader() {
        this.setBackground(Color.WHITE);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        I = new ImageIcon("Images//icons8-home-50.png");
        i = new JButton(I);
        i.setText("HOME");
        i.setHorizontalTextPosition(JButton.CENTER);
        i.setVerticalTextPosition(JButton.BOTTOM);
        i.setBackground(Color.WHITE);
        i.setFocusable(false);
        i.addMouseListener(new MouseEventClass(ColorPool.getColor("elemColor")));
        this.add(i);
    }
    
    public JButton getHomeButton() {
    	return i;
    }
}