package com.unsw.tilegame.tool;


import java.io.IOException;
import java.io.InputStream;

import javafx.scene.text.Font;

/**
 * loader of font
 * @author xiaoyang
 * @version 2.1
 * @since 1.9
 */
public class FontLoader {

	/**
	 * @param path
	 * @param size
	 * @return font that it aims to load
	 * @throws IOException
	 */
	public static Font loadFont(String path,float size) throws IOException {
		InputStream is = FontLoader.class.getResourceAsStream(path);
		return Font.loadFont(is, size);
	}
}
