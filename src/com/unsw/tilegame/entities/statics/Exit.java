package com.unsw.tilegame.entities.statics;

import com.unsw.tilegame.Handler;
import com.unsw.tilegame.tiles.Tile;
import com.unsw.tilegame.tool.Assets;

import javafx.scene.canvas.GraphicsContext;

public class Exit extends StaticEntity {
	public static final int EXIT_ID = 20;
	public Exit(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		id  = EXIT_ID;
	}

	@Override
	public void die() {
		this.active = true;
		
	}
	@Override
	public void render(GraphicsContext g) {
		g.drawImage(Assets.exit, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height);
	}

}
