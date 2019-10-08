package com.unsw.tilegame.entities.equipment;

import com.unsw.tilegame.Handler;
import com.unsw.tilegame.tiles.Tile;
import com.unsw.tilegame.tool.Assets;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * bomb
 * @author xiaoyang
 * @version 2.0
 * @since 2.0
 */
public class Bomb extends Equipment{

	/**
	 * @param handler
	 * @param x
	 * @param y
	 */
	public Bomb(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		id = 7;
		count = 3;
		name = "Bomb";
	}
	@Override
	public void render(GraphicsContext g) {
		g.drawImage(Assets.bomb, (int) (x - handler.getGameCamera().getxOffset()), 
				(int) (y - handler.getGameCamera().getyOffset()), width, height);
	}

	@Override
	public void die() {
		this.active = false;
	}

	@Override
	public void pickUP() {
		handler.getPlayer().getInventory().addEquipment(this);
	}
	@Override
	public void tick() {
		if (handler.getPlayer().getCollisionBounds(0f, 0f).getBoundsInParent().intersects(
				getCollisionBounds(0f,0f).getBoundsInParent())){
			pickUP();
			die();
		}
	}

	/**
	 * @return image for bomb
	 */
	public Image getTexture() {
		return Assets.bomb;
	}
}
