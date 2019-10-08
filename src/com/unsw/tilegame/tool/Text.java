package com.unsw.tilegame.tool;

import com.sun.javafx.tk.FontMetrics;
import com.sun.javafx.tk.Toolkit;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Text class that creates all fancy text
 * @author xiaoyang
 * @since 2.1
 * @version 2.1
 */
public class Text {
	private String text;
	private Font font = Font.getDefault();
	private double fontSize = Font.getDefault().getSize();
	private Color color = Color.BLACK;

	/**
	 * @param g -> graph
	 * @param text
	 * @param xPos
	 * @param yPos
	 * @param center
	 * @param c -> color
	 * @param font
	 */
	public static void drawString(GraphicsContext g, String text, int xPos, int yPos, boolean center, Color c, Font font) {
		g.save();
		g.setFont(font);
		g.setFill(c);
		double x = xPos;
		double y = yPos;
		if (center) {
			if (text != null) {
				g.fillText(text, x, y);
			}
		}
		g.restore();
	}


	/**
	 * @return the height of the line
	 */
	public double getHeight() {
		FontMetrics fm = Toolkit.getToolkit().getFontLoader().getFontMetrics(font);
		return fm.getLineHeight();
	}

}
