package com.unsw.tilegame.view;

import com.unsw.tilegame.Game;
import com.unsw.tilegame.input.KeyManagerJavaFx;
import com.unsw.tilegame.input.MouseManagerJavaFx;
import com.unsw.tilegame.scene_controller.GameSceneController;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * game screen when user is playing game
 * @author xiaoyang
 * @version 2.1
 * @since 2.1
 * @see com.unsw.tilegame.view.SceneMaker
 */
public class GameScreen implements SceneMaker {
	private Game game;
	public static Canvas canvas;
	private static int widths = 1280;
	private static int heights = 720;
	private  KeyManagerJavaFx keyManagerJF;
	private MouseManagerJavaFx mouseManagerJF;
	private Stage stage;

	/**
	 * @param game
	 * @param keyManagerJF
	 * @param mouseManagerJF
	 * @param stage
	 */
	public GameScreen(Game game,KeyManagerJavaFx keyManagerJF,MouseManagerJavaFx mouseManagerJF, Stage stage) {
		canvas = new Canvas();
		this.game = game;
		this.keyManagerJF = keyManagerJF;
		this.mouseManagerJF = mouseManagerJF;
		this.stage = stage;
	}

	/**
	 * @return current scene
	 */
	@Override
	public Scene getScene() {
		Pane root = new Pane();
		canvas.setWidth(widths);
		canvas.setHeight(heights);
		canvas.setFocusTraversable(true);
		canvas.setOnKeyPressed(event -> keyManagerJF.handle(event));
        canvas.setOnKeyReleased(event -> keyManagerJF.handle(event));
		canvas.addEventHandler(KeyEvent.KEY_PRESSED,keyManagerJF);
		canvas.addEventHandler(KeyEvent.KEY_RELEASED,keyManagerJF);
		canvas.addEventHandler(MouseEvent.MOUSE_MOVED, mouseManagerJF);
		canvas.addEventHandler(MouseEvent.MOUSE_RELEASED, mouseManagerJF);
		canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, mouseManagerJF);
		System.out.println(Game.GameOver);
		root.getChildren().add(canvas);
		root.requestFocus();
		root.setOnKeyPressed(event->{
			if (event.getCode().equals(KeyCode.ESCAPE)){
				new GameSceneController(stage).handleMousePress(event);
			}
		});
		Game.GameOver = false;
		game.getEngine().gameLoop.play();
		Scene scene = new Scene(root, widths, heights);
		return scene;
	}

	/**
	 * @return current game
	 */
	public Game getGame() {
		return game;
	}
}
