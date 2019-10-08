package com.unsw.tilegame.entities.potion;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.unsw.tilegame.Handler;
import com.unsw.tilegame.entities.creatures.Player;
import com.unsw.tilegame.entities.effect.PotionEffect;
import com.unsw.tilegame.tool.Assets;
import javafx.scene.canvas.GraphicsContext;

/**
 * @author cecilian
 * @version 2.5
 * @since 1.5
 * @see com.unsw.tilegame.entities.equipment.Equipment
 * @see com.unsw.tilegame.entities.potion.Potion
 */
public class Invincible extends Potion {
	ExecutorService cachedThreadPool;

	/**
	 * @param handler
	 * @param x
	 * @param y
	 */
	public Invincible(Handler handler, float x, float y) {
		super(handler, x, y, 50, 60);
		id = 100;
		cachedThreadPool = Executors.newCachedThreadPool();
	}

	/**
	 * @param g
	 */
	@Override
	public void render(GraphicsContext g) {
		g.drawImage(Assets.invincible, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height);

	}

	/**
	 * pick up the potion....
	 */
	/*@Override
	public void die() {
		this.active = false;
	}*/
	@Override
	public void pickUP() {
		Player.setOnInvincible(true);
		PotionEffect potionEffect = new PotionEffect(handler,x,y);
		cachedThreadPool.execute(potionEffect); 
	}

	/**
	 * enemy dies when encountering it
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