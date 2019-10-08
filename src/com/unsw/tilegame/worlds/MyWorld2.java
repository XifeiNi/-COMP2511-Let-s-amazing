package com.unsw.tilegame.worlds;

import com.unsw.tilegame.Handler;
import com.unsw.tilegame.entities.EntityManager;
import com.unsw.tilegame.entities.creatures.Player;
import com.unsw.tilegame.entities.enemy.Coward;
import com.unsw.tilegame.entities.enemy.HoundDog;
import com.unsw.tilegame.entities.enemy.Hunter;
import com.unsw.tilegame.entities.enemy.Strategist;
import com.unsw.tilegame.entities.equipment.Arrow;
import com.unsw.tilegame.entities.equipment.Bomb;
import com.unsw.tilegame.entities.equipment.EquipmentManager;
import com.unsw.tilegame.entities.equipment.Gold;
import com.unsw.tilegame.entities.equipment.Key;
import com.unsw.tilegame.entities.equipment.Sword;
import com.unsw.tilegame.entities.potion.Hover;
import com.unsw.tilegame.entities.potion.Invincible;
import com.unsw.tilegame.entities.statics.Door;
import com.unsw.tilegame.entities.statics.Exit;
import com.unsw.tilegame.entities.statics.Rock;
import com.unsw.tilegame.entities.statics.Tree;

/**
 * World 2
 * @author Cecilia Xifei Ni && Xiaoyang Yan
 * @version 2.5
 * @since 2.5
 * @see com.unsw.tilegame.worlds.WorldDecorator
 */
public class MyWorld2 extends WorldDecorator{

	private World world;

	/**
	 * @param world object
	 */
	public MyWorld2(World world) {
		super(world);
		this.world = world;
		EntityManager entityManager = new EntityManager(world.getHandler(),new Player(world.getHandler(),100,100));
		buildWorld(entityManager);
	}

	/**
	 * @param entityManager
	 */
	@Override
	public void buildWorld(EntityManager entityManager) {
		Handler handler = world.getHandler();
		EquipmentManager equipmentManager = world.getEquipmentManager();
		entityManager.addEntity(new Tree(handler,1030,250));
		entityManager.addEntity(new Tree(handler,730,450));
		entityManager.addEntity(new Rock(handler,650,650));
		entityManager.addEntity(new Rock(handler,800,600));
		entityManager.addEntity(new Rock(handler,650,500));
		entityManager.addEntity(new Tree(handler,130,850));
		entityManager.addEntity(new Tree(handler,530,950));
		equipmentManager.addEquipment(new Sword(handler,200,200));
		equipmentManager.addEquipment(new Arrow(handler,600,700));
		equipmentManager.addEquipment(new Arrow(handler,400,800));
		equipmentManager.addEquipment(new Gold(handler,1000,200));
		equipmentManager.addEquipment(new Key(handler,1000,800));
		entityManager.addEntity(new Door(handler,900,900));
		entityManager.addEntity(new Door(handler,960,900));
		equipmentManager.addEquipment(new Bomb(handler,600,100));
		equipmentManager.addEquipment(new Bomb(handler,400,400));
		entityManager.addEntity(new Strategist(handler,700,950));
		entityManager.addEntity(new Coward(handler,250,200));
		equipmentManager.addEquipment(new Hover(handler,500,700));
		equipmentManager.addEquipment(new Invincible(handler,500,200));
		entityManager.addEntity(new Exit(handler,1152,1152));
		Hunter hunter = new Hunter(handler,900,400);
		entityManager.addEntity(hunter);
		entityManager.addEntity(new HoundDog(handler,250,900,hunter));
		entityManager.addEntity(new HoundDog(handler,1050,400,hunter));
		world.setEntityManager(entityManager);
	}

	/**
	 * @return this world object
	 */
	public World getWorld() {
		return world;
	}

}
