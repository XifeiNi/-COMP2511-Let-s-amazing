package com.unsw.tilegame.entities.effect;


import com.unsw.tilegame.Handler;
import com.unsw.tilegame.entities.Entity;
import com.unsw.tilegame.entities.creatures.Player;
import com.unsw.tilegame.tiles.Tile;
import com.unsw.tilegame.tool.Animation;
import com.unsw.tilegame.tool.Assets;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

/**
 * bomb effect
 * @author xiaoyang
 * @version 1.5
 * @since 1.4
 */
public class BombEffect extends Effect implements Runnable{
	private Animation bombLit;
	private int Playermove;
	private boolean burstPoint = false;
	private boolean Movable = true;
	private float xOriginal = 0, yOriginal = 0;
	public static final int UP = 0,DOWN = 1,LEFT = 2,RIGHT = 3;
	private int r;
	protected float xMove,yMove;
	protected int speed;
	private int id = 5;
	public int playerDie = 0;
	private final static int DEFAULT_SPEED = 10;

	/**
	 * @param handler
	 * @param x
	 * @param y
	 * @param playermove
	 */
	public BombEffect(Handler handler, float x, float y, int playermove) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		bombLit = new Animation(500,Assets.bomb_lit);
		xOriginal = x;
		yOriginal = y;
		r = 300;
		speed = DEFAULT_SPEED;
		if (playermove == UP)
			yMove = -10;
		else if (playermove == DOWN)
			yMove = 10;
		else if (playermove == LEFT)
			xMove = -10;
		else if (playermove == RIGHT)
			xMove = 10;
		Playermove = playermove;
		Movable = true;
		handler.getPlayer().getInventory().removeEquipment(Player.currentWeapon);
	}

	/**
	 * @param g
	 */
	@Override
	public void render(GraphicsContext g) {
		if (burstPoint) {
			g.drawImage(bombLit.getCurrentFrame(), (int) (x - handler.getGameCamera().getxOffset()), 
					(int) (y - handler.getGameCamera().getyOffset()), width/2, height/2);
		} 
		if (Movable) {
			g.drawImage(Assets.bomb, (int) (x - handler.getGameCamera().getxOffset()), 
					(int) (y - handler.getGameCamera().getyOffset()), width/2, height/2);
		}
	}

	/**
	 * @param xOffset
	 * @param yOffset
	 * @return
	 */
	public Rectangle getDestroyBounds(float xOffset, float yOffset) {
		return new Rectangle((int)(x + bounds.getX() + xOffset-(1/2)*bounds.getX()*3),
				(int)(y+bounds.getY()+yOffset-(1/2)*bounds.getHeight()*3),bounds.getWidth()*3.5,bounds.getHeight()*3.5);
	}
	
	public void die() {
		if (handler.getEntityManager()!= null) {
			if (handler.getEntities().size()!=0) {
				for (int i = 0; i < handler.getEntities().size(); i++) {
					Entity e = handler.getEntities().get(i);
					if (e.getCollisionBounds(0f, 0f).getBoundsInLocal().intersects(getDestroyBounds(xMove,0).getBoundsInLocal()) || 
							e.getCollisionBounds(0f, 0f).getBoundsInLocal().intersects(getDestroyBounds(0,yMove).getBoundsInLocal()) ) {
						if (e.id == 4 || e.id == 7 || e.id == 2) {
							e.die();
						} else if (e.id == 0) {
							playerDie = 1;
						}
					}
				}
			}
		}
		this.active = false;
	}

	/**
	 * @param xOffset
	 * @param yOffset
	 * @return
	 */
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		if (handler.getEntityManager() != null) {
			for(Entity e : handler.getEntities()) {
				if (e.getCollisionBounds(0f, 0f).getBoundsInLocal().intersects(
						getCollisionBounds(xOffset,yOffset).getBoundsInLocal())) {
					if (e.id == 0) {
						return false;
					}
					Movable = false;
					burstPoint = true;
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}

	public void move() {
		if (Movable) {
			if ((Math.sqrt(Math.pow((x-xOriginal), 2)+Math.pow((y-yOriginal), 2))<r)){
				if (Playermove == UP || Playermove == DOWN) {
					if(!checkEntityCollisions(0f,yMove)) {
						if (yMove<0) {
							int ty = (int)(y+yMove+bounds.getY())/Tile.TILEHEIGHT;
							if (!collisionWithTile((int)(x+bounds.getX())/Tile.TILEWIDTH,ty) &&
									!collisionWithTile((int)(x+bounds.getX()+bounds.getWidth())/Tile.TILEHEIGHT,ty)) {
								y+=yMove;
							} else {
								Movable = false;
								burstPoint = true;
							}
						} else if (yMove>0) {
							int ty = (int)(y+yMove+bounds.getY()+bounds.getHeight())/Tile.TILEWIDTH;
							if (!collisionWithTile((int)(x+bounds.getX())/Tile.TILEHEIGHT,ty) &&
									!collisionWithTile((int)(x+bounds.getX()+bounds.getWidth())/Tile.TILEHEIGHT,ty)) {
								y+=yMove;
							} else {
								Movable = false;
								burstPoint = true;
							}
						}
					}
				} else if (Playermove == LEFT || Playermove == RIGHT) {
					if(!checkEntityCollisions(xMove,0f)) {
						if (Playermove == LEFT) {
							int tx = (int)(x+xMove+bounds.getX())/Tile.TILEWIDTH;
							if (!collisionWithTile(tx,(int)(y+bounds.getY())/Tile.TILEHEIGHT) &&
									!collisionWithTile(tx,(int)(y+bounds.getY()+bounds.getHeight())/Tile.TILEHEIGHT)) {
								x+=xMove;
							} else {
								Movable = false;
								burstPoint = true;
							}
						} else if (Playermove == RIGHT) {
							int tx = (int)(x+xMove+bounds.getX()+bounds.getWidth())/Tile.TILEWIDTH;
							if (!collisionWithTile(tx,(int)(y+bounds.getY())/Tile.TILEHEIGHT) &&
									!collisionWithTile(tx,(int)(y+bounds.getY()+bounds.getHeight())/Tile.TILEHEIGHT)) {
								x+=xMove;
							} else {
								Movable = false;
								burstPoint = true;
							}
						}
					}
				}
			} else {
				Movable = false;
				burstPoint = true;
			}
		}
	}
	public int getSpeed() {
		return speed;
	}
	@Override
	public void run() {
		while (active) {
			try {
				Thread.sleep(20);
			} catch (Exception e) {
				e.printStackTrace();
			}
			move();
			if (burstPoint) {
				bombLit.tick();
				if (bombLit.frameIndex() == 4) {
					die();
				}
			}
			if (active == false) {
				break;
			}
		}
	}
}
