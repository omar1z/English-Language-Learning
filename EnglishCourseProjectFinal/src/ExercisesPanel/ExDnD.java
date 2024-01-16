package ExercisesPanel;
import javax.swing.* ;

import VariablePool.FontPool;

//import displayLevels.FontPool;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Flow;
import java.util.regex.Pattern;
import java.awt.* ;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExDnD extends JPanel {
    JPanel internal;
    JPanel external;
    JPanel linePanels;
    boolean booly = false;
    JLabel choose;
    String dash = " -------- ";
    FontPool fp = new FontPool();
    int k =0;
    String[] choices;
    ArrayList<JLabel> arrayChoice = new ArrayList<>();

    public ExDnD(String[] lines, String[] choices) {
    	internal = new JPanel();
    	external = new JPanel();
    	 linePanels = new JPanel();
    	 choose = new JLabel();
        this.choices = choices;
        internal.setLayout(new FlowLayout(FlowLayout.LEFT));
        linePanels.setLayout(new BoxLayout(linePanels, BoxLayout.Y_AXIS));

        for (int i = 0; i < lines.length; i++) {
            JPanel ppp = new JPanel(new FlowLayout(FlowLayout.LEFT));
            ppp.setBackground(Color.white);
            String line1 = lines[i];
            if(line1.contains("*")){
                String[] parts = line1.split("\\*");
                JLabel first = new JLabel(parts[0]);
                JLabel second = new JLabel(parts[1]);
                JLabel third = new JLabel(dash);
                third.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
                arrayChoice.add(third);
                third.setPreferredSize(new Dimension(100,40));
                first.setFont(fp.getFont("normal"));
                second.setFont(fp.getFont("normal"));
                third.setFont(fp.getFont("normal"));
                third.addMouseListener(new MyMouseListener(third));
                ppp.add(first);
                ppp.add(third);
                ppp.add(second);
                linePanels.add(ppp);
            }
            else{
            	
                JLabel nothing = new JLabel(line1);
                nothing.setFont(fp.getFont("normal"));
                ppp.add(nothing);
                linePanels.add(ppp);
            }
        }

        external.setLayout(new FlowLayout(FlowLayout.CENTER)); 
        //internal.add(linePanels,FlowLayout.LEFT);

        
        for (int i = 1; i < choices.length; i++) {
            
            String choice = choices[i];
            JButton choicebutton = new JButton(choice);
            choicebutton.setBackground(Color.white);
            choicebutton.setFont(FontPool.getFont("normal"));
            external.add(choicebutton);
            choicebutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    booly = true;
                    choose.setText(choice);
                    //choicebutton.setEnabled(false);
                }
            });
        }

        this.setLayout(new BorderLayout());
        linePanels.setBackground(Color.white);
        external.setBackground(Color.white);
        this.add(linePanels, BorderLayout.CENTER);
        this.add(external, BorderLayout.NORTH);
        Dimension sc = Toolkit.getDefaultToolkit().getScreenSize();
        int w = (int) sc.getWidth();
        int h = (int) sc.getHeight();
        this.setSize(w, h);
       // this.setVisible(true);
    }

    private class MyMouseListener extends MouseAdapter {
        private JLabel source;

        public MyMouseListener(JLabel source) {
            this.source = source;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel source = (JLabel) e.getSource();
            String a = source.getText();
            if (booly) {
                JLabel third = new JLabel(choose.getText());
                source.setText(third.getText());
                booly = false;
            }
        }
    }

    public ArrayList<JLabel> getUserAwnser(){
    	return arrayChoice;
    }
    
}
