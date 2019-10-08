package com.unsw.tilegame.view;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * menu item when lauchine the game
 * @author xiaoyang
 * @version 1.5
 * @since 1.0
 */
public class MenuItem extends StackPane {
	private  Runnable onStart = ()->{};
	public void setOnStart(Runnable OnStart) {
		onStart = OnStart;
	}
	public MenuItem(String name) {
		LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
				new Stop[] { new Stop(0, Color.DARKBLUE), new Stop(0.1, Color.BLACK), new Stop(0.9, Color.BLACK),
						new Stop(1, Color.DARKBLUE)
				});

		Rectangle bg = new Rectangle(200, 30);
		bg.setOpacity(0.4);

		Text text = new Text(name);
		text.setFill(Color.DARKGREY);
		text.setFont(Font.font("Times New Roman", FontWeight.SEMI_BOLD, 20));

		setAlignment(Pos.CENTER);
		getChildren().addAll(bg, text);
		setOnMouseEntered(event -> {
			bg.setFill(gradient);
			text.setFill(Color.WHITE);

		});

		setOnMouseExited(event -> {
			bg.setFill(Color.BLACK);
			text.setFill(Color.DARKGREY);
		});
		setOnMousePressed(event -> {
			bg.setFill(Color.DARKVIOLET);

		});
		setOnMouseReleased(event -> {
			bg.setFill(gradient);
		});
        setOnMouseClicked(event->onStart.run());
	}
}
