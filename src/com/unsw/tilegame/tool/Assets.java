package com.unsw.tilegame.tool;



import java.io.IOException;
//<a href = https://github.com/CodeNMore/New-Beginner-Java-Game-Programming-Src/blob/master/Episode%2034/TileGame/src/dev/codenmore/tilegame/gfx/Assets.java>

import javafx.scene.image.Image;
import javafx.scene.text.Font;

/**
 * class that deals witl all the images/ and resources
 * @author cecilian && xiaoyang
 * @version 2.8
 * @since 1.0
 */
public class Assets{
	private static final int width = 32, height = 32, player_width = 64, player_height = 64;
	public static Image dirt,grass,stone,tree,rock,wood,sword,arrow,dogmeat,switchPlace,pit,gold,key,bomb,invincible,hover,exit;
	public static Image[] player_down, player_archer_down;
	public static Image[] player_up, player_archer_up;
	public static Image[] player_left, player_archer_left;
	public static Image[] player_right,player_archer_right,select,select1,select2,goddessArrow,door;
	public static Image[] player_attack_down, player_attack_arrow_down;
	public static Image[] player_attack_up, player_attack_arrow_up;
	public static Image[] player_attack_left, player_attack_arrow_left;
	public static Image[] player_attack_right, player_attack_arrow_right;
	public static Image[] stratigist_up, stratigist_down, stratigist_left,stratigist_right;
	public static Image[] bomb_lit;
	public static Image[] hound_down,coward_down,hunter_down;
	public static Image[] hound_up,coward_up,hunter_up;
	public static Image[] hound_left,coward_left,hunter_left;
	public static Image[] hound_right,coward_right,hunter_right;
	public static Image inventoryScreen;
	public static Font font28, font120, font15,font58,font28new,font28new1,font28new2,font28new3;

	/**
	 * initializing them....
	 * ooops so much resources
	 */
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/texture/sheet.png"));
		inventoryScreen = ImageLoader.loadImage("/texture/inventoryScreen.png");
		wood = sheet.crop(width, height, width, height);
		select = new Image[2];
		select1 = new Image[2];
		select2 = new Image[2];
		door = new Image[2];
		goddessArrow = new Image[2];
		select[0] = ImageLoader.loadImage("/texture/choose.png");
		select[1] = ImageLoader.loadImage("/texture/selected.png");
		
		select1[0] = ImageLoader.loadImage("/texture/choose.png");
		select1[1] = ImageLoader.loadImage("/texture/selected.png");
		
		select2[0] = ImageLoader.loadImage("/texture/choose.png");
		select2[1] = ImageLoader.loadImage("/texture/selected.png");
		
		goddessArrow[0] = ImageLoader.loadImage("/texture/goddessShotUp.png");
		goddessArrow[1] = ImageLoader.loadImage("/texture/goddessShotLine.png");
		
		door[0] = ImageLoader.loadImage("/texture/closed_door.png");
		door[1] = ImageLoader.loadImage("/texture/open_door.png");
		
		hound_down = new Image[4];
		hound_up = new Image[4];
		hound_left = new Image[4];
		hound_right = new Image[4];
		dogmeat = ImageLoader.loadImage("/texture/dogMeat.png");
		pit = ImageLoader.loadImage("/texture/shaft.png");
		gold = ImageLoader.loadImage("/texture/gold_pile.png");
		bomb = ImageLoader.loadImage("/texture/bomb_unlit.png");
		arrow = ImageLoader.loadImage("/texture/arrow.png");
		sword = ImageLoader.loadImage("/texture/sword.png");
		switchPlace = ImageLoader.loadImage("/texture/switch.png");
		key = ImageLoader.loadImage("/texture/key.png");
		invincible = ImageLoader.loadImage("/texture/brilliant_blue_new.png");
		hover = ImageLoader.loadImage("/texture/bubbly.png");
		
		bomb_lit = new Image[5];
		bomb_lit[0] = ImageLoader.loadImage("/texture/bomb_lit_1.png");
		bomb_lit[1] = ImageLoader.loadImage("/texture/bomb_lit_2.png");
		bomb_lit[2] = ImageLoader.loadImage("/texture/bomb_lit_3.png");
		bomb_lit[3] = ImageLoader.loadImage("/texture/bomb_lit_4.png");
		bomb_lit[4] = ImageLoader.loadImage("/texture/bomb_lit_4.png");
		
		player_down = new Image[4];
		player_up = new Image[4];
		player_left = new Image[4];
		player_right = new Image[4];
		
		coward_down = new Image[4];
		coward_up = new Image[4];
		coward_left = new Image[4];
		coward_right = new Image[4];
		
		stratigist_down = new Image[4];
		stratigist_up = new Image[4];
		stratigist_left = new Image[4];
		stratigist_right = new Image[4];
		
		hunter_down = new Image[4];
		hunter_up = new Image[4];
		hunter_left = new Image[4];
		hunter_right = new Image[4];
		
		player_archer_down = new Image[4];
		player_archer_up = new Image[4];
		player_archer_left = new Image[4];
		player_archer_right = new Image[4];
		
		player_attack_down = new Image[4];
		player_attack_up = new Image[4];
		player_attack_left = new Image[4];
		player_attack_right = new Image[4];
		
		player_attack_arrow_down = new Image[4];
		player_attack_arrow_up = new Image[4];
		player_attack_arrow_left = new Image[4];
		player_attack_arrow_right = new Image[4];
		
		SpriteSheet houndSheet = new SpriteSheet(ImageLoader.loadImage("/texture/hound.png"));
		SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("/texture/hero3.png"));
		SpriteSheet playerArrowSheet = new SpriteSheet(ImageLoader.loadImage("/texture/archer.png"));
		SpriteSheet playerAttackSheet = new SpriteSheet(ImageLoader.loadImage("/texture/attack1.png"));
		SpriteSheet playerAttackArrowSheet = new SpriteSheet(ImageLoader.loadImage("/texture/archerattack.png"));
		SpriteSheet stratigistSheet = new SpriteSheet(ImageLoader.loadImage("/texture/wolfman.png"));
		SpriteSheet cowardSheet = new SpriteSheet(ImageLoader.loadImage("/texture/coward.png"));
		SpriteSheet hunterSheet = new SpriteSheet(ImageLoader.loadImage("/texture/hunter.png"));
		// cropping..........
		for (int i = 0; i < 4; i++) {
			player_down[i] = playerSheet.crop(i*player_width, 0, player_width, player_height); 
		}
		for (int i = 0; i < 4; i++) {
			player_left[i] = playerSheet.crop(i*player_width, player_height, player_width, player_height);
		}
		for (int i = 0; i < 4; i++) {
			player_right[i] = playerSheet.crop(i*player_width, 2*player_height, player_width, player_height);
		}
		for (int i = 0; i < 4; i++) {
			player_up[i] = playerSheet.crop(i*player_width, 3*player_height, player_width, player_height);
		}
		for (int i = 0; i < 4; i++) {
			hound_left[i] = houndSheet.crop(i*player_width, 0, player_width, player_height); 
		}
		for (int i = 0; i < 4; i++) {
			hound_right[i] = houndSheet.crop(i*player_width, player_height, player_width, player_height);
		}
		for (int i = 0; i < 4; i++) {
			hound_down[i] = houndSheet.crop(i*player_width, 2*player_height, player_width, player_height);
		}
		for (int i = 0; i < 4; i++) {
			hound_up[i] = houndSheet.crop(i*player_width, 3*player_height, player_width, player_height);
		}
		for (int i = 0; i < 4; i++) {
			player_attack_down[i] = playerAttackSheet.crop(i*(player_width+16), 0, player_width+16, player_height+16); 
		}
		for (int i = 0; i < 4; i++) {
			player_attack_left[i] = playerAttackSheet.crop(i*(player_width+16), player_height+16, player_width+16, player_height+16);
		}
		for (int i = 0; i < 4; i++) {
			player_attack_right[i] = playerAttackSheet.crop(i*(player_width+16), 2*(player_height+16), player_width+16, player_height+16);
		}
		for (int i = 0; i < 4; i++) {
			player_attack_up[i] = playerAttackSheet.crop(i*(player_width+16), 3*(player_height+16), player_width+16, player_height+16);
		}
		for (int i = 0; i < 4; i++) {
			player_attack_arrow_down[i] = playerAttackArrowSheet.crop(i*player_width, 0, player_width, player_height); 
		}
		for (int i = 0; i < 4; i++) {
			player_attack_arrow_left[i] = playerAttackArrowSheet.crop(i*player_width, player_height, player_width, player_height);
		}
		for (int i = 0; i < 4; i++) {
			player_attack_arrow_right[i] = playerAttackArrowSheet.crop(i*player_width, 2*player_height, player_width, player_height);
		}
		for (int i = 0; i < 4; i++) {
			player_attack_arrow_up[i] = playerAttackArrowSheet.crop(i*player_width, 3*player_height, player_width, player_height);
		}
		for (int i = 0; i < 4; i++) {
			player_archer_down[i] = playerArrowSheet.crop(i*player_width, 0, player_width, player_height); 
		}
		for (int i = 0; i < 4; i++) {
			player_archer_left[i] = playerArrowSheet.crop(i*player_width, player_height, player_width, player_height);
		}
		for (int i = 0; i < 4; i++) {
			player_archer_right[i] = playerArrowSheet.crop(i*player_width, 2*player_height, player_width, player_height);
		}
		for (int i = 0; i < 4; i++) {
			player_archer_up[i] = playerArrowSheet.crop(i*player_width, 3*player_height, player_width, player_height);
		}
		for (int i = 0; i < 4; i++) {
			stratigist_down[i] = stratigistSheet.crop(i*51, 0, 51, 70); 
		}
		for (int i = 0; i < 4; i++) {
			stratigist_left[i] = stratigistSheet.crop(i*51, 70, 51, 70); 
		}
		for (int i = 0; i < 4; i++) {
			stratigist_right[i] = stratigistSheet.crop(i*51, 140, 51, 70); 
		}
		for (int i = 0; i < 4; i++) {
			stratigist_up[i] = stratigistSheet.crop(i*51, 210, 51, 70); 
		}
		for (int i = 0; i < 4; i++) {
			coward_down[i] = cowardSheet.crop(i*player_width, 0, player_width, player_height); 
		}
		for (int i = 0; i < 4; i++) {
			coward_left[i] = cowardSheet.crop(i*player_width, player_height, player_width, player_height);
		}
		for (int i = 0; i < 4; i++) {
			coward_right[i] = cowardSheet.crop(i*player_width, 2*player_height, player_width, player_height);
		}
		for (int i = 0; i < 4; i++) {
			coward_up[i] = cowardSheet.crop(i*player_width, 3*player_height, player_width, player_height);
		}
		for (int i = 0; i < 4; i++) {
			hunter_down[i] = hunterSheet.crop(i*player_width, 0, player_width, player_height); 
		}
		for (int i = 0; i < 4; i++) {
			hunter_left[i] = hunterSheet.crop(i*player_width, player_height, player_width, player_height);
		}
		for (int i = 0; i < 4; i++) {
			hunter_right[i] = hunterSheet.crop(i*player_width, 2*player_height, player_width, player_height);
		}
		for (int i = 0; i < 4; i++) {
			hunter_up[i] = cowardSheet.crop(i*player_width, 3*player_height, player_width, player_height);
		}
		try {
			font28 = FontLoader.loadFont("slkscr.ttf", 28);
			font15 = FontLoader.loadFont("slkscr.ttf", 15);
			font58 = FontLoader.loadFont("slkscr.ttf", 58);
			font120 = FontLoader.loadFont("slkscr.ttf", 120);
			font28new = FontLoader.loadFont("Penumbra-HalfSerif-Std_35114.ttf", 120);
			font28new1 = FontLoader.loadFont("Penumbra-HalfSerif-Std_35114.ttf", 60);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		dirt = sheet.crop(width, 0, width, height);
		grass = sheet.crop(width * 2, 0, width, height);
		stone = sheet.crop(width * 3, 0, width, height);
		tree = sheet.crop(0, 0, width, height * 2);
		rock = ImageLoader.loadImage("/texture/boulder.png");
		exit = ImageLoader.loadImage("/texture/exit.png");
	}
}
