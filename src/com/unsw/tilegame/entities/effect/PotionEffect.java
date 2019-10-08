package com.unsw.tilegame.entities.effect;

import com.unsw.tilegame.Handler;
import com.unsw.tilegame.entities.creatures.Player;
import com.unsw.tilegame.tiles.Tile;

import javafx.scene.canvas.GraphicsContext;

/**
 * the effect of potion
 * @author xiaoyang
 * @version 2.3
 * @see java.lang.Runnable
 */
public class PotionEffect extends Effect implements Runnable{

	/**
	 * @param handler
	 * @param x
	 * @param y
	 */
	public PotionEffect(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	@Override
	protected boolean collisionWithTile(int x, int y) {
		return false;
	}

	/**
	 * @param g
	 */
	@Override
	public void render(GraphicsContext g) {
		return;
	}
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(8000);
			} catch (Exception e){
				e.printStackTrace();
			}
			Player.setOnInvincible(false);
			break;
		}
	}
}
