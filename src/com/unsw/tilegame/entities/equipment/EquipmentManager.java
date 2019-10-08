package com.unsw.tilegame.entities.equipment;

import java.util.ArrayList;
import java.util.Iterator;

import com.unsw.tilegame.Handler;

import javafx.scene.canvas.GraphicsContext;

/**
 * equipment manager that stores all equiment
 * @author xiaoyang
 * @version 2/3
 * @since 1.4
 */
public class EquipmentManager {
	private Handler handler;
	private ArrayList<Equipment> equips;

	/**
	 * @param handler
	 */
	public EquipmentManager(Handler handler) {
		this.handler = handler;
		equips = new ArrayList<Equipment>();
	}

	/**
	 * @return current handler
	 */
	public Handler getHandler() {
		return handler;
	}

	/**
	 * @param handler
	 */
	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	public void tick() {
		Iterator<Equipment> it = equips.iterator();
		while (it.hasNext()) {
			Equipment e = it.next();
			e.tick();
			if (!e.isActive())
				it.remove();
		}
	}

	/**
	 * @param g
	 */
	public void render(GraphicsContext g) {
		for(Equipment i:equips)
			i.render(g);
	}

	/**
	 * @param i
	 */
	public void addEquipment(Equipment i) {
		i.setHandler(handler);
		equips.add(i);
	}
}
