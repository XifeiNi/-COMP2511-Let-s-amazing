package com.unsw.tilegame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.util.Duration;
/*
 * @author Xiaoyang Yan
 * @version 1.3
 * @since 1.0
 * This class manage the time line of the game
 */
public class GameEngine{
	private int frameRate;
	private final Game game;
	public final Timeline gameLoop;
	/*
	 * constructor of game engine
	 */
	public GameEngine(int frameRate, Game game) {
	    this.frameRate = frameRate;
	    this.game = game;
	    game.setEngine(this);
	    gameLoop = createLoop();
	}
	/*
	 * this manages game's running process
	 * @param e is the game being called
	 */
	private void run(Event e) {
	    game.tick();
	    game.render();
	}
	/*
	 * this manages time line
	 * this class cannot be accessed outside
	 * @return timeline
	 */
	private Timeline createLoop() {
	    final Duration d = Duration.millis(1000 / frameRate);
	    final KeyFrame oneFrame = new KeyFrame(d, this::run);
	    Timeline t = new Timeline(frameRate, oneFrame);
	    t.setCycleCount(Timeline.INDEFINITE);
	    return t;
	}
}
