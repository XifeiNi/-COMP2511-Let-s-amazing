package com.unsw.tilegame.scene_controller;

import com.unsw.tilegame.Main;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * page when launching the game
 *
 * @author xiaoyang
 * @version 2.7
 * @since 1.5
 */
public class MainController{
	private Stage stage;

	/**
	 * @param stage
	 */
	public MainController(Stage stage) {
		this.stage = stage;
	}

	/**
	 * @param event
	 */
	public void handleOnPressButton(MouseEvent event) {
		stage.setScene(Main.getScenes().get(SceneName.GAMESCENE));
	}

	/**
	 * @param event
	 */
	public void handleOnPressButtonSetting(MouseEvent event) {
		stage.setScene(Main.getScenes().get(SceneName.SETTING));
	}
}
