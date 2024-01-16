package Observer;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JComponent;

public class DataSetChangedObserver {
	 private ArrayList<JComponent> subscribers = new ArrayList<JComponent>();
	 public void addSubsicriber(JComponent c) {
		 subscribers.add(c);
	 }
	 public void deleteSubscriber(JComponent c) {
		 subscribers.remove(c);
	 }
	 public void  notifyDataSetChanged( Color color) {
		 for(JComponent comp : subscribers) {
			 comp.setBackground(color);
		 }
	 }
}
