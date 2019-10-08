package com.unsw.tilegame.entities.statics;

import com.unsw.tilegame.Handler;
import com.unsw.tilegame.tiles.Tile;
import com.unsw.tilegame.tool.Assets;

import javafx.scene.canvas.GraphicsContext;

public class Rock extends StaticEntity {
	public static final int ROCK = 7;
	private float xMove, yMove;
	public static float xM,yM;
	private boolean Movable;
	private static int rockSwitch;
	public Rock(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		id = ROCK;
		xMove = 0;
		yMove = 0;
		rockSwitch = 0;
		Movable = true;
		
	}
	@Override
	public void render(GraphicsContext g) {
		g.drawImage(Assets.rock, (int) (x - handler.getGameCamera().getxOffset()), 
				(int) (y - handler.getGameCamera().getyOffset()), width, height);
	}

	@Override
	public void die() {
		this.active = false;
	}

	protected boolean collisionWithTile(int x, int y) {
		return handler.getTile(x, y).isSolid();
	}

	public void move() {
		xMove = xM;
		yMove = yM;
		if (!checkEntityCollisions(xMove,0f))
			moveX();
		if (!checkEntityCollisions(0f,yMove))
			moveY();
		xM = 0;
		yM = 0;
		xMove = 0;
		yMove = 0;
	}
	public boolean collisionWithSwitchTile(int x, int y) {
		return handler.getTile(x, y).isSwitch();
	}
	public int calculateTileXMove() {
		if (xMove > 0) {
			return (int)(x+xMove+bounds.getX()+bounds.getWidth())/Tile.TILEWIDTH;
		} else if (xMove < 0){
			return (int)(x+xMove+bounds.getX())/Tile.TILEWIDTH;
		}
		return -1;
	}
	public boolean calculateXMoveSituation(int tx) {
		return !collisionWithTile(tx,(int)(y+bounds.getY())/Tile.TILEHEIGHT) &&
				!collisionWithTile(tx,(int)(y+bounds.getY()+bounds.getHeight())/Tile.TILEHEIGHT);
	}
	public boolean calculateXMoveSwitchSituation(int tx) {
		return!collisionWithSwitchTile(tx,(int)(y+bounds.getY())/Tile.TILEHEIGHT);
	}
	public float calculateXMoveCollisions(int tx) {
		if (xMove != 0) {
			return (float) (tx*Tile.TILEWIDTH-bounds.getX()-bounds.getWidth()-1);
		}
		return 0;
	}
	public float calculateXMoveSwitchCollisions(int tx) {
		return (tx)*Tile.TILEWIDTH;
	}
	public int calculateTileYMove() {
		if (yMove != 0)
			return (int)(y+yMove+bounds.getY())/Tile.TILEHEIGHT;
		return -1;
	}
	public boolean calculateYMoveSituation(int ty) {
		return !collisionWithTile((int)(x+bounds.getX())/Tile.TILEWIDTH,ty) &&
				!collisionWithTile((int)(x+bounds.getX()+bounds.getWidth())/Tile.TILEHEIGHT,ty);
	}
	public float calculateYMoveCollisions(int ty) {
		if (yMove < 0) {
			return (float) (ty*Tile.TILEHEIGHT+Tile.TILEHEIGHT-bounds.getY());
		} else if (yMove > 0){
			return (float) (ty*Tile.TILEHEIGHT-bounds.getY()-bounds.getHeight()-1);
		}
		return -1;
	}
	public boolean calculateYMoveSwitchSituation(int ty) {
		if (yMove < 0)
			return !collisionWithSwitchTile((int)(x+bounds.getX())/Tile.TILEWIDTH,ty);
		else
			return !collisionWithSwitchTile((int)(x)/Tile.TILEWIDTH,ty);
	}
	public float calculateYMoveSwitchCollisions(int ty) {
		return ty*Tile.TILEHEIGHT;
	}
	public void moveX() {
		if (Movable) {
			int tx = calculateTileXMove();
			if (tx != -1) {
				if (calculateXMoveSituation(tx)) {
					if (calculateXMoveSwitchSituation(tx)) {
						x += xMove;
					} else {
						x = calculateXMoveSwitchCollisions(tx);
						Movable = false;
						rockSwitch++;
					}
				} else {
					x=calculateXMoveCollisions(tx);
				}
			}
		}
	}
	public void moveY() {
		if (Movable) {
			int ty = calculateTileYMove();
			if (ty != -1) {
				if (calculateYMoveSituation(ty)) {
					if (calculateYMoveSwitchSituation(ty)) {
						y += yMove;
					} else {
						y = calculateYMoveSwitchCollisions(ty);
						Movable = false;
						rockSwitch++;
					}
				} else {
					y=calculateYMoveCollisions(ty);
				}
			}
		}
	}
	public static int getRockSwitch() {
		return rockSwitch;
	}
	public void setXMove(float xMove) {
		this.xMove = xMove;
	}
}
