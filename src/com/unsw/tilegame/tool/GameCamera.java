package com.unsw.tilegame.tool;

import com.unsw.tilegame.Handler;
import com.unsw.tilegame.entities.Entity;
import com.unsw.tilegame.tiles.Tile;

/**
 * reference from 	//a href<https://github.com/CodeNMore/New-Beginner-Java-Game-Programming-Src/blob/master/Episode%2034/TileGame/src/dev/codenmore/tilegame/gfx/GameCamera.java>
 * game camera that changes as player moves
 * @author xiaoyang
 * @version 1.3
 * @since 1.1
 */
public class GameCamera {
	private Handler handler;
	private float xOffset, yOffset;

	/**
	 * @param handler
	 * @param xOffset
	 * @param yOffset
	 */
	public GameCamera(Handler handler, float xOffset, float yOffset){
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	/**
	 * setting offset
	 * centering on entity
	 */
	public void checkBlankSpace() {
		if (xOffset < 0) {
			xOffset = 0;
		} else if (xOffset > handler.getWorld().getWidth()*Tile.TILEWIDTH-handler.getWidth()) {
			xOffset = handler.getWorld().getWidth()*Tile.TILEWIDTH-handler.getWidth();
		}
		if (yOffset < 0) {
			yOffset = 0;
		} else if (yOffset > handler.getWorld().getHeight()*Tile.TILEHEIGHT-handler.getHeight()) {
			yOffset = handler.getWorld().getHeight()*Tile.TILEHEIGHT-handler.getHeight();
		}
	}

	/**
	 * @param e entity
	 */
	public void centerOnEntity(Entity e){
		xOffset = e.getX() - handler.getWidth() / 2 + e.getWidth() / 2;
		yOffset = e.getY() - handler.getHeight() / 2 + e.getHeight() / 2;
		checkBlankSpace();
	}

	/**
	 * @return offset of x coordinate
	 */
	public float getxOffset() {
		return xOffset;
	}

	/**
	 * @return offset of y coordinate
	 */
	public float getyOffset() {
		return yOffset;
	}
}

