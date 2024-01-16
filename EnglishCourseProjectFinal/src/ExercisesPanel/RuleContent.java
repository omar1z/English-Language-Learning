package ExercisesPanel;

import javax.swing.*;

import Container.*;
import VariablePool.ColorPool;
import VariablePool.FontPool;

import java.awt.*;
//import java.util.Iterator;

public class RuleContent extends JPanel {

    public RuleContent(MyHashMap<String,String> ruleContent) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel[] title = new JLabel[ruleContent.size()];
        JLabel[] content = new JLabel[ruleContent.size()];
        MyIterator<MyHashMap.Entry<String, String>> iterator = ruleContent.getIterator();
        int i = 0;

        while(iterator.hasNext()) {
            MyHashMap.Entry<String,String> entry = iterator.next();

     
            JPanel titlePanel = new JPanel();
            JPanel contentPanel = new JPanel(new BorderLayout());

            title[i] = new JLabel(entry.getKey());
            title[i].setOpaque(true);
            title[i].setBackground(Color.WHITE);
            title[i].setForeground(ColorPool.getColor("dBlue"));
            titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
            titlePanel.add(title[i]);
            JSeparator s = new JSeparator();
            s.setForeground(Color.red);
            titlePanel.add(s);
            content[i] = new JLabel(entry.getValue());
            content[i].setOpaque(true);
            content[i].setBackground(Color.WHITE);
            content[i].setForeground(ColorPool.getColor("lBlue")); 
            content[i].setFont(FontPool.getFont("thin"));

            contentPanel.add(content[i], BorderLayout.CENTER);

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(titlePanel,BorderLayout.NORTH);
            panel.add(contentPanel,BorderLayout.CENTER);
            this.add(panel);
            i++;
        }
    }

}
