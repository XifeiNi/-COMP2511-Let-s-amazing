package com.unsw.tilegame.entities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.unsw.tilegame.Handler;
import com.unsw.tilegame.entities.creatures.Player;
import com.unsw.tilegame.entities.enemy.Enemy;

import javafx.scene.canvas.GraphicsContext;

/**
 * manager of entity
 * @author xiaoyang
 * @version 2.8
 * @since 1.4
 */
public class EntityManager {
	//a href<https://github.com/CodeNMore/New-Beginner-Java-Game-Programming-Src/blob/master/Episode%2034/TileGame/src/dev/codenmore/tilegame/entities/EntityManager.java>
	private Handler handler;
	private Player player;
	public ArrayList<Entity> entities;
	ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

	/**
	 * @param handler
	 * @param player
	 */
	public EntityManager(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		entities = new ArrayList<Entity>();
		addEntity(player);
	}

	/**
	 * start thread
	 */
	public void tick() {
		Iterator<Entity> it = entities.iterator();
		while(it.hasNext()) {
			Entity e = it.next();
			e.tick();
			if (e.id == 4) {
				Enemy npc = (Enemy)e;
				cachedThreadPool.execute(npc);
			}
			if (!e.isActive())
				it.remove();
		}
		if (entities != null) {
			entities.sort(renderSorter);
		}
	}

	/**
	 * comparator of entity
	 */
	private Comparator<Entity> renderSorter = new Comparator<Entity>() {
		@Override
		public int compare(Entity a, Entity b) {
			if (a.getY()+a.getHeight() < b.getY()+b.getHeight())
				return -1;
			return 1;
		}
		
	};

	/**
	 * @return handler of current game
	 */
	public Handler getHandler() {
		return handler;
	}

	/**
	 * setter for handler
	 * @param handler
	 */
	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	/**
	 * @return current player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * setting current player
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @return all entitis
	 */
	public ArrayList<Entity> getEntities() {
		return entities;
	}

	/**
	 * display all entities
	 * @param g
	 */
	public void render(GraphicsContext g) {
		if (entities != null) {
			for (Entity e:entities) {
				e.render(g);
			}
		}
		for (int i = 0; i < Player.sshot.size(); i++) {
			Player.sshot.get(i).render(g);
			if (!Player.sshot.get(i).isActive()) {
				Player.sshot.remove(i);
			}
		}
		for (int i = 0; i < Player.bbshot.size(); i++) {
			if (Player.bbshot.get(i).isActive()) {
				Player.bbshot.get(i).render(g);
			} else {
				int bb = Player.bbshot.get(i).playerDie;
				Player.bbshot.remove(i);
				if (bb == 1) {
					player.die();
				}
			}
		}
		if (player != null) {
			player.postRender(g);
		}
	}

	/**
	 * @param e
	 */
	public void addEntity(Entity e) {
		for(Entity i : entities) {
			if (i.id == 5) {
				return;
			}
		}
		this.entities.add(e);
	}
	
}
