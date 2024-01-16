package PanelPackage;
import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;

import Container.MyHashMap;
import Container.MyIterator;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

public class ProgressBar extends JPanel {
    JPanel jp ;
    private JProgressBar progressBars;
    private JLabel labels;
    MyHashMap<String,Integer> hashi;
    Color color = Color.red;

    ProgressBar(MyHashMap<String,Integer> hashi,Color c) {
        this.hashi = hashi;
        this.color = c;
        jp = new JPanel(new BorderLayout());
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        // execute later from database
        progressBars = new JProgressBar();
        labels = new JLabel();
        MyIterator<MyHashMap.Entry<String, Integer>> it = this.hashi.getIterator();
        while (it.hasNext()) {
        	JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        	MyHashMap.Entry<String, Integer> entry = it.next();
            String key = entry.getKey();
            Integer value = entry.getValue();
            
            labels = new JLabel();
            labels.setText(key);
            labels.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));
            labels.setForeground(c);
            JLabel mark = new JLabel(value+" %");
            mark.setBackground(Color.white);
            mark.setForeground(c);
            mark.setOpaque(true);
            mark.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));
            progressBars = new JProgressBar(0, 100);
            progressBars.setStringPainted(true); // Display percentage string
            int width =(int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - labels.getWidth() - mark.getWidth() - 400; 
            progressBars.setPreferredSize(new Dimension(width, 30)); // Set preferred size
            progressBars.setValue(value);
            progressBars.setUI(new CustomProgressBarUI());
            p.add(labels);
            p.add(progressBars);
            p.add(mark);
            p.setBackground(Color.WHITE);
            this.add(p);
        }

//        jp.setBackground(Color.WHITE);
//        jp.setVisible(true);
       // this.add(jp);
       this.setVisible(true);
    }


     class CustomProgressBarUI extends BasicProgressBarUI {
        @Override
        protected void paintDeterminate(Graphics g, JComponent c) {
            Graphics2D g2d = (Graphics2D) g;
            Insets b = progressBar.getInsets(); // BorderInsets

            // Calculate the width of the filled portion
            int amountFull = (int) Math.round(progressBar.getPercentComplete() * (progressBar.getWidth() - b.right - b.left));
            
            // Set a different color for the filled portion
            g2d.setColor(color); // Change this color as needed

            // Fill the progress bar
            if (progressBar.getOrientation() == JProgressBar.HORIZONTAL) {
                g2d.fillRect(b.left, b.top, amountFull, progressBar.getHeight() - b.top - b.bottom);
            } else { // VERTICAL
                g2d.fillRect(b.left, progressBar.getHeight() - b.bottom - amountFull, progressBar.getWidth() - b.left - b.right, amountFull);
            }
        }
    }
}