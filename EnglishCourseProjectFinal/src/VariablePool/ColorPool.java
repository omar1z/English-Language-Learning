package VariablePool;
import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;

public class ColorPool {
	private static HashMap<String,Color> colorPool = new HashMap<>();
	
	static {
		colorPool.put("elemColor", new Color(235,34,39));
		colorPool.put("itermColor", new Color(109,178,0));
		colorPool.put("advColor", new Color(95,74,204));
		colorPool.put("dBlue", new Color(45,25,85));
		colorPool.put("lBlue", new Color(21,160,255));
		colorPool.put("lPink", new Color(253, 232, 234));
		colorPool.put("dPink", new Color(240, 191, 196));
		colorPool.put("gray", new Color(242, 242, 242));
		colorPool.put("dGreen",  new Color(109,178,0));
		colorPool.put("lGreen",new Color(210, 227, 185));
		colorPool.put("dAdv", new Color(207, 202, 233));
		colorPool.put("lAdv", new Color(229, 228, 237));
		colorPool.put("foreGround",new Color(108, 115, 61));
		colorPool.put("instructionColor", new Color(240, 191, 196));
	}
	public static Color getColor(String colorName) {
		return colorPool.get(colorName);
		
	}
	public static void addColor(String name, Color color) {
		colorPool.put(name, color);
	}

}
