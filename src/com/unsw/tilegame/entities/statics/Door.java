package com.unsw.tilegame.entities.statics;

import com.unsw.tilegame.Handler;
import com.unsw.tilegame.entities.creatures.Player;
import com.unsw.tilegame.tiles.Tile;
import com.unsw.tilegame.tool.Assets;

import javafx.scene.canvas.GraphicsContext;

public class Door extends StaticEntity{
	public boolean open = false;
	public Door(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		open = false;
		id = 3;
	}
	
	@Override
	public void die() {
		this.active = true;
	}
	@Override
	public void render(GraphicsContext g) {
		if (open == false) {
			g.drawImage(Assets.door[0], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height);
		} else {
			g.drawImage(Assets.door[1], (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height);
		}
	}
	
	public void move() {
		if (Player.currentWeapon != null) {
			if (Player.currentWeapon.getId() == 6) {
				open = true;
				handler.getPlayer().getInventory().removeEquipment(Player.currentWeapon);
				Player.currentWeapon = null;
			}
		}
	}
}
