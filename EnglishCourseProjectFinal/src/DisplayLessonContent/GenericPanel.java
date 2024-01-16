package DisplayLessonContent;
import java.awt.*;




import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import DataClass.DataBaseItem;
import DataClass.Rule;
import Observer.DataSetChangedObserver;
import VariablePool.ColorPool;
import VariablePool.FontPool;
import VariablePool.MouseEventClass;

import java.util.*;



// this class represent each databaseItem in Buttons in the same way the only change is title and color of Button
public class GenericPanel<T extends DataBaseItem> extends JPanel {
	private ArrayList<T> list;
	private ArrayList<JButton> buttonList = new ArrayList<>();
	public GenericPanel(ArrayList<T> list) {
		this.list = list;
		this.setLayout(new GridLayout(list.size()+1,1));
		this.setBackground(Color.white);
		for(T item : list) {
			JButton button = new JButton(item.getTitle());
			DataSetChangedObserver observer = new DataSetChangedObserver();
			observer.addSubsicriber(button);
			item.setComponentObserver(observer);
			button.setForeground(Color.black);
			button.setBackground(item.getBackgroundColor());
			int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
			int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
			button.setPreferredSize(new Dimension((screenWidth/2 -50),50));
			button.setFont(FontPool.getFont("normal"));
			button.addMouseListener(new MouseHilightListener(Color.white));
			this.add(button);
			this.setBackground(Color.white);
			buttonList.add(button);
		}
	}
	protected class MouseHilightListener extends MouseEventClass{
		
		public MouseHilightListener(Color background) {
			super(background);
		}
		public void mouseEntered(MouseEvent e) {
			JButton b = (JButton) e.getSource();
			b.setBackground(Color.white);
			b.setForeground(ColorPool.getColor("foreGround"));
		}
		public void mouseExited(MouseEvent e) {
		JButton b = (JButton) e.getSource();
		String s = b.getText();
		DataBaseItem i = list.stream().filter(l -> l.getTitle().equals(b.getText()))
				.findFirst()
				.orElse(null);
		b.setBackground(i.getBackgroundColor());
		b.setForeground(Color.black);
		}
		public void mousePressed(MouseEvent e) {
			JButton b = (JButton) e.getSource();
			DataBaseItem i = list.stream().filter(l -> l.getTitle().equals(b.getText()))
					.findFirst()
					.orElse(null);
			b.setBackground(i.getBackgroundColor());
			b.setForeground(Color.black);
			b.setBackground(i.getBackgroundColor());
			b.setForeground(Color.black);
		}
		
	}
	public ArrayList<JButton> getButtonList(){
		return buttonList;
	}
	public ArrayList<T> getData(){
		return list;
	}
}




