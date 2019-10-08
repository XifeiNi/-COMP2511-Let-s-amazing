package com.unsw.tilegame.tiles;

import com.unsw.tilegame.tool.Assets;

/**
 * switch of the game
 * @author xiaoyang
 * @version 2.7
 * @since 1.5
 */
public class SwitchTile extends Tile {

	/**
	 * @param id
	 */
	public SwitchTile(int id) {
		super(Assets.switchPlace, id);
	}

	/**
	 * @return true... is a switch
	 */
	public boolean isSwitch() {
		return true;
	}
}
