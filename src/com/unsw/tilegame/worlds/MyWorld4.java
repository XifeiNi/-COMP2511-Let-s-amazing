package com.unsw.tilegame.worlds;

import com.unsw.tilegame.Handler;
import com.unsw.tilegame.entities.EntityManager;
import com.unsw.tilegame.entities.creatures.Player;
import com.unsw.tilegame.entities.statics.Rock;

/**
 * World 4
 * @author cecilian && xiaoyang
 * @version 2.6
 * @since 2.6
 * @see com.unsw.tilegame.worlds.IWorld
 * @see com.unsw.tilegame.worlds.WorldDecorator
 */
public class MyWorld4 extends WorldDecorator{
	private World world;

	/**
	 * @param world
	 */
	public MyWorld4(World world) {
		super(world);
		this.world = world;
		EntityManager entityManager = new EntityManager(world.getHandler(),new Player(world.getHandler(),60,60));
		buildWorld(entityManager);
	}

	/**
	 * adding all the necessity to world... rock...etc
	 * @param entityManager
	 */
	@Override
	public void buildWorld(EntityManager entityManager) {
		Handler handler = world.getHandler();
		entityManager.addEntity(new Rock(handler,320,192));
		entityManager.addEntity(new Rock(handler,512,512));
		entityManager.addEntity(new Rock(handler,1024,1098));
		entityManager.addEntity(new Rock(handler,896,128));
		world.setEntityManager(entityManager);
	}

	/**
	 * @return this world object
	 */
	public World getWorld() {
		return world;
	}

}
