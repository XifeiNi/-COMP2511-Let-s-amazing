package com.unsw.tilegame.entities.equipment;

import com.unsw.tilegame.Handler;
import com.unsw.tilegame.entities.creatures.Player;
import com.unsw.tilegame.tool.Assets;
import com.unsw.tilegame.tiles.Tile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * gold/treasure
 * @author xiaoyang
 * @version 2.0
 * @since 1.4
 * @see com.unsw.tilegame.entities.equipment.Equipment
 */
public class Gold extends Equipment {
	/**
	 * @param handler
	 * @param x
	 * @param y
	 */
	public Gold(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		id = 5;
		count = 5;
		name = "Gold";
	}

	/**
	 * @param g
	 */
	@Override
	public void render(GraphicsContext g) {
		g.drawImage(Assets.gold, (int) (x - handler.getGameCamera().getxOffset()), 
				(int) (y - handler.getGameCamera().getyOffset()), width, height);
	}

	@Override
	public void die() {
		this.active = false;
	}

	@Override
	public void pickUP() {
		Player.setTreasure(Player.getTreasure()+count);
		handler.getWorld().getEntityManager().getPlayer().getInventory().addEquipment(this);
	}
	@Override
	public void tick() {
		if (handler.getPlayer().getCollisionBounds(0f, 0f).getBoundsInLocal().intersects(
				getCollisionBounds(0f,0f).getBoundsInLocal())){
			pickUP();
			die();
		}
	}
	public Image getTexture() {
		return Assets.gold;
	}

}
