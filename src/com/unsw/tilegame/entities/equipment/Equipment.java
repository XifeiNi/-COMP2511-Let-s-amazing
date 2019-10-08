package com.unsw.tilegame.entities.equipment;

import com.unsw.tilegame.Handler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

/**
 * equipment class that abstrqcts all equipment
 */
public abstract class Equipment {
	private static final int DEFAULT_ATTACK = 10;
	private int attack;
	protected Handler handler;
	protected float x,y;
	protected int width,height;
	protected Rectangle bounds;
	protected boolean active = true;
	protected int id = 0;
	protected int count = 0;
	protected String name;
	protected Image texture;

	/**
	 * @param handler
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Equipment(Handler handler, float x, float y, int width, int height) {
		this.setAttack(DEFAULT_ATTACK);
		this.handler = handler;
		this.name = "Equipment";
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		bounds = new Rectangle(0,0,width,height);
		bounds.setX(10);
		bounds.setY(10);
		bounds.setHeight(50);
		bounds.setWidth(50);
	}
	public abstract void tick();
	public abstract void render(GraphicsContext g);
	public abstract void die();
	public abstract void pickUP();

	/**
	 * @param attack
	 */
	public void setAttack(int attack) {
		this.attack = attack;
	}

	/**
	 * @return  current handler
	 */
	public Handler getHandler() {
		return this.handler;
	}

	/**
	 * @return id of equipment
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return number of equipments
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return name of equipment
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return picture of the equipment
	 */
	public Image getTexture() {
		return texture;
	}

	/**
	 * @return whether the equipment is still active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param handler
	 */
	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	/**
	 * @param xOffset
	 * @param yOffset
	 * @return bounds that will be considered as a collision
	 */
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int)(x + bounds.getX() +xOffset),
				(int)(y+bounds.getY()+yOffset),bounds.getWidth(),bounds.getHeight());
	}

	/**
	 * @param x
	 * @param y
	 */
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
		bounds.setX(x);
		bounds.setY(y);
	}
}
