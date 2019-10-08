package com.unsw.tilegame.entities.enemy;

import com.unsw.tilegame.entities.CreatureAlgorithm;
import com.unsw.tilegame.entities.creatures.Player;

/**
 * algorithem for enemies to move when player on invincible
 * @author  xiaoyang
 */
public class PlayerOnInvincibleMoveAlgorithm implements CreatureAlgorithm {
    private Enemy coward;

    /**
     * @param enemy
     */
    public PlayerOnInvincibleMoveAlgorithm(Enemy enemy){
        this.coward = enemy;
    }


    /**
     * @param xMove
     * @param yMove
     */
    @Override
    public void Move(float xMove, float yMove) {
        double playerX = coward.getPlayerX();
        double playerY = coward.getPlayerY();
        if (Player.getInvincible()) {
            if (playerX < coward.getX()) {
                if (!coward.checkEntityCollisions(xMove, 0)) {
                    coward.changeEnemyXMove(coward.getDefaultSpeed());
                    coward.moveX();
                } else {
                    coward.changeEnemyXMove((float) (0 - 0.2 * coward.getDefaultSpeed()));
                    coward.moveX();
                }
            }
            if (playerX > coward.getX()) {
                if (!coward.checkEntityCollisions(xMove, 0)) {
                    coward.changeEnemyXMove(0 - coward.getDefaultSpeed());
                    coward.moveX();
                } else {
                    coward.changeEnemyXMove((float) (0.2 * coward.getDefaultSpeed()));
                    coward.moveX();
                }
            }
            if (playerY > coward.getX()) {
                if (!coward.checkEntityCollisions(0, yMove)) {
                    coward.changeEnemyYMove(0 - coward.getDefaultSpeed());
                    coward.moveY();
                } else {
                    coward.changeEnemyYMove((float) (0.2 * coward.getDefaultSpeed()));
                    coward.moveY();
                }
            }
            if (playerY < coward.getY()) {
                if (!coward.checkEntityCollisions(0, yMove)) {
                    coward.changeEnemyYMove(coward.getDefaultSpeed());
                    coward.moveY();
                } else {
                    coward.changeEnemyYMove((float) (0 - 0.2 * coward.getDefaultSpeed()));
                    coward.moveY();
                }
            }
        }
    }
}
