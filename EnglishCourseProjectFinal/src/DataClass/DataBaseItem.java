package DataClass;

import java.awt.Color;

import Observer.DataSetChangedObserver;

public interface DataBaseItem {
	public String getTitle();
	public boolean isFinished();
	public Color getBackgroundColor();
	public void setComponentObserver(DataSetChangedObserver observer);

}
