package com.unsw.tilegame.worlds;

import com.unsw.tilegame.Handler;
import com.unsw.tilegame.entities.EntityManager;
import com.unsw.tilegame.entities.creatures.Player;
import com.unsw.tilegame.entities.equipment.EquipmentManager;
import com.unsw.tilegame.entities.equipment.Gold;

/**
 * World 3
 * @author cecilian && Xiaoyang
 * @version 2.5
 * @since 2.5
 * @see com.unsw.tilegame.worlds.WorldDecorator
 * @see com.unsw.tilegame.worlds.IWorld
 */
public class MyWorld3 extends WorldDecorator{

private World world;

	/**
	 * @param world
	 */
	public MyWorld3(World world) {
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
		EquipmentManager equipmentManager = world.getEquipmentManager();
		equipmentManager.addEquipment(new Gold(handler,250,756));
		equipmentManager.addEquipment(new Gold(handler,960,820));
		equipmentManager.addEquipment(new Gold(handler,352,240));
		equipmentManager.addEquipment(new Gold(handler,422,440));
		equipmentManager.addEquipment(new Gold(handler,622,640));
		equipmentManager.addEquipment(new Gold(handler,222,840));
		equipmentManager.addEquipment(new Gold(handler,145,1040));
		equipmentManager.addEquipment(new Gold(handler,1152,1152));
		world.setEntityManager(entityManager);
	}

	/**
	 * @return this world object
	 */
	public World getWorld() {
		return world;
	}
}
