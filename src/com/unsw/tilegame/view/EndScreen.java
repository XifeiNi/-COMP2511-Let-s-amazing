package com.unsw.tilegame.view;

import com.unsw.tilegame.Game;
import com.unsw.tilegame.scene_controller.EndSceneController;

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
 * screen when user lose the game
 * @author xiaoyang
 * @version 2.7
 * @since 1.2
 * @see com.unsw.tilegame.view.SceneMaker
 */
public class EndScreen implements SceneMaker {
	private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    public Stage stage;
    public Game game;

	/**
	 * @param builder
	 */
    private EndScreen(EndScreenBuilder builder) {
    	this.stage = builder.stage;
    	this.game = builder.game;
    }

	/**
	 * builder patter for end screen
	 * @author cecilian
	 * @version 2.8
	 * @since 2.8
	 */
    public static class EndScreenBuilder{
		public Stage stage;
		public Game game;
    	public EndScreenBuilder(Stage stage, Game game){
			this.stage = stage;
			this.game = game;
		}

		/**
		 * @return end screen object
		 */
		public EndScreen builder(){
    		return new EndScreen(this);
		}
	}

	/**
	 * @return current scene
	 */
	@Override
	public Scene getScene() {
		Pane root = new Pane(); 
    	ImageView imageView = new ImageView(new Image(getClass().getResource("/texture/gameover.jpg").toExternalForm()));
	    imageView.setFitWidth(WIDTH);
	    imageView.setFitHeight(HEIGHT);
	    root.getChildren().add(imageView);
    	root.setPrefSize(WIDTH, HEIGHT);
    	Title text = new Title("PLEASE ClICK AND BACK");
    	text.setTranslateX(440);
        text.setTranslateY(400);
        text.setOnMousePressed(e->{
        	new EndSceneController(stage).handleMousePressBack(e);
        	game.getKeyManagerJF().keyManagerRestart();
        	game.getWorldManager().worldRestart();
        	game.getEngine().gameLoop.pause();
        });
        root.getChildren().add(text);
        root.requestFocus();
        Game.GameOver = true;
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
	 * setting stage
	 * @param stage
	 */
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	/**
	 * title again
	 * @author xiaoyang
	 */
	private static class Title extends StackPane{
		public Title(String name) {
			
			Text text = new Text(name);
			text.setFill(Color.GOLD);
			text.setFont(Font.font("Times New Roman",FontWeight.SEMI_BOLD,50));
			
			setAlignment(Pos.CENTER);
			getChildren().addAll(text);
		}
	}
}
