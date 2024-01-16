package Factory;

import javax.swing.JFrame;

public abstract class FrameFactory {
	private JFrame frame;
	public FrameFactory(JFrame frame) {
		this.frame = frame;
		
	}
	public void displayFrame() {
		frameFactore();
	}
	protected abstract void frameFactore();

}
