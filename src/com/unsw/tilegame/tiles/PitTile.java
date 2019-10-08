package com.unsw.tilegame.tiles;

import com.unsw.tilegame.tool.Assets;

/**
 * pit class
 * @author xiaoyang
 * @version 2.7
 * @since 1.5
 */
public class PitTile extends Tile {

	/**
	 * @param id
	 */
	public PitTile(int id) {
		super(Assets.pit, id);
	}

	/**
	 * @return true... it is a pit
	 */
	public boolean isPit() {
		return true;
	}
	
}
