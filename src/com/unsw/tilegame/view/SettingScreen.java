package com.unsw.tilegame.view;

import com.unsw.tilegame.Game;
import com.unsw.tilegame.scene_controller.SettingSceneController;
import com.unsw.tilegame.tool.Assets;
import com.unsw.tilegame.worlds.WorldManager;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Setting screen for user's preference on map
 * @author cecilian && Xiaoyang
 * @since 2.7
 * @version 2.7
 * @see com.unsw.tilegame.view.SceneMaker
 */
public class SettingScreen implements SceneMaker{
	private Game game;
	private static int widths = 1280;
	private static int heights = 720;
	private Stage stage;

	/**
	 * @param builder
	 */
	private SettingScreen(SettingScreenBuilder builder) {
		this.game = builder.game;
		this.stage = builder.stage;
	}

	/**
	 * Builder pattern for setting screen
	 * @author cecilian
	 * @version 2.8
	 * @since 2.8
	 */
	public static class SettingScreenBuilder{
		private Game game;
		private Stage stage;
		public SettingScreenBuilder(Stage stage, Game game){
			this.game = game;
			this.stage = stage;
		}

		/**
		 * @return setting screen object
		 */
		public SettingScreen builder(){
			return new SettingScreen(this);
		}
	}


	/**
	 * getter function of scene
	 * @return current scene
	 */
	@Override
	public Scene getScene() {
		Pane root = new Pane();
		ImageView imageView = new ImageView(new Image(getClass().getResource("/texture/mainmenu1.jpg").toExternalForm()));
	    imageView.setFitWidth(widths);
	    imageView.setFitHeight(heights);
	    root.getChildren().add(imageView);
    	root.setPrefSize(widths, heights);
    	VBox vbox = new VBox();
    	vbox.setTranslateX(440);
    	vbox.setTranslateY(100);
    	vbox.setPrefSize(1000, 700);
    	vbox.setSpacing(60);
    	final ToggleGroup group = new ToggleGroup();
    	RadioButton rb1 = new RadioButton("Treasure");
    	ImageView imageButton = new ImageView(new Image(getClass().getResource("/texture/choose.png").toExternalForm()));
    	rb1.setGraphic(imageButton);
    	rb1.setToggleGroup(group);
    	rb1.setPrefSize(360, 80);
    	rb1.setFont(Assets.font28);
    	rb1.setTextFill(Color.GREEN);
    	RadioButton rb2 = new RadioButton("Stone");
    	rb2.setGraphic(imageButton);
    	rb2.setToggleGroup(group);
    	rb2.setPrefSize(260, 80);
    	rb2.setTextFill(Color.PINK);
    	rb2.setFont(Assets.font28);
    	RadioButton rb3 = new RadioButton("Enemy");
    	rb3.setGraphic(imageButton);
    	rb3.setToggleGroup(group);
    	rb3.setPrefSize(260, 80);
    	rb3.setFont(Assets.font28);
    	rb3.setTextFill(Color.PURPLE);
    	Label label = new Label("BACK");
    	label.setFont(Assets.font28);
    	ImageView labelImage = new ImageView(new Image(getClass().getResource("/texture/mart.png").toExternalForm()));
    	label.setGraphic(labelImage);
    	label.setAlignment(Pos.CENTER);
    	label.setGraphicTextGap(-250);
    	label.setTextFill(Color.GOLD);
    	label.setTextAlignment(TextAlignment.CENTER);
    	label.setLayoutX(100);
    	label.setLayoutY(600);
    	Label label2 = new Label("START");
    	ImageView labelImage2 = new ImageView(new Image(getClass().getResource("/texture/mart.png").toExternalForm()));
    	label2.setGraphic(labelImage2);
    	label2.setGraphicTextGap(-250);
    	label2.setFont(Assets.font28);
    	label2.setTextFill(Color.GOLD);
    	label2.setLayoutX(700);
    	label2.setLayoutY(600);
    	label2.setAlignment(Pos.CENTER);
    	label2.setTextAlignment(TextAlignment.CENTER);
    	vbox.getChildren().addAll(rb1,rb2,rb3);
    	root.getChildren().add(vbox);
    	label2.addEventHandler(MouseEvent.MOUSE_CLICKED, event->{
    		if (rb1.isSelected()) {
    			Game.worldId = 2;
    			game.getWorldManager().worldRestart();
    			game.getHandler().setWorld(WorldManager.worlds[Game.worldId].getWorld());
    			game.getEngine().gameLoop.play();
    			new SettingSceneController(stage).handleMousePress();
    		} else if (rb2.isSelected()) {
    			Game.worldId = 3;
    			game.getWorldManager().worldRestart();
    			game.getHandler().setWorld(WorldManager.worlds[Game.worldId].getWorld());
    			game.getEngine().gameLoop.play();
    			new SettingSceneController(stage).handleMousePress();
    		} else if (rb3.isSelected()) {
    			Game.worldId = 4;
    			game.getWorldManager().worldRestart();
    			game.getHandler().setWorld(WorldManager.worlds[Game.worldId].getWorld());
    			game.getEngine().gameLoop.play();
    			new SettingSceneController(stage).handleMousePress();
    		}
    	});
    	label.addEventHandler(MouseEvent.MOUSE_CLICKED, event->{
    		new SettingSceneController(stage).handleMousePressBack(event);
    	});
    	root.getChildren().addAll(label,label2);
    	
    	Scene scene = new Scene(root,widths,heights);
    	return scene;
	}
	
}
