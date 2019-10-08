package com.unsw.tilegame.entities.potion;

import com.unsw.tilegame.Handler;
import com.unsw.tilegame.entities.creatures.Player;
import com.unsw.tilegame.tool.Assets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.scene.canvas.GraphicsContext;

/**
 * hover potion to escape all the pits
 * @author cecilian
 * @version 2.5
 * @since 1.5
 * @see com.unsw.tilegame.entities.potion.Potion
 * @see com.unsw.tilegame.entities.equipment.Equipment \
 */
public class Hover extends Potion {
	public boolean isInUse = false;
	ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

	/**
	 * @param handler
	 * @param x
	 * @param y
	 */
	public Hover(Handler handler, float x, float y) {
		super(handler, x, y, 50, 60);
		id = 101;
	}

	/**
	 * display.....
	 * @param g
	 */
	@Override
	public void render(GraphicsContext g){
		g.drawImage(Assets.hover, (int) (x - handler.getGameCamera().getxOffset()), 
				(int) (y - handler.getGameCamera().getyOffset()), width, height);
	}

	@Override
	public void pickUP() {
		Player.setOnHover(true);
	}

	/**
	 * the running process of this potion
	 */
	@Override
	public void tick() {
		if (handler.getPlayer().getCollisionBounds(0f, 0f).getBoundsInLocal().intersects(
				getCollisionBounds(0f,0f).getBoundsInLocal())){
			pickUP();
			die();
		}
	}
}