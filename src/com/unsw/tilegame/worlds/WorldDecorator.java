package com.unsw.tilegame.worlds;

import com.unsw.tilegame.entities.EntityManager;
import com.unsw.tilegame.entities.creatures.Player;
/*
 * This is a decorator pattern for world
 * that differentiates different world decorator
 * @author Cecilia Xifei Ni && Xiaoyang Yan
 * @version 2.5
 * @since 2.5
 */
public class WorldDecorator implements IWorld {
	
	private World world;
	/*
	 * constructor
	 */
	public WorldDecorator(World world) {
		this.world = world;
	}
	/*
	 * this function is to build a new world
	 */
	@Override
	public void buildWorld(EntityManager entityManager) {
		entityManager = new EntityManager(world.getHandler(),new Player(world.getHandler(),60,60));
	}
	/*
	 * the getter function of the current running world
	 * @return current world
	 */
	@Override
	public World getWorld() {
		return world;
	}
	
}
