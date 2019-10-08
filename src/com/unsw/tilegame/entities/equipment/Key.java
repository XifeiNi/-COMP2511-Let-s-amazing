package com.unsw.tilegame.entities.equipment;

import com.unsw.tilegame.Handler;
import com.unsw.tilegame.tool.Assets;
import com.unsw.tilegame.tiles.Tile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * key class
 * @author cecilian && xiaoyang
 *
 */
public class Key extends Equipment{

	/**
	 * @param handler
	 * @param x
	 * @param y
	 */
	public Key(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		id = 6;
		count = 1;
		name = "Key";
	}

	/**
	 * @param g
	 */
	@Override
	public void render(GraphicsContext g) {
		g.drawImage(Assets.key, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height);
	}

	@Override
	public void die() {
		this.active = false;
	}

	@Override
	public void pickUP() {
		handler.getWorld().getEntityManager().getPlayer().getInventory().addEquipment(this);
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
	 * @return image of this ....
	 */
	public Image getTexture() {
		return Assets.key;
	}
}
