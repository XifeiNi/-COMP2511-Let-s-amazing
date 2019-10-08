package com.unsw.tilegame.tiles;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * tile that manages all the static thingy in game
 * @author xiaoyang
 * @version 2.7
 * @since 1.5
 */
public class Tile {
	//a href<https://github.com/CodeNMore/New-Beginner-Java-Game-Programming-Src/blob/master/Episode%2034/TileGame/src/dev/codenmore/tilegame/tiles/Tile.java>
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(0);
	public static Tile dirtTile = new DirtTile(1);
	public static Tile wallTile = new WalllTile(2);
	public static Tile switchTile = new SwitchTile(3);
	public static Tile pitTile = new PitTile(4);
	protected final int id;
	protected Image texture;
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;

	/**
	 * @param texture
	 * @param id
	 */
	public Tile(Image texture, int id) {
		this.texture = texture;
		this.id = id;
		tiles[id] = this;
	}

	/**
	 * @return id of this object
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return this obkect is not movavle
	 */
	public boolean isSolid() {
		return false;
	}

	/**
	 * @return this object is not a switch
	 */
	public boolean isSwitch() {
		return false;
	}

	/**
	 * @return this object is not pit
	 */
	public boolean isPit() {
		return false;
	}

	/**
	 * @param g -> graph
	 * @param x
	 * @param y
	 */
	public void render(GraphicsContext g, int x, int y) {
		g.drawImage(texture,x,y,TILEWIDTH,TILEHEIGHT);
	}
}
