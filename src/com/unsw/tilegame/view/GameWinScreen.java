package com.unsw.tilegame.view;

import com.unsw.tilegame.Game;
import com.unsw.tilegame.scene_controller.WinSceneController;
import com.unsw.tilegame.tool.Assets;
import com.unsw.tilegame.worlds.WorldManager;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * victory screen when user wins
 * @author cecilian
 * @version 2.8
 * @since 2.8
 * @see com.unsw.tilegame.view.SceneMaker
 */
public class GameWinScreen implements SceneMaker {

	private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    public Stage stage;
    public Game game;
    private int screenId;

	/**
	 * @param builder
	 */
    private GameWinScreen(GameWinScreenBuilder builder) {
    	this.stage = builder.stage;
    	this.game = builder.game;
    	this.screenId = builder.screenId;
    }

	/**
	 * builder pattern for scene
	 * so that the original class constructor can be private
	 * @author cecilian
	 * @version 2.8
	 * @since 2.7
	 */
	public static class GameWinScreenBuilder{
		public Stage stage;
		public Game game;
		private int screenId;
		public GameWinScreenBuilder(Stage stage, Game game, int id){
			this.stage = stage;
			this.game = game;
			this.screenId = id;
		}
		public GameWinScreen builder(){
			return new GameWinScreen(this);
		}

	}

	/**
	 * @return current scene
	 */
	@Override
	public Scene getScene() {
		Pane root = new Pane(); 
    	ImageView imageView = new ImageView(new Image(getClass().getResource("/texture/godofwar.jpg").toExternalForm()));
	    imageView.setFitWidth(WIDTH);
	    imageView.setFitHeight(HEIGHT);
	    root.getChildren().add(imageView);
    	root.setPrefSize(WIDTH, HEIGHT);
    	Text title = new Text("V I C T O R Y");
    	title.setFill(Color.GOLD);
    	title.setFont(Assets.font28new);
    	title.setX(260);
    	title.setY(200);
    	root.getChildren().add(title);
    	if (screenId == 0) {
    		Text text = null;
    		text = new Text("PLEASE ClICK AND CONTINUE ANOTHER BATTLE");
	    	text.setTranslateX(300);
	        text.setTranslateY(540);
	        text.setFont(Assets.font28);
	        text.setFill(Color.WHITE);
	        text.setOnMousePressed(e->{
	        	Game.isWin = false;
	        	Game.worldId = 1;
	        	game.getWorldManager().worldRestart();
	        	game.getKeyManagerJF().keyManagerRestart();
	        	game.getHandler().setWorld(WorldManager.worlds[1].getWorld());
	        	new WinSceneController(stage).handleOnPressButtonAnotherBattle();
	        });
	        root.getChildren().add(text);
    	} else if (screenId == 1) {
    		Title text = new Title("PLEASE ClICK AND BACK");
        	text.setTranslateX(370);
            text.setTranslateY(480);
            text.setOnMousePressed(e->{
            	Game.isWin = false;
            	game.getWorldManager().worldRestart();
            	game.getKeyManagerJF().keyManagerRestart();
            	game.getHandler().setWorld(WorldManager.worlds[0].getWorld());
            	game.getEngine().gameLoop.pause();
            	new WinSceneController(stage).handleMousePressBack(e);
            });
            root.getChildren().add(text);
    	}
        root.requestFocus();
        Game.GameOver = true;
        Text bot = new Text("Congratulations, hope peace and love everyday");
        bot.setFill(Color.RED);
        bot.setFont(Assets.font28);
        bot.setX(220);
        bot.setY(620);
        root.getChildren().add(bot);
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		return scene;
	}

	/**
	 * @return current stage
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 * setter function fot setting the stage
	 * @param stage
	 */
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	/**
	 * title class again...
	 */
	private static class Title extends StackPane{
		public Title(String name) {
			Text text = new Text(name);
			text.setFill(Color.SEASHELL);
			text.setFont(Font.font("Times New Roman",FontWeight.SEMI_BOLD,50));
			setAlignment(Pos.CENTER);
			getChildren().addAll(text);
		}
	}
}