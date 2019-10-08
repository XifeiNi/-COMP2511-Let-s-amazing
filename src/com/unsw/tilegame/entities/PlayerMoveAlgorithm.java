package com.unsw.tilegame.entities;

import com.unsw.tilegame.entities.creatures.Player;

/**
 * algorithm for how player moves
 * @author cecilian
 * @version 2.9
 * @since 2.6
 */
public class PlayerMoveAlgorithm implements CreatureAlgorithm {
    Player player;

    /**
     * @param player
     */
    public PlayerMoveAlgorithm(Player player){
        this.player = player;
    }

    /**
     * @param xMove
     * @param yMove
     */
    @Override
    public void Move(float xMove,float yMove){
        if (!player.checkEntityCollisions(xMove,0f))
            moveX();
        if (!player.checkEntityCollisions(0f,yMove))
            moveY();
    }

    /**
     * move on x coordinate
     */
    public void moveX() {
        int tx = player.calculateTileXMove();
        if (tx != -1) {
            if (player.isHover()) {
                player.increaseXMove();
            } else if (player.calculateXMoveSituation(tx)) {
                if (player.calculateXMovePitSituation(tx)) {
                    player.increaseXMove();
                } else {
                    player.setX(player.calculateXMovePitCollisions(tx));
                    player.die();
                }
            } else {
                player.setX(player.calculateXMoveCollisions(tx));
            }
        }
    }

    /**
     * move on y coordinate
     */
    public void moveY() {
        int ty = player.calculateTileYMove();
        if (ty != -1) {
            if (player.isHover()) {
                player.increaseyMove();
            } else if (player.calculateYMoveSituation(ty)) {
                if (player.calculateYMovePitSituation(ty)) {
                    player.increaseyMove();
                } else {
                    player.setY(player.calculateYMovePitCollisions(ty));
                    player.die();
                }
            } else {
                player.setY(player.calculateYMoveCollisions(ty));
            }
        }
    }
}
