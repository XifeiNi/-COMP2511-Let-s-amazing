package com.unsw.tilegame.entities.enemy;

import com.unsw.tilegame.Handler;
import com.unsw.tilegame.entities.creatures.Player;
import com.unsw.tilegame.tiles.Tile;
import com.unsw.tilegame.tool.Animation;
import com.unsw.tilegame.tool.Assets;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author xiaoyang
 * @since 1.6
 * @version 1.7
 * @see com.unsw.tilegame.entities.creatures.Creature
 * @see com.unsw.tilegame.entities.enemy.Enemy
 * @see com.unsw.tilegame.entities.Entity
 */
public class Hunter extends Enemy{
	public static final float DEFAULT_SPEED =2;
	private HunterMoveAlgorithm howToMove;

	/**
	 * @param handler
	 * @param x
	 * @param y
	 */
	public Hunter(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		speed = DEFAULT_SPEED;
		health = 20;
		xMove = 0;
		yMove = 0;
		howToMove = new HunterMoveAlgorithm(this);
		animDown = new Animation(500,Assets.hunter_down);
		animUp = new Animation(500,Assets.hunter_up);
		animRight = new Animation(500, Assets.hunter_right);
		animLeft = new Animation(500,Assets.hunter_left);
	}

	/**
	 * when hunter is alive
	 */
	@Override
	public void run() {
        if (!Player.getInvincible()){
            howToMove.Move(xMove,yMove);
        }   else {
            playerInv.Move(xMove,yMove);
        }
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
	}

	@Override
	public void tick() {
		
	}

	/**
	 * @param g
	 */
	@Override
	public void render(GraphicsContext g) {
		g.drawImage(getCurrentAnimationFrame(),(int) (x - handler.getGameCamera().getxOffset()), 
				(int) (y - handler.getGameCamera().getyOffset()), width, height);
	}

	@Override
	public void die() {
		enemyDie.die();
	}

	@Override
	public void hurt(int amt) {
		enemyHurt.hurt(amt);
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
}
