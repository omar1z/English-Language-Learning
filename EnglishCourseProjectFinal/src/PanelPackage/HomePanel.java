package PanelPackage;

import java.awt.*;
import javax.swing.*;

public class HomePanel extends JPanel {
    JButton i = new JButton();
    ImageIcon I = new ImageIcon();

    public HomePanel() {
        this.setBackground(Color.WHITE);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        I = new ImageIcon("Images\\icons8-home-50.png");
        i = new JButton(I);
        i.setText("HOME");
        i.setHorizontalTextPosition(JButton.CENTER);
        i.setVerticalTextPosition(JButton.BOTTOM);
        i.setBackground(Color.WHITE);
        i.setFocusable(false);
        this.add(i);
    }

    public JButton getHomePanel(){
        return i;
    }
}