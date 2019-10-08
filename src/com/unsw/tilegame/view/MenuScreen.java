package com.unsw.tilegame.view;

import com.unsw.tilegame.Game;
import com.unsw.tilegame.scene_controller.MainController;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * menu screen when launching the game
 * @author xiaoyang
 * @version 2.8
 * @since 1.8
 * @see com.unsw.tilegame.view.SceneMaker
 */
public class MenuScreen implements SceneMaker {
	private Stage stage;
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	public Game game;

	/**
	 * @param builder
	 */
	private MenuScreen(MenuScreenBuilder builder) {
		this.stage = builder.stage;
		this.game = builder.game;
	}

	/**
	 * builder pattern for scene
	 * @author cecilian
	 * @version 2.8
	 * @since 2.8
	 */
	public static class MenuScreenBuilder{
		private Stage stage;
		public Game game;
		public MenuScreenBuilder(Stage stage, Game game){
			this.stage = stage;
			this.game = game;
		}
		public MenuScreen builder(){
			return new MenuScreen(this);
		}
	}

	/**
	 * @return currnt scene
	 */
	@Override
	public Scene getScene() {
		MainController controller = new MainController(stage);
		Pane root = createContent();
		MenuItem gameContinue = new MenuItem("CONTINUE"); 
		MenuItem gameStart = new MenuItem("NEW GAME");
		gameStart.setOnMousePressed(e -> {
			controller.handleOnPressButton(e);
			game.getEngine().gameLoop.play();
			game.initialize();
		});
		MenuItem exit = new MenuItem("Exit"); 
		MenuItem setting = new MenuItem("Setting");
		setting.setOnMousePressed(e->{
			controller.handleOnPressButtonSetting(e);
		});
		exit.setOnMousePressed(e->{
			Platform.exit();
		});
		MenuBox vbox = new MenuBox(
			gameContinue,
			gameStart,
			setting,
			exit
		);
		Game.GameOver = false;
		vbox.setTranslateX(120);
		vbox.setTranslateY(335);
		root.setPrefSize(1280,720);
		root.getChildren().add(vbox);
		root.requestFocus();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		
		return scene;
	}
	
	private Pane createContent() {
		Pane root =new Pane();
		root.setPrefSize(1280,720);
		ImageView imageView = new ImageView(new Image(getClass().getResource("/texture/mainmenu.jpg").toExternalForm()));
	    imageView.setFitWidth(WIDTH);
	    imageView.setFitHeight(HEIGHT);
	    root.getChildren().add(imageView);
	    Title title = new Title("LET'S  A  MAZING");
		title.setTranslateX(70);
        title.setTranslateY(150);
		root.getChildren().add(title);
		return root;
	}

	/**
	 * the title class
	 * manage title of the starting page
	 * @author xiaoyang
	 * @since 1.9
	 * @version 1.9
	 */
	private static class Title extends StackPane{
		public Title(String name) {
			Rectangle bounds =  new Rectangle(500,100);
			bounds.setStroke(Color.WHITE);
			bounds.setStrokeWidth(2);
			
			Text text = new Text(name);
			text.setFill(Color.GOLD);
			text.setFont(Font.font("Times New Roman",FontWeight.SEMI_BOLD, 50));
			
			setAlignment(Pos.CENTER);
			getChildren().addAll(bounds,text);
		}
	}

	/**
	 * menubox class
	 * @author cecilian
	 * @version 2.0
	 * @since 2.0
	 */
	private static class MenuBox extends VBox {
		public MenuBox(MenuItem...items) {
			getChildren().add(seperator());
			for (MenuItem item:items) {
				getChildren().addAll(item,seperator());
			}
		}

		/**
		 * @return seperator of line
		 */
		private Line seperator() {
			Line sep = new Line();
			sep.setEndX(210);
			sep.setStroke(Color.DARKGRAY);
			return sep;
		}
	}
}
