package PanelPackage;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Imagepan extends JPanel {
    private ImageIcon backgroundImage;

    public Imagepan() {
            backgroundImage = new ImageIcon("Images\\image.jpg");
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}