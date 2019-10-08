package com.unsw.tilegame.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * a class connecting resources folder to source folder
 * aims to deal with parsing of files... etc
 * @author xiaoyang
 * @since 1.5
 * @version 1.8
 */
public class Utils {

	/**
	 * @param path
	 * @return buffer line
	 */
	public static String loadFile(String path) {
		StringBuilder builder = new StringBuilder();
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while ((line = br.readLine())!=null) {
				builder.append(line+"\n");
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}

	/**
	 * @param number
	 * @return the number being parsed
	 */
	public static int parseInt(String number) {
		try {
			return Integer.parseInt(number);
		}catch(NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
