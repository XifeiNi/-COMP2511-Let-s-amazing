package com.unsw.tilegame.entities.enemy;

import com.unsw.tilegame.Handler;
import com.unsw.tilegame.entities.*;
import com.unsw.tilegame.entities.creatures.Creature;
import com.unsw.tilegame.entities.creatures.Player;
import com.unsw.tilegame.state.Move;
import com.unsw.tilegame.state.State;
import com.unsw.tilegame.tool.Animation;

/**
 * enemy abstract class
 * @author xiaoyang
 * @version 2.5
 * @since 1.5
 * @see com.unsw.tilegame.entities.Entity
 * @see com.unsw.tilegame.entities.creatures.Creature
 * @see java.lang.Runnable
 */
public abstract class Enemy extends Creature implements Runnable {
	protected Animation animDown, animUp, animLeft, animRight;
	protected static final int DEFAULT_HEALTH = 5;
	protected int health = DEFAULT_HEALTH;
	protected State state;
	protected PlayerOnInvincibleMoveAlgorithm playerInv;
	protected EnemyDieBehavior enemyDie;
	protected EnemyHurtBehaviour enemyHurt;

	/**
	 * @param handler
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Enemy(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		id = 4;
		bounds.setX(16);
		bounds.setY(16);
		bounds.setWidth(40);
		bounds.setHeight(40);
		playerInv = new PlayerOnInvincibleMoveAlgorithm(this);
        enemyDie = new EnemyDieBehavior(this);
        enemyHurt = new EnemyHurtBehaviour(this);
		this.state = new Move();
	}

	/**
	 * @param amt
	 */
	public abstract void hurt(int amt);
	public void setActive(){
		this.active = false;
	}
	public int getHealth(){
	    return health;
    }

	/**
	 * @param health
	 */
    public void setHealth(int health){
	    this.health = health;
    }
	public abstract void run();

	/**
	 * @param xOffset
	 * @param yOffset
	 * @return
	 */
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		if (handler.getEntityManager()!= null) {
			if (handler.getEntities() != null) {
				for(int i = 0; i < handler.getEntities().size();i++) {
					Entity e = handler.getEntities().get(i);
					if (e.equals(this))
						continue;
					if (e.getCollisionBounds(0f, 0f).getBoundsInLocal().intersects(
							getCollisionBounds(xOffset,yOffset).getBoundsInLocal())) {
						if (e instanceof Player) {
							if(!Player.getInvincible()) {
								if (!((Player)e).hasWeapon()) {
									((Player) e).die();
								} 
							} else {
								die();
							}
						}
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * @param state
	 */
	public void setState(State state) {
		this.state = state;
	}
	public double getPlayerX(){
		return this.handler.getPlayerSituationX();
	}
	public double getPlayerY(){
		return this.handler.getPlayerSituationY();
	}
	public boolean getEnemyState(){
		return state.getMoveState();
	}

	/**
	 * @param x
	 */
	public void changeEnemyXMove(float x){
		xMove = x;
	}

	/**
	 * @param y
	 */
	public void changeEnemyYMove(float y){
		yMove = y;
	}
	public float getDefaultSpeed(){
		return speed;
	}
	public EnemyDieBehavior getEnemyDieBehavior(){
	    return enemyDie;
    }
    public EnemyHurtBehaviour getEnemyHurtBehavior(){
	    return enemyHurt;
    }
}
