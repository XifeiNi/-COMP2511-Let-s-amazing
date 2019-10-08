package com.unsw.tilegame.entities.creatures;

import com.unsw.tilegame.Handler;
import com.unsw.tilegame.entities.Entity;
import com.unsw.tilegame.tiles.Tile;

import javafx.scene.image.Image;
/*
 * @author      Player.java Ni & Yang
 * @version     2.7
 * @since       1.0
 */
public abstract class Creature extends Entity  {
	protected float speed;
	protected float xMove,yMove;

	public abstract Image getCurrentAnimationFrame();

	/**
	 * @param handler
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Creature(Handler handler, float x, float y,int width, int height) {
		super(handler,x,y,width,height);
		speed = 0;
		xMove = 0;
		yMove = 0;
	}
	public void moveX() {
		int tx = calculateTileXMove();
		if (calculateXMoveSituation(tx)) {
			x += xMove;
		} else {
			x=calculateXMoveCollisions(tx);
		}
	}
	public int calculateTileXMove() {
		if (xMove > 0) {
			return (int)(x+xMove+bounds.getX()+bounds.getWidth())/Tile.TILEWIDTH;
		} else {
			return (int)(x+xMove+bounds.getX())/Tile.TILEWIDTH;
		}
	}

	/**
	 *
	 * @param tx
	 * @return
	 */
	public boolean calculateXMoveSituation(int tx) {
		return !collisionWithTile(tx,(int)(y+bounds.getY())/Tile.TILEHEIGHT) &&
				!collisionWithTile(tx,(int)(y+bounds.getY()+bounds.getHeight())/Tile.TILEHEIGHT);
	}
	public float calculateXMoveCollisions(int tx) {
		if (xMove > 0) {
			return (float) (tx*Tile.TILEWIDTH-bounds.getX()-bounds.getWidth()-1);
		} else {
			return (float) (tx*Tile.TILEWIDTH+Tile.TILEWIDTH-bounds.getX());
		}
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean collisionWithTile(int x, int y) {
		return handler.getTile(x, y).isSolid();
	}
	public int calculateTileYMove() {
		if (yMove < 0) {
			return (int)(y+yMove+bounds.getY())/Tile.TILEHEIGHT;
		} else if (yMove > 0) {
			return (int)(y+yMove+bounds.getY()+bounds.getHeight())/Tile.TILEHEIGHT;
		}
		return -1;
	}

	/**
	 * @param ty
	 * @return
	 */
	public boolean calculateYMoveSituation(int ty) {
		return !collisionWithTile((int)(x+bounds.getX())/Tile.TILEWIDTH,ty) &&
				!collisionWithTile((int)(x+bounds.getX()+bounds.getWidth())/Tile.TILEHEIGHT,ty);
	}

	/**
	 * @param ty
	 * @return
	 */
	public float calculateYMoveCollisions(int ty) {
		if (yMove < 0) {
			return (float) (ty*Tile.TILEHEIGHT+Tile.TILEHEIGHT-bounds.getY());
		} else if (yMove > 0){
			return (float) (ty*Tile.TILEHEIGHT-bounds.getY()-bounds.getHeight()-1);
		}
		return -1;
	}

	/**
	 * @param xMove
	 */
	public void setxMove(float xMove) {
		this.xMove = xMove;
	}
	public float getyMove() {
		return yMove;
	}
	public void moveY() {
		int ty = calculateTileYMove();
		if (calculateYMoveSituation(ty)) {
			y += yMove;
		} else {
			y=calculateYMoveCollisions(ty);
		}
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean collisionWithPitTile(int x, int y) {
		return handler.getTile(x, y).isPit();
	}
	public float getxMove() {
		return xMove;
	}

	/**
	 * @param yMove
	 */
	public void setyMove(float yMove) {
		this.yMove = yMove;
	}
	public float getSpeed() {
		return speed;
	}
}
