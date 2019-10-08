package com.unsw.tilegame.entities;

import com.unsw.tilegame.Handler;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

/**
 * abstract class for entity
 * @author xiaoyang
 * @version 2.4
 * @since 1.4
 */
public abstract class Entity {
	protected Handler handler;
	protected float x,y;
	protected int width,height;
	protected Rectangle bounds;
	protected boolean active = true;
	public int id = 0;

	/**
	 * @param handler
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Entity(Handler handler, float x, float y,int width,int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle(0,0,width,height);
	}
	public abstract void tick();
	public abstract void die();
	public abstract void render(GraphicsContext g);

	/**
	 * @param xOffset
	 * @param yOffset
	 * @return whether collides with entity
	 */
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		if (handler.getEntityManager()!= null && handler.getEntities() != null) {
			for(int i = 0; i < handler.getEntities().size();i++) {
				Entity e = handler.getEntities().get(i);
				if (e.equals(this))
					continue;
				if (e.getCollisionBounds(0f, 0f).getBoundsInLocal().intersects(
						getCollisionBounds(xOffset,yOffset).getBoundsInLocal())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @param xOffset
	 * @param yOffset
	 * @return bounds that considers as collision
	 */
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int)(x + bounds.getX() +xOffset),
				(int)(y+bounds.getY()+yOffset),bounds.getWidth(),bounds.getHeight());
	}

	/**
	 * @return x
	 */
	public float getX() {
		return x;
	}

	/**
	 * @return current handler
	 */
	public Handler getHandler() {
		return handler;
	}

	/**
	 * @return bounds of collision
	 */
	public Rectangle getBounds() {
		return bounds;
	}

	/**
	 * @return whether this entity is active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * setter of whether entity is active
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
