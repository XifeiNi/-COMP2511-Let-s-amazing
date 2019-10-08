package com.unsw.tilegame.entities.equipment;

import com.unsw.tilegame.Handler;
import com.unsw.tilegame.tool.Assets;
import com.unsw.tilegame.tiles.Tile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * sword class
 * @author cecilian && xiaoyang
 * @version 2.4
 * @since 1.5
 * @see com.unsw.tilegame.entities.equipment.Equipment
 */
public class Sword extends Equipment{
	/**
	 * @param handler
	 * @param x
	 * @param y
	 */
	public Sword(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		id = 2;
		count = 1;
		name = "Sword";
	}

	/**
	 * @param g
	 */
	@Override
	public void render(GraphicsContext g) {
		g.drawImage(Assets.sword, (int) (x - handler.getGameCamera().getxOffset()), 
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
		if (handler.getPlayer().getCollisionBounds(0f, 0f).getBoundsInLocal().intersects(
				getCollisionBounds(0f,0f).getBoundsInLocal())){
			pickUP();
			die();
		}
	}

	/**
	 * @return image of this sword
	 */
	public Image getTexture() {
		return Assets.sword;
	}

}
