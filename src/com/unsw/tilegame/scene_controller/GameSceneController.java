package com.unsw.tilegame.scene_controller;

import com.unsw.tilegame.Main;

import javafx.event.Event;
import javafx.stage.Stage;

/**
 * game screen ... when user is playing game
 * @author xiaoyang
 * @version 2.7
 * @since 1.5
 */
public class GameSceneController{
	private Stage stage;

	/**
	 * @param stage
	 */
	public GameSceneController(Stage stage) {
		if (stage == null) {
			throw new IllegalArgumentException("Stage cannot be null");
		}
		this.stage = stage;
	}

	/**
	 * @param event
	 */
	public void handleMousePress(Event event) {
		stage.setScene(Main.getScenes().get(SceneName.MAIN));
	}
}
