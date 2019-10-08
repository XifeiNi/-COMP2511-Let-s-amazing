package com.unsw.tilegame.state;

/**
 * state pattern for whether it is movable or not
 * @author xiaoyang
 * @version 2.5
 * @since 2.5
 */
public class Stop implements State {
	
	public boolean Movable;

	/**
	 * setting movable to falsae
	 */
	public Stop() {
		Movable = false;
	}

	/**
	 * @param movable
	 */
	@Override
	public void setMovable(boolean movable) {
		Movable = false;
	}

	/**
	 * @return stage of movement
	 */
	@Override
	public boolean getMoveState() {
		return Movable;
	}

}
