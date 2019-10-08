package com.unsw.tilegame.worlds;

import com.unsw.tilegame.Handler;
/*
 * this is to manage different map/ mode
 * @author Xiaoyang Yan && Cecilia Xifei Ni
 *
 */
public class WorldManager {
	private Handler handler;
	public static IWorld[] worlds = new WorldDecorator[5];
	public WorldManager(Handler handler) {
		this.handler = handler;
		initialize(handler);
	}
	/*
	 * initialize world array... aka map of the game
	 * world is defined by an 2D array in resources file
	 * @param handler of current game
	 */
	public void initialize(Handler handler) {
		this.handler = handler;
		IWorld world1 = new MyWorld1(new World(handler, "res/worlds/world1.txt",0));
		IWorld world2 = new MyWorld2(new World(handler, "res/worlds/world2.txt",2));
		IWorld world3 = new MyWorld3(new World(handler, "res/worlds/world4.txt",4));
		IWorld world4 = new MyWorld4(new World(handler,"res/worlds/world5.txt",5));
		IWorld world5 = new MyWorld2(new World(handler,"res/worlds/world3.txt",6));
		worlds[0] = world1;
		worlds[1] = world2;
		worlds[2] = world3;
		worlds[3] = world4;
		worlds[4]= world5;
	}
	/*
	 * getter function of current handler
	 * @return handler of this game
	 */
	public Handler getHandler() {
		return handler;
	}
	/*
	 * this function is used to restart all the worlds
	 * after the player dies and returns to main screen
	 */
	public void worldRestart() {
		worlds[0] = new MyWorld1(new World(handler, "res/worlds/world1.txt",0));
		worlds[1] = new MyWorld2(new World(handler, "res/worlds/world2.txt",2));
		worlds[2] =  new MyWorld3(new World(handler, "res/worlds/world4.txt",4));
		worlds[3] = new MyWorld4(new World(handler, "res/worlds/world5.txt",5));
		worlds[4] = new MyWorld2(new World(handler,"res/worlds/world3.txt",6));
	}
}
