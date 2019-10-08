package com.unsw.tilegame.worlds;

import com.unsw.tilegame.entities.EntityManager;
/*
 * an interface for world building decorator
 * @author Cecilia Xifei Ni && Xiaoyang Yan
 * @version 2.5
 * @since 2.5
 */
public interface IWorld {
	/*
	 * this function is to build a new world
	 */
	public void buildWorld(EntityManager entityManager);
	/*
	 * the getter function of the current running world
	 * @return current world
	 */
	public World getWorld();
}
