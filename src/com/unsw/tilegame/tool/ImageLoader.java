package com.unsw.tilegame.tool;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

/**
 * class that loads image resources
 * @author xiaoyang
 * @version 1.7
 * @since 1.3
 */
public class ImageLoader {
	public static Image loadImage(String path) {
		try {
			BufferedImage  bf =  ImageIO.read(ImageLoader.class.getResource(path));
			return SwingFXUtils.toFXImage(bf,null);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}
