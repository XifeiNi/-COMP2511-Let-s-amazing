package com.unsw.tilegame.tool;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

/**
 * @author xiaoyang
 * @version 2.5
 * @since 1.9
 * class for partitioning the image to scenes of each animation
 */
public class SpriteSheet {
	private Image sheet;

	/**
	 * @param sheet
	 */
	public SpriteSheet(Image sheet) {
		this.sheet = sheet;
	}

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return the writable image object
	 * that has the image being cropped
	 */
	public Image crop(int x, int y, int width, int height) {
		return new WritableImage(sheet.getPixelReader(), x, y, width, height);
	}
}


