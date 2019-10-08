package com.unsw.tilegame.worlds;

import com.unsw.tilegame.Handler;
import com.unsw.tilegame.entities.EntityManager;
import com.unsw.tilegame.entities.creatures.Player;
import com.unsw.tilegame.entities.statics.Exit;
import com.unsw.tilegame.entities.statics.Rock;
/*
 * world 1
 * @author Cecilia Xifei Ni
 * @version 1.3
 * @since 1.0
 */
public class MyWorld1 extends WorldDecorator {
	private World world;
	
	public MyWorld1(World world) {
		super(world);
		this.world = world;
		EntityManager entityManager = new EntityManager(world.getHandler(),new Player(world.getHandler(),60,60));
		buildWorld(entityManager);
	}

	/**
	 * @param entityManager
	 */
	@Override
	public void buildWorld(EntityManager entityManager) {
		Handler handler = world.getHandler();
		entityManager.addEntity(new Rock(handler,128,756));
		entityManager.addEntity(new Rock(handler,960,820));
		entityManager.addEntity(new Rock(handler,352,240));
		entityManager.addEntity(new Exit(handler,1152,1152));
		world.setEntityManager(entityManager);
	}

	/**
	 * @return this world
	 */
	public World getWorld() {
		return world;
	}
}
