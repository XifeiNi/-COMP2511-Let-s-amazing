package com.unsw.tilegame.entities.effect;


import com.unsw.tilegame.Handler;
import com.unsw.tilegame.entities.Entity;
import com.unsw.tilegame.entities.creatures.Player;
import com.unsw.tilegame.entities.enemy.Enemy;
import com.unsw.tilegame.tiles.Tile;
import com.unsw.tilegame.tool.Assets;

import javafx.scene.canvas.GraphicsContext;
/*
 * @author Cecilia Xifei Ni && Xiaoyang Yan
 * @version 2.7
 * @since 1.1
 */
public class ArrowEffect extends Effect implements Runnable{
	private int Playermove;
	private int attack;
	public static final int UP = 0,DOWN = 1,LEFT = 2,RIGHT = 3;
	protected float xMove,yMove;
	protected int speed;
	private static final int DEFAULT_SPEED = 20;
	/*
	 * constructor of arrow effect
	 */
	public ArrowEffect(Handler handler, float x, float y, int width, int height,int playermove) {
		super(handler, x, y, width, height);
		attack = 5;
		speed = DEFAULT_SPEED;
		if (playermove == UP)
			yMove = 0-speed;
		else if (playermove == DOWN)
			yMove = speed;
		else if (playermove == LEFT)
			xMove = 0-speed;
		else if (playermove == RIGHT);
			xMove = speed;
		Playermove = playermove;
		handler.getPlayer().getInventory().removeEquipment(Player.currentWeapon);
	}

	/*
	 * this is the function that displays the image in javafx
	 * @see animation of using arrow
	 */

	@Override
	public void render(GraphicsContext g) {
		if (Playermove == ArrowEffect.UP || Playermove == ArrowEffect.DOWN ) {
			g.drawImage(Assets.goddessArrow[0], (int) (x - handler.getGameCamera().getxOffset()+30), 
					(int) (y- handler.getGameCamera().getyOffset()), 32, 64);
		} else if (Playermove == ArrowEffect.LEFT || Playermove == ArrowEffect.RIGHT) {
			g.drawImage(Assets.goddessArrow[1], (int) (x - handler.getGameCamera().getxOffset()), 
					(int) (y - handler.getGameCamera().getyOffset()+30), 54, 8);
		}
	}
	/*
	 * arrow will die after limited usage
	 */
	public void die() {
		this.active = false;
	}
	/*
	 * this is to check whether arrow has hit any entity or not
	 * @return whether arrow has hit any entities
	 */
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		if (handler.getEntityManager() != null) {
			if (handler.getEntities() != null) {
				for(int i= 0; i < handler.getEntities().size();i++) {
					Entity e = handler.getEntities().get(i);
					if (e.getCollisionBounds(0f, 0f).getBoundsInLocal().intersects(
							getCollisionBounds(xOffset,yOffset).getBoundsInLocal())) {
						if (e instanceof Enemy){
							((Enemy)e).hurt(attack);
						}
						if (e instanceof Player) {
							return false;
						}
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}
	public void move() {
		if (Playermove == UP || Playermove == DOWN) {
			if(!checkEntityCollisions(0f,yMove)) {
				if (yMove<0) {
					int ty = (int)(y+yMove+bounds.getY())/Tile.TILEHEIGHT;
					if (!collisionWithTile((int)(x+bounds.getX())/Tile.TILEWIDTH,ty) &&
							!collisionWithTile((int)(x+bounds.getX()+bounds.getWidth())/Tile.TILEHEIGHT,ty)) {
						y+=yMove;
					} else {
						die();
					}
				} else if (yMove>0) {
					int ty = (int)(y+yMove+bounds.getY()+bounds.getHeight())/Tile.TILEWIDTH;
					if (!collisionWithTile((int)(x+bounds.getX())/Tile.TILEHEIGHT,ty) &&
							!collisionWithTile((int)(x+bounds.getX()+bounds.getWidth())/Tile.TILEHEIGHT,ty)) {
						y+=yMove;
					} else {
						die();
					}
				}
			} else {
				die();
			}
		} else if (Playermove == LEFT || Playermove == RIGHT) {
			if(!checkEntityCollisions(xMove,0f)) {
				if (Playermove == LEFT) {
					int tx = (int)(x+xMove+bounds.getX())/Tile.TILEWIDTH;
					if (!collisionWithTile(tx,(int)(y+bounds.getY())/Tile.TILEHEIGHT) &&
							!collisionWithTile(tx,(int)(y+bounds.getY()+bounds.getHeight())/Tile.TILEHEIGHT)) {
						x-=speed;
					} else {
						die();
					}
				} else if (Playermove == RIGHT) {
					int tx = (int)(x+xMove+bounds.getX()+bounds.getWidth())/Tile.TILEWIDTH;
					if (!collisionWithTile(tx,(int)(y+bounds.getY())/Tile.TILEHEIGHT) &&
							!collisionWithTile(tx,(int)(y+bounds.getY()+bounds.getHeight())/Tile.TILEHEIGHT)) {
						x+=speed;
					} else {
						die();
					}
				}
			} else {
				die();
			}
		}
	}

	@Override
	public void run() {
		while (active) {
			try {
				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
			move();
			if (!active) {
				break;
			}
		}
	}
	/*
	 * @return the speed of arrow
	 */
	public int getSpeed() {
		return speed;
	}
}
