package com.unsw.tilegame.tiles;

import com.unsw.tilegame.tool.Assets;

/*
 * rock ...
 * @author xiaoyang
 * @version 2.7
 * @since 1.5
 */
public class RockTile extends Tile {

	/**
	 * @param id
	 */
	public RockTile(int id) {
		super(Assets.stone, id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return it is movable
	 */
	@Override
	public boolean isSolid() {
		return true;
	}
}
