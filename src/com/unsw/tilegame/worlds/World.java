package com.unsw.tilegame.worlds;

import com.unsw.tilegame.Handler;
import com.unsw.tilegame.entities.EntityManager;
import com.unsw.tilegame.entities.creatures.Player;
import com.unsw.tilegame.entities.enemy.Coward;
import com.unsw.tilegame.entities.enemy.HoundDog;
import com.unsw.tilegame.entities.enemy.Hunter;
import com.unsw.tilegame.entities.enemy.Strategist;
import com.unsw.tilegame.entities.equipment.Arrow;
import com.unsw.tilegame.entities.equipment.Bomb;
import com.unsw.tilegame.entities.equipment.EquipmentManager;
import com.unsw.tilegame.entities.equipment.Gold;
import com.unsw.tilegame.entities.equipment.Key;
import com.unsw.tilegame.entities.equipment.Sword;
import com.unsw.tilegame.entities.potion.Hover;
import com.unsw.tilegame.entities.potion.Invincible;
import com.unsw.tilegame.entities.statics.Door;
import com.unsw.tilegame.entities.statics.Exit;
import com.unsw.tilegame.entities.statics.Rock;
import com.unsw.tilegame.entities.statics.Tree;
import com.unsw.tilegame.tiles.Tile;
import com.unsw.tilegame.utils.Utils;

import javafx.scene.canvas.GraphicsContext;

/**
 * World class that creates world object
 * @author cecilian && xiaoyang
 * @version 2.7
 * @since 1.0
 * @see com.unsw.tilegame.worlds.IWorld
 */
public class World implements IWorld{
	//a href<https://github.com/CodeNMore/New-Beginner-Java-Game-Programming-Src/blob/master/Episode%2034/TileGame/src/dev/codenmore/tilegame/worlds/World.java>
	private Handler handler;
	private int width, height;
	private int[][] tiles;
	private int worldId;
	private EntityManager entityManager;
	private EquipmentManager equipmentManager;

	/**
	 * @param handler
	 * @param path
	 * @param worldId
	 */
	public World(Handler handler, String path, int worldId){
		this.handler  = handler;
		equipmentManager = new EquipmentManager(handler);
		loadWorld(path);
		if (worldId == 1) {
			setWorldHard();
		}
	}

	/**
	 * Ensuring equipment & entity is not null
	 * then make them run
	 */
	public void tick(){
		if (equipmentManager != null) {
			equipmentManager.tick();
		}
		if (entityManager != null) {
			entityManager.tick();
		}
	}

	/**
	 * @param g -> graphic of java
	 * render graphs and images of game
	 */
	public void render(GraphicsContext g){
		/*int xStart = (int)Math.max(0, handler.getGameCamera().getxOffset()/Tile.TILEWIDTH);
		int xEnd = (int)Math.min(width, (handler.getGameCamera().getxOffset()+
				handler.getWidth())/Tile.TILEWIDTH+1);
		int yStart = (int)Math.max(0, handler.getGameCamera().getyOffset()/Tile.TILEHEIGHT);
		int yEnd = (int)Math.min(height, (handler.getGameCamera().getyOffset()+
				handler.getHeight())/Tile.TILEHEIGHT+1);*/
		  
		int xStart = 0;
		int xEnd = 20;
		int yStart = 0;
		int yEnd = 20;
		for(int y = yStart;y < yEnd;y++){
			for(int x = xStart;x < xEnd;x++){
				if (worldId == 2) {
					Tile.grassTile.render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
							(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
				} else if (worldId == 0) {
					Tile.dirtTile.render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
							(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
				}
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		if (equipmentManager != null) {
			equipmentManager.render(g);
		}
		if (entityManager != null) {
			entityManager.render(g);
		}
	}

	/**
	 * @param x coordinate
	 * @param y coordinate
	 * @return tile on that coordinate
	 */
	public Tile getTile(int x, int y){
		if (x<0 || y<0 || x>width || y>height) {
			return Tile.dirtTile;
		}
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null)
			return Tile.dirtTile;
		return t;
	}

	/**
	 * @param path that parsed by string
	 *             loading world according to string
	 */
	public void loadWorld(String path){
		String file = Utils.loadFile(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		tiles = new int[width][height];
		for(int y = 0;y < height;y++){
			for(int x = 0;x < width;x++){
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 2]);
			}
		}
	}

	/**
	 * @return width of the canvas
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return height of the canvas
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return entitymanager of the game
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * @param entityManager
	 */
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * @return handler of the game
	 */
	public Handler getHandler() {
		return handler;
	}

	/**
	 * @return equipmentManager of the game
	 */
	public EquipmentManager getEquipmentManager() {
		return equipmentManager;
	}

	/**
	 * @param entityManager
	 */
	@Override
	public void buildWorld(EntityManager entityManager) {
		equipmentManager = new EquipmentManager(handler);
	}

	/**
	 * @return this world
	 */
	@Override
	public World getWorld() {
		return this;
	}

	/**
	 * @return player on this game
	 */
	public Player getPlayer() {
		return entityManager.getPlayer();
	}
	//just for JUnit test
	public void setWorldHard() {
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		entityManager.addEntity(new Tree(handler, 1030, 250));
		entityManager.addEntity(new Tree(handler, 730, 450));
		entityManager.addEntity(new Rock(handler, 650, 650));
		entityManager.addEntity(new Rock(handler, 800, 600));
		entityManager.addEntity(new Rock(handler, 650, 500));
		entityManager.addEntity(new Tree(handler, 130, 850));
		entityManager.addEntity(new Tree(handler, 530, 950));
		equipmentManager.addEquipment(new Sword(handler, 330, 600));
		equipmentManager.addEquipment(new Arrow(handler, 600, 700));
		equipmentManager.addEquipment(new Arrow(handler, 400, 800));
		equipmentManager.addEquipment(new Gold(handler, 1000, 200));
		equipmentManager.addEquipment(new Key(handler, 1000, 800));
		entityManager.addEntity(new Door(handler, 900, 900));
		entityManager.addEntity(new Door(handler, 960, 900));
		equipmentManager.addEquipment(new Bomb(handler, 600, 100));
		equipmentManager.addEquipment(new Bomb(handler, 400, 400));
		entityManager.addEntity(new Strategist(handler, 700, 950));
		entityManager.addEntity(new Coward(handler, 250, 200));
		equipmentManager.addEquipment(new Hover(handler, 500, 700));
		equipmentManager.addEquipment(new Invincible(handler, 500, 200));
		entityManager.addEntity(new Exit(handler, 1152, 1152));
		Hunter hunter = new Hunter(handler, 900, 400);
		entityManager.addEntity(hunter);
		entityManager.addEntity(new HoundDog(handler, 250, 900, hunter));
		entityManager.addEntity(new HoundDog(handler, 1050, 400, hunter));
	}
}
