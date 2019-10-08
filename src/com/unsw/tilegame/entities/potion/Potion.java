package com.unsw.tilegame.entities.potion;
import com.unsw.tilegame.Handler;
import com.unsw.tilegame.entities.equipment.Equipment;

/**
 * @author cecilian
 * @version 1.5
 * @since 1.5
 */
public abstract class Potion extends Equipment{

	/**
	 * @param handler
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Potion(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, 50, 60);
	}
	public void die(){
		this.active = false;
	}
}