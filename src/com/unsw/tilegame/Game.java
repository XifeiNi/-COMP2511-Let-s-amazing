package com.unsw.tilegame;

import com.unsw.tilegame.entities.creatures.Player;
import com.unsw.tilegame.entities.statics.Rock;
import com.unsw.tilegame.input.KeyManagerJavaFx;
import com.unsw.tilegame.input.MouseManagerJavaFx;
import com.unsw.tilegame.scene_controller.EndSceneController;
import com.unsw.tilegame.scene_controller.WinSceneController;
import com.unsw.tilegame.tool.Assets;
import com.unsw.tilegame.tool.GameCamera;
import com.unsw.tilegame.view.GameScreen;
import com.unsw.tilegame.worlds.WorldManager;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/*
 * This class manage all the managers of games
 * @author Cecilia Xifei Ni && Xiaoyang Yan
 * @version 2.1
 * @since 1.0
 */
public class Game{
	private int widths = 1280;
	private int heights = 720;
	private KeyManagerJavaFx keyManagerJF;
	private MouseManagerJavaFx mouseManagerJF;
	private GraphicsContext g;
	private Handler handler = new Handler(this);
	private GameCamera gameCamera;
	private GameEngine gameEngine;
	private Stage stage;
	public static boolean isWin = false;
	public static int worldId = 0;
	public static boolean GameOver = false;
	private WorldManager worldManager;
	public Game() {}
	/*
	 * the constructor of game
	 * constructing all manager and stage
	 */
	public Game(Stage stage,KeyManagerJavaFx keyManagerJF,MouseManagerJavaFx mouseManagerJF) {
		initialize();
		this.keyManagerJF = keyManagerJF;
		this.mouseManagerJF = mouseManagerJF;
		this.stage = stage;
	}
	/*
	 * this function manages winning and losing of the game
	 * it also manages various maps to proceed different turns
	 * @see if player lose, the lose screen, if win, the winning screen
	 */
	public void tick() {
		WorldManager.worlds[worldId].getWorld().tick();
		if (!WorldManager.worlds[worldId].getWorld().getPlayer().isActive()) {
			new EndSceneController(stage).handleMousePress();
			worldManager.worldRestart();
		}
		if (Game.isWin) {
			if (worldId == 0) {
				new WinSceneController(stage).handleMousePress();
			} else if (worldId == 1 || worldId == 4) {
				new WinSceneController(stage).handleMousePressOne();
			}
		}
		if (gameIsWinForMapTreasure() && worldId == 2) {
			new WinSceneController(stage).handleMousePressOne();
		}
		if (gameIsWinForMapStone() && worldId == 3) {
			new WinSceneController(stage).handleMousePressOne();
		}
	}
	/*
	 * this aims to get handler from outside
	 * handler handles all events in game
	 * @return handler of the game
	 */
	public Handler getHandler() {
		return handler;
	}
	/*
	 * this function manages graph display of the game
	 * @see all the scene of the game
	 */
	public void render() {
		g = GameScreen.canvas.getGraphicsContext2D();
		g.clearRect(0, 0, widths,heights);
		g.setStroke(Color.BLUE);
		WorldManager.worlds[worldId].getWorld().render(g);
	}
	/*
	 * this is the initalization function of all games
	 * initialize all the pictures in res folder
	 * initialize world, camera, handler, and engine...
	 */
	public void initialize() {
		Assets.init();
		handler = new Handler(this);
		gameCamera = new GameCamera(handler,0,0);
		gameEngine = new GameEngine(15,this);
		worldManager = new WorldManager(handler);
		worldId = 0;
		handler.setWorld(WorldManager.worlds[worldId].getWorld());
	}
	/*
	 * getter function of game camera
	 * @return current camera of game
	 */
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	/*
	 * getter function of mouse manager java fx
	 * @return mouse manager of java fx
	 */
	public MouseManagerJavaFx getMouseManagerJF() {
		return mouseManagerJF;
	}
	/*
	 * getter function of key manager java fx
	 * @return key manager of java fx
	 */
	public KeyManagerJavaFx getKeyManagerJF() {
		return this.keyManagerJF;
	}
	/*
	 * getter function of width of game
	 * @return width of the game
	 */
	public double getWidths() {
		return widths;
	}
	/*
	 * getter function for the height of canvas
	 * @return height of canvas
	 */
	public double getHeights() {
		return heights;
	}
	/*
	 * setter function for game engine
	 * @param game engine that meant to be set
	 */
	public void setEngine(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
	}
	/*
	 * getter function for game engine
	 * @return game engine of current game
	 */
	public GameEngine getEngine() {
		return this.gameEngine;
	}
	/*
	 * this function is to determine whether game is win with treasure map
	 * @return whether game is win with stone map
	 */
	public boolean gameIsWinForMapTreasure(){
		if (Player.getTreasure() == 30){
			Game.isWin = true;
	  		return true;
	  	}
	  	return false;
	}
	/*
	 * getter function for world manager
	 * @return world manager of current game
	 */
	public WorldManager getWorldManager() {
		return worldManager;
	}
	/*
	 * this function is to determine whether game is win with stone map
	 * @return whether game is win with stone map
	 */
	public boolean gameIsWinForMapStone() {
		if(Rock.getRockSwitch() == 4) {
			Game.isWin = true;
			return true;
		}
		return false;
	}
}
