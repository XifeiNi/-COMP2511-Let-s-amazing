package com.unsw.tilegame;

import java.util.HashMap;
import java.util.Map;

import com.unsw.tilegame.input.KeyManagerJavaFx;
import com.unsw.tilegame.input.MouseManagerJavaFx;
import com.unsw.tilegame.scene_controller.SceneName;
import com.unsw.tilegame.view.EndScreen;
import com.unsw.tilegame.view.GameScreen;
import com.unsw.tilegame.view.GameWinScreen;
import com.unsw.tilegame.view.MenuScreen;
import com.unsw.tilegame.view.SettingScreen;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
/*
 * This is the main function of the whole project
 * @author Cecilia Xifei Ni && Xiaoyang yan
 * @version 2.6
 * @since 1.0
 */
public class Main extends Application{
	//a href<https://github.com/ksnortum/javafx-multi-scene/blob/master/src/net/snortum/javafx/multiscene/Main.java>
	private static Map<SceneName, Scene> scenes = new HashMap<>();
	public KeyManagerJavaFx keyManagerJF = new KeyManagerJavaFx();
	public MouseManagerJavaFx mouseManagerJF = new MouseManagerJavaFx();
	public int width = 1280,height = 720;
	/*
	 * this is to start the game
	 * initialize all the scene in javafx
	 * @see scene of the game
	 */
	@Override
	public void start(Stage stage) {
		stage.setTitle("Let US A Mazing!");
		stage.setWidth(width);
		stage.setHeight(height);
		stage.setResizable(false);
		Game game = new Game(stage,keyManagerJF,mouseManagerJF);
		scenes.put(SceneName.MAIN, new MenuScreen.MenuScreenBuilder(stage,game).builder().getScene());
		scenes.put(SceneName.SETTING, new SettingScreen.SettingScreenBuilder(stage,game).builder().getScene());
		scenes.put(SceneName.GAMESCENE, new GameScreen(game,keyManagerJF,mouseManagerJF,stage).getScene());
		scenes.put(SceneName.ENDSCENE, new EndScreen.EndScreenBuilder(stage,game).builder().getScene());
		scenes.put(SceneName.GAMEWIN,new GameWinScreen.GameWinScreenBuilder(stage,game,0).builder().getScene());
		scenes.put(SceneName.GAMEWIN1,new GameWinScreen.GameWinScreenBuilder(stage,game,1).builder().getScene());
		stage.setScene(scenes.get(SceneName.MAIN));
		stage.setTitle("LETS A MAZING");
		stage.show();
	}
	/*
	 * get scene according to name it has defined in enum
	 * @return scene object
	 */
	public static Map<SceneName, Scene> getScenes() {
		return scenes;
	}
	/*
	 * MAIN FUNCTION
	 * Launch game!
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
