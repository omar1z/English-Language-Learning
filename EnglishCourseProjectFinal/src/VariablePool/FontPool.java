package VariablePool;

import java.awt.Font;
import java.util.HashMap;

public class FontPool {
	private static HashMap<String,Font> fontPool = new HashMap<>();
	static {
		fontPool.put("normal",new Font("Segoe UI", Font.PLAIN+Font.ITALIC, 16));
		fontPool.put("bold", new Font("Segoe UI", Font.PLAIN+Font.BOLD, 24));
		fontPool.put("thin", new Font("Segoe UI", Font.PLAIN, 16));
		fontPool.put("level", new Font("Arial", Font.BOLD, 24));
	}
	public static Font getFont(String fontName) {
		return fontPool.get(fontName);
	}

}
