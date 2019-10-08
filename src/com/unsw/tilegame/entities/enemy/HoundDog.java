package com.unsw.tilegame.entities.enemy;

import java.util.Random;

import com.unsw.tilegame.Handler;
import com.unsw.tilegame.entities.creatures.Player;
import com.unsw.tilegame.tiles.Tile;
import com.unsw.tilegame.tool.Animation;
import com.unsw.tilegame.tool.Assets;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * hound dog...
 * @author xiaoyang
 */
public class HoundDog extends Enemy implements Runnable{
	public static final int DEFAULT_SPEED = 3;
	private Random r = new Random();
	private HoundDogMoveAlgorithm howToMove;
	private EnemyDieBehavior howToDie;

	/**
	 * @param handler
	 * @param x
	 * @param y
	 * @param hunter
	 */
	public HoundDog(Handler handler, float x, float y, Hunter hunter) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		speed = DEFAULT_SPEED;
		health = 30;
		xMove = 1;
		yMove = 1;
		howToMove = new HoundDogMoveAlgorithm(this,hunter);
		howToDie = new EnemyDieBehavior(this);
		animDown = new Animation(500, Assets.hound_down);
		animUp = new Animation(500, Assets.hound_up);
		animLeft = new Animation(500, Assets.hound_left);
		animRight = new Animation(500, Assets.hound_right);
	}

	@Override
	public void tick() {
	}

	/**
	 * @param g
	 */
	@Override
	public void render(GraphicsContext g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height);
	}
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
		howToDie.die();
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
}
