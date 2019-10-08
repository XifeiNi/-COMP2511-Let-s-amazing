package com.unsw.tilegame;

import java.util.ArrayList;

import com.unsw.tilegame.entities.Entity;
import com.unsw.tilegame.entities.EntityManager;
import com.unsw.tilegame.entities.creatures.Player;
import com.unsw.tilegame.input.KeyManagerJavaFx;
import com.unsw.tilegame.input.MouseManagerJavaFx;
import com.unsw.tilegame.inventory.Inventory;
import com.unsw.tilegame.tiles.Tile;
import com.unsw.tilegame.tool.GameCamera;
import com.unsw.tilegame.worlds.World;
/*
 * This class is to get input from user
 * @author Xiaoyang Yan
 * @version 2.1
 * @since 2.1
 */
public class Handler{
	//a href<https://github.com/CodeNMore/New-Beginner-Java-Game-Programming-Src/blob/master/Episode%2034/TileGame/src/dev/codenmore/tilegame/Handler.java>
	private Game game;
	private World world;
	/*
	 * constructor
	 */
	public Handler(Game game) {
		this.game = game;
	}
	/*
	 * @return game camera-> the center of the screen
	 */
	public GameCamera getGameCamera() {
		return game.getGameCamera();
	}
	/*
	 * this is to get keymanager in javafx
	 * @return Keymanager javafx is currently using
	 */
	public KeyManagerJavaFx getKeyManagerJF() {
		return game.getKeyManagerJF();
	}
	/*
	 * this is to get width of the canvas
	 * @return width of the canvas
	 */
	public int getWidth() {
		return (int) game.getWidths();
	}
	/*
	 * this is to get height of the canvas
	 * @return height of the canvas
	 */
	public int getHeight() {
		return (int) game.getHeights();
	}
	/*
	 * @return the game it is currently running
	 */
	public Game getGame() {
		return game;
	}
	/*
	 * @return the world it is currently using
	 */
	public World getWorld() {
		return world;
	}
	/*
	 * this is to get mouse input from javafx
	 * @return mouse manager from javafx
	 */
	public MouseManagerJavaFx getMouseManagerJF() {
		return game.getMouseManagerJF();
	}
	/*
	 * this is to set world
	 * @param world that user wants to set, according to their input.
	 */
	public void setWorld(World world) {
		this.world = world;
	}

	/*
	 * get player's current x coordinate
	 * @return x coordinate of the player
	 */
	public float getPlayerSituationX() {
		return this.getWorld().getEntityManager().getPlayer().getX();
	}
	/*
	 * get player's current y coordinate
	 * @return y coordinate of the player
	 */
	public float getPlayerSituationY() {
		return this.getWorld().getEntityManager().getPlayer().getY();
	}
	/*
	 * get the player object which is currently playing this game
	 * @return current player object
	 */
	public Player getPlayer() {
		return world.getEntityManager().getPlayer();
	}
	/*
	 * get current inventory
	 * @return current inventory
	 */
	public Inventory getInventory() {
		return getPlayer().getInventory();
	}
	/*
	 * get all the entities
	 * @return an array list of entities
	 */
	public ArrayList<Entity> getEntities(){
		return world.getEntityManager().getEntities();
	}
	/*
	 * get entity manager
	 * @return entity manager object
	 */
	public EntityManager getEntityManager() {
		return world.getEntityManager();
	}
	/*
	 * From the x, y coordinate, get tile at that coordinate
	 * @param x, y coordinate
	 * @return tile
	 */
	public Tile getTile(int x, int y) {
		return world.getTile(x, y);
	}
}
