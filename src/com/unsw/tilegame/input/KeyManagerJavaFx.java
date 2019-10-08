package com.unsw.tilegame.input;

import java.util.HashMap;
import java.util.Map;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * key manager
 * @author xiaoyang
 * @version 2.7
 * @since 1.5
 */
public class KeyManagerJavaFx implements EventHandler<KeyEvent>{
	private Map<KeyCode, Boolean> map = new HashMap<KeyCode, Boolean>();
	public Boolean up,down,left,right,exit;
	public Boolean aUp,aDown,aLeft,aRight;

	/**
	 * hash map of all the commands
	 */
	public KeyManagerJavaFx() {
		map.put(KeyCode.W, false);
		map.put(KeyCode.S, false);
		map.put(KeyCode.A, false);
		map.put(KeyCode.D, false);
		map.put(KeyCode.E, false);
		map.put(KeyCode.ENTER, false);
		map.put(KeyCode.ESCAPE, false);
		
		map.put(KeyCode.UP,false);
		map.put(KeyCode.DOWN,false);
		map.put(KeyCode.LEFT,false);
		map.put(KeyCode.RIGHT,false);
		
		up = map.get(KeyCode.W);
		down = map.get(KeyCode.S);
		left = map.get(KeyCode.A);
		right = map.get(KeyCode.D);
		exit = map.get(KeyCode.ESCAPE);
		
		
		aUp = map.get(KeyCode.UP);
		aDown = map.get(KeyCode.DOWN);
		aLeft = map.get(KeyCode.LEFT);
		aRight = map.get(KeyCode.RIGHT);
	}

	/**
	 * @param event
	 */
	@Override
	public void handle(KeyEvent event) {
		 if (event.getEventType().equals(KeyEvent.KEY_PRESSED)) {
			 map.put(event.getCode(), true);
		 } else if (event.getEventType().equals(KeyEvent.KEY_RELEASED)) {
			 map.put(event.getCode(), false);
		 }
	}

	/**
	 * @param code
	 * @return whether a certain key is pressed
	 */
	public boolean isPressed(KeyCode code) {
	    return map.containsKey(code)?map.get(code):false;
	}

	/**
	 * restarting when game restarts
	 */
	public void keyManagerRestart() {
		for(Map.Entry<KeyCode,Boolean> entry:map.entrySet()) {
			entry.setValue(false);
		}
	}
}
