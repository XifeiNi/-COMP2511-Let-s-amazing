package com.unsw.tilegame.entities.statics;

import com.unsw.tilegame.Handler;
import com.unsw.tilegame.entities.Entity;

public abstract class StaticEntity extends Entity {
	public StaticEntity(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		this.id = 2;
		bounds.setX(10);
		bounds.setY((int)(height/1.5f));
		bounds.setWidth(width-20);;
		bounds.setHeight((int)(height-height/1.5f));
	}
	public abstract void die();
	public void tick() {
		
	}
}