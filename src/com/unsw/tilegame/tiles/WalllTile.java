package com.unsw.tilegame.tiles;

import com.unsw.tilegame.tool.Assets;

/**
 * wall of the game
 * @author cecilian
 * @version 2.8
 * @since 1.5
 */
public class WalllTile extends Tile {

	/**
	 * @param id
	 */
	public WalllTile(int id) {
		super(Assets.stone, id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return whether this can be moved
	 */
	@Override
	public boolean isSolid() {
		return true;
	}
}
