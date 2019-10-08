package com.unsw.tilegame.inventory;

import java.util.ArrayList;
import com.unsw.tilegame.Handler;
import com.unsw.tilegame.entities.creatures.Player;
import com.unsw.tilegame.entities.equipment.Equipment;
import com.unsw.tilegame.tool.Assets;
import com.unsw.tilegame.tool.Text;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

/**
 * inventory object that manages player's inventory... aka enquipment
 * @author cecilian && xiaoyang
 * @since 1.3
 * @version 2.6
 */
public class Inventory {
	//a href<https://github.com/CodeNMore/New-Beginner-Java-Game-Programming-Src/tree/master/Episode%2034/TileGame/src/dev/codenmore/tilegame/inventory>
	//For calculating the frame in the picture chosen, some techniques from the online tutorial.
	private int invX = 136,invY = 96,invWidth = 1024,invHeight = 768, invListCenterX = invX + (int)(256.5),
			invListCenterY = invY + (int)(invHeight/2 + 10), invListSpacing = 60;
	private int swordAttack = 100;
	private int selectedItem = 0;
	private Handler handler;
	private boolean active = false;
	private ArrayList<Equipment> inventoryItems;
	private int invImageX = 904, invImageY = 164,invImageWidth = 128, invImageHeight = 128;
	private int invCountX = 968, invCountY = 344;

	/**
	 * @param builder
	 */
	private Inventory(InventoryBuilder builder) {
		this.handler = builder.handler;
		inventoryItems = builder.inventoryItems;
	}

	/**
	 * builder pattern to hide inventory
	 * @author cecilian
	 * @version 2.6
	 * @since 2.6
	 */
	public static class InventoryBuilder{
		private Handler handler;
		private ArrayList<Equipment> inventoryItems;

		/**
		 * @param handler
		 */
		public InventoryBuilder(Handler handler){
			this.handler = handler;
			inventoryItems = new ArrayList<Equipment>();
		}

		/**
		 * @return inventory object
		 */
		public Inventory build(){
			return new Inventory(this);
		}
	}

	/**
	 * manages event of selecting weapon
	 */
	public void tick() {
		if (handler.getKeyManagerJF().isPressed(KeyCode.E))
			active = !active;
		if(!active)
			return;
		if (handler.getKeyManagerJF().isPressed(KeyCode.W))
			selectedItem--;
		if(handler.getKeyManagerJF().isPressed(KeyCode.S))
			selectedItem++;
		if (selectedItem < 0)
			selectedItem = inventoryItems.size()-1;
		else if (selectedItem >= inventoryItems.size())
			selectedItem = 0;
		if (handler.getKeyManagerJF().isPressed(KeyCode.ENTER)) {
			if (inventoryItems != null) {
				if (inventoryItems.get(selectedItem).getId() == 2) {
					Player.setCurrentWeapon(inventoryItems.get(selectedItem));
					Player.setOnFire(true);
					handler.getPlayer().setAttack(Player.ATTACKORIGINAL);
					handler.getPlayer().setAttack(handler.getPlayer().getAttack() + swordAttack);
				} else if (inventoryItems.get(selectedItem).getId() == 3) {
					Player.setCurrentWeapon(inventoryItems.get(selectedItem));
					Player.setOnFire(true);
				} else if (inventoryItems.get(selectedItem).getId() == 6) {
					Player.setCurrentWeapon(inventoryItems.get(selectedItem));
				} else if (inventoryItems.get(selectedItem).getId() == 7) {
					Player.setCurrentWeapon(inventoryItems.get(selectedItem));
					Player.setOnFire(true);
				}
			}
		}
		setPostionForAllEquipment();
	}

	/**
	 * display...
	 * @param g
	 */
	public void render(GraphicsContext g) {
		if(!active)
			return;
		g.drawImage(Assets.inventoryScreen,invX,invY,invWidth,invHeight);
		int len = inventoryItems.size();
		if (len == 0)
			return;
		for (int i=-5 ; i < 6; i++) {
			if (selectedItem+i < 0 || selectedItem+i>=len)
				continue;
			if (i == 0) {
				Text.drawString(g,">"+inventoryItems.get(selectedItem+i).getName()+"<",invListCenterX,
						invListCenterY + i*invListSpacing,true,Color.YELLOW,Assets.font28);
			} else {
				Text.drawString(g,">"+inventoryItems.get(selectedItem+i).getName()+"<",invListCenterX,
						invListCenterY + i*invListSpacing,true,Color.WHITE,Assets.font28);
			}
		}
		Equipment item = inventoryItems.get(selectedItem);
		g.drawImage(item.getTexture(), invImageX,invImageY,invImageWidth,invImageHeight);
		Text.drawString(g, Integer.toString(item.getCount()), invCountX, invCountY, true,
				Color.WHITE, Assets.font28);
	}

	/**
	 * @param equipment
	 */
	public void removeEquipment(Equipment equipment) {
		for(Equipment e : inventoryItems) {
			if (e.getId() == equipment.getId()) {
				e.setCount(e.getCount()-1);
				if (e.getCount()> 0) {
					return;
				}
			}
		}
		inventoryItems.remove(equipment);
	}

	/**
	 * @return handler of the game
	 */
	public Handler getHandler() {
		return handler;
	}

	/**
	 * @return whether user is using inventory?
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param equipment
	 */
	public void addEquipment(Equipment equipment) {
		for(Equipment e : inventoryItems) {
			if (e.getId() == equipment.getId()) {
				e.setCount(e.getCount()+equipment.getCount());
				return;
			}
		}
		inventoryItems.add(equipment);
	}

	/**
	 * set posionn acoording to player's situation
	 */
	public void setPostionForAllEquipment() {
		for(Equipment e: inventoryItems) {
			e.setPosition(handler.getPlayerSituationX(), handler.getPlayerSituationY());
		}
	}
}
