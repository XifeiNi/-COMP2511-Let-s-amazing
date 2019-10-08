package com.unsw.tilegame.tool;

import javafx.scene.image.Image;

/**
 * reference to 	//a href<https://github.com/CodeNMore/New-Beginner-Java-Game-Programming-Src/blob/master/Episode%2034/TileGame/src/dev/codenmore/tilegame/gfx/Animation.java>
 *     create animation of player/monster movement
 * @author xiaoyang
 * @version 1.5
 * @since 1.2
 */
public class Animation{
	//a href<https://github.com/CodeNMore/New-Beginner-Java-Game-Programming-Src/blob/master/Episode%2034/TileGame/src/dev/codenmore/tilegame/gfx/Animation.java>
	private int speed, index;
	private Image[] frames;
	private long lastTime,timer;

	/**
	 * @param speed
	 * @param frames
	 */
	public Animation(int speed, Image[] frames) {
		this.speed = speed;
		this.frames = frames;
		index = 0;
		lastTime = System.currentTimeMillis();
	}

	/**
	 * time frame of animation
	 */
	public void tick() {
		timer += System.currentTimeMillis()-lastTime;
		lastTime = System.currentTimeMillis();
		if(timer > speed) {
			index++;
			timer = 0;
			if (index >= frames.length) {
				index = 0;
			}
		}
	}

	/**
	 * @return current time frame
	 */
	public Image getCurrentFrame() {
		return frames[index];
	}

	/**
	 * @return current time frame index
	 */
	public int frameIndex() {
		return index;
	}
}
