package com.unsw.tilegame.entities.enemy;

import com.unsw.tilegame.Handler;
import com.unsw.tilegame.entities.*;
import com.unsw.tilegame.entities.creatures.Player;
import com.unsw.tilegame.tiles.Tile;
import com.unsw.tilegame.tool.Animation;
import com.unsw.tilegame.tool.Assets;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * coward
 * @author xiaoyang && cecilia
 * @
 */
public class Coward extends Enemy implements Runnable, CreatureInterface {
	private static final float DEFAULT_SPEED =4;
	private CowardMoveAlgorithm howToMove;
	public Coward(Handler handler, float x, float y) {
		super(handler, x, y,Tile.TILEWIDTH, Tile.TILEHEIGHT);
		speed = DEFAULT_SPEED;
		health = 20;
		xMove = 0;
		yMove = 0;
		howToMove = new CowardMoveAlgorithm((this));
		animDown = new Animation(500,Assets.coward_down);
		animUp = new Animation(500,Assets.coward_up);
		animRight = new Animation(500, Assets.coward_right);
		animLeft = new Animation(500,Assets.coward_left);
	}

	/**
	 * @param playerX
	 * @param playerY
	 * @return
	 */
	public boolean meetPlayer(double playerX, double playerY){
		if (Math.sqrt(Math.pow(playerX-x, 2)+Math.pow(playerY-y, 2))<=300) {
			return true;
		} else {
			return false;
		}
	}
	public double getPlayerX(){
		return this.handler.getPlayerSituationX();
	}
	public double getPlayerY(){
		return this.handler.getPlayerSituationY();
	}

	public boolean getCowardState(){
		return state.getMoveState();
	}

	/**
	 * @param x
	 */
	public void changeCowardXMove(float x){
		xMove = x;
	}

	/**
	 * @param y
	 */
	public void changeCowardYMove(float y){
		yMove = y;
	}
	public float getDefaultSpeed(){
		return speed;
	}

	@Override
	public void run() {
		if (!Player.getInvincible())
			howToMove.Move(xMove, yMove);
		else
			playerInv.Move(xMove,yMove);
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

	/**
	 * @param amt
	 */
	@Override
	public void hurt(int amt) {
		enemyHurt.hurt(amt);
	}
	@Override
	public float calculateXMoveCollisions(int tx) {
		y -=yMove;
		if (xMove > 0) {
			return (float) (tx*Tile.TILEWIDTH-bounds.getX()-bounds.getWidth()-1);
		} else {
			return (float) (tx*Tile.TILEWIDTH+Tile.TILEWIDTH-bounds.getX());
		}
	}

	/**
	 * @param ty
	 * @return
	 */
	@Override
	public float calculateYMoveCollisions(int ty) {
		x-=xMove;
		if (yMove < 0) {
			return (float) (ty*Tile.TILEHEIGHT+Tile.TILEHEIGHT-bounds.getY());
		} else if (yMove > 0){
			return (float) (ty*Tile.TILEHEIGHT-bounds.getY()-bounds.getHeight()-1);
		}
		return -1;
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
	public CreatureAlgorithm getCreatureAlgorithm() {
		return howToMove;
	}
}
