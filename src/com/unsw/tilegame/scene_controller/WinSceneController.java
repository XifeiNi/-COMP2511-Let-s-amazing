package com.unsw.tilegame.scene_controller;

import com.unsw.tilegame.Main;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * winning screen controller
 * @author xiaoyang
 * @version 2.7
 * @since 1.5
 */
public class WinSceneController{
	private Stage stage;

	/**
	 * @param stage
	 */
	public WinSceneController(Stage stage) {
		if (stage == null) {
			throw new IllegalArgumentException("Stage cannot be null");
		}
		this.stage = stage;
	}


	/**
	 * @param e
	 */
	public void handleMousePressBack(MouseEvent e) {
		stage.setScene(Main.getScenes().get(SceneName.MAIN));
	}
	public void handleMousePress() {
		stage.setScene(Main.getScenes().get(SceneName.GAMEWIN));
	}
	
	public void handleMousePressOne() {
		stage.setScene(Main.getScenes().get(SceneName.GAMEWIN1));
	}
	
	public void handleOnPressButtonAnotherBattle() {
		stage.setScene(Main.getScenes().get(SceneName.GAMESCENE));
	}
}
