package com.unsw.tilegame.entities.effect;

import com.unsw.tilegame.Handler;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

/**
 * effect class
 * @author xiaoyang
 * @version 1.4
 * @since 1.4
 */
public abstract class Effect{
	protected Handler handler;
	protected float x,y;
	protected int width,height;
	protected Rectangle bounds;
	protected boolean active = true;

	/**
	 * @param handler
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Effect(Handler handler, float x, float y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle(0,0,width,height);
	}

	/**
	 * @param g
	 */
	public abstract void render(GraphicsContext g);

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	protected abstract boolean collisionWithTile(int x, int y);
	public float getX() {
		return x;
	}

	/**
	 * @param xOffset
	 * @param yOffset
	 * @return
	 */
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int)(x + bounds.getX() +xOffset),
				(int)(y+bounds.getY()+yOffset),bounds.getWidth(),bounds.getHeight());
	}
	public Handler getHandler() {
		return handler;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public boolean isActive() {
		return active;
	}

	/**
	 * @param active
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
}
