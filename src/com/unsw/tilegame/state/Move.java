package com.unsw.tilegame.state;

/**
 * move class
 * @author xiaoyang
 * @version 2.7
 * @since 1.5
 */
public class Move implements State{
	public boolean Movable;
	
	public Move() {
		Movable = true;
	}

	/**
	 * @param movable
	 */
	@Override
	public void setMovable(boolean movable) {
		this.Movable = true;
	}

	/**
	 * @return movable state
	 */
	public boolean getMoveState() {
		return this.Movable;
	}

}
