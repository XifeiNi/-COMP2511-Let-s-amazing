package com.unsw.tilegame.entities.enemy;

import com.unsw.tilegame.Handler;
import com.unsw.tilegame.entities.creatures.Player;
import com.unsw.tilegame.tool.Animation;
import com.unsw.tilegame.tool.Assets;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * strategist class
 * @author cecilian
 * @version 2.5
 * @since 2.3
 */
public class Strategist extends Enemy implements Runnable{
	private static final float DEFAULT_SPEED =3;
	private float xOriginal = 0, yOriginal = 0;
	private StrategistMoveAlgorithm howToMove;

	/**
	 * @param handler
	 * @param x
	 * @param y
	 */
	public Strategist(Handler handler, float x, float y) {
		super(handler, x, y, 65, 100);
		bounds.setX(15);
		bounds.setY(15);
		bounds.setWidth(40);
		bounds.setHeight(80);
		speed = DEFAULT_SPEED;
		howToMove = new StrategistMoveAlgorithm(this);
		health = 100;
		xMove = 0;
		yMove = 0;
		xOriginal = x;
		yOriginal = y;
		animDown = new Animation(500,Assets.stratigist_down);
		animUp = new Animation(500,Assets.stratigist_up);
		animRight = new Animation(500, Assets.stratigist_right);
		animLeft = new Animation(500,Assets.stratigist_left);
	}

	@Override
	public void tick() {
		
	}

	/**
	 * @param g
	 */
	@Override
	public void render(GraphicsContext g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height);
	}

	/**
	 * @return current animation frame
	 */
	@Override
	public Image getCurrentAnimationFrame() {
		if (xMove < 0) {
			return animLeft.getCurrentFrame();
		} else if(xMove > 0) {
			return animRight.getCurrentFrame();
		} else if (yMove < 0) {
			return animUp.getCurrentFrame();
		} else  {
			return animDown.getCurrentFrame();
		}
	}
	


	
	@Override
	public void die() {
		enemyDie.die();
	}

	@Override
	public void hurt(int amt) {
		enemyHurt.hurt(amt);
	}
	@Override
	public void run() {
		if (!Player.getInvincible()){
			howToMove.Move(xMove,yMove);
		} else {
			playerInv.Move(xMove,yMove);
		}
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
	}

	public float getxOriginal(){
		return xOriginal;
	}

	public float getyOriginal(){
		return yOriginal;
	}

}
