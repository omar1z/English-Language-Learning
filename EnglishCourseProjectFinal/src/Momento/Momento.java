package Momento;

import java.util.Stack;

import javax.swing.JPanel;

public class Momento {
	private static Stack<JPanel> stack = new Stack();
	
	public static void push(JPanel panel) {
		stack.push(panel);
	}
	public static JPanel pop() {
		return stack.pop();
		}

}
