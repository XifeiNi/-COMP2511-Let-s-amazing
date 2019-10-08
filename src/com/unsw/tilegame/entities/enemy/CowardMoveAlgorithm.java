package com.unsw.tilegame.entities.enemy;

import com.unsw.tilegame.entities.CreatureAlgorithm;

/**
 *
 */
public class CowardMoveAlgorithm implements CreatureAlgorithm {
    private Coward coward;

    /**
     * @param coward
     */
    public CowardMoveAlgorithm(Coward coward){
        this.coward = coward;
    }

    /**
     * @param xMove
     * @param yMove
     */
    @Override
    public void Move(float xMove, float yMove) {
        double playerX = coward.getPlayerX();
        double playerY = coward.getPlayerY();
        boolean meetPlayer = coward.meetPlayer(playerX, playerY);
        if (coward.getCowardState()) {
            if (meetPlayer) {
                if (playerX<coward.getX()) {
                    if (!coward.checkEntityCollisions(xMove,0)) {
                        coward.changeCowardXMove(coward.getDefaultSpeed());
                        coward.moveX();
                    } else {
                        coward.changeCowardXMove((float)(0-0.2*coward.getDefaultSpeed()));
                        coward.moveX();
                    }
                }
                if (playerX>coward.getX()) {
                    if (!coward.checkEntityCollisions(xMove,0)) {
                        coward.changeCowardXMove(0-coward.getDefaultSpeed());
                        coward.moveX();
                    } else {
                        coward.changeCowardXMove((float) (0.2*coward.getDefaultSpeed()));
                        coward.moveX();
                    }
                }
                if (playerY>coward.getX()) {
                    if (!coward.checkEntityCollisions(0,yMove)) {
                        coward.changeCowardYMove(0-coward.getDefaultSpeed());
                        coward.moveY();
                    } else {
                        coward.changeCowardYMove((float)(0.2*coward.getDefaultSpeed()));
                        coward.moveY();
                    }
                }
                if (playerY<coward.getY()) {
                    if (!coward.checkEntityCollisions(0,yMove)) {
                        coward.changeCowardYMove(coward.getDefaultSpeed());
                        coward.moveY();
                    }  else {
                        coward.changeCowardYMove((float)(0-0.2*coward.getDefaultSpeed()));
                        coward.moveY();
                    }
                }
            } else {
                if (playerX < coward.getX()) {
                    if (!coward.checkEntityCollisions(xMove, 0)) {
                        coward.changeCowardXMove(0 - coward.getDefaultSpeed());
                        coward.moveX();
                    } else {
                        coward.changeCowardXMove((float) (0.2*coward.getDefaultSpeed()));
                        coward.moveX();
                    }
                }
                if (playerX > coward.getX()) {
                    if (!coward.checkEntityCollisions(xMove, 0)) {
                        coward.changeCowardXMove(coward.getDefaultSpeed());
                        coward.moveX();
                    } else {
                        coward.changeCowardYMove((float)(0-0.2*coward.getDefaultSpeed()));
                        coward.moveX();
                    }
                }
                if (playerY > coward.getY()) {
                    if (!coward.checkEntityCollisions(0, yMove)) {
                        coward.changeCowardYMove(coward.getDefaultSpeed());
                        coward.moveY();
                    } else {
                        coward.changeCowardYMove((float)(0-0.2*coward.getDefaultSpeed()));
                        coward.moveY();
                    }
                }
                if (playerY < coward.getY()) {
                    if (!coward.checkEntityCollisions(0, yMove)) {
                        coward.changeCowardYMove(0-coward.getDefaultSpeed());
                        coward.moveY();
                    } else {
                        coward.changeCowardYMove((float)(0.2*coward.getDefaultSpeed()));
                        coward.moveY();
                    }
                }
            }
            if (playerY == coward.getY() ||
                    playerX == coward.getX()) {
                coward.changeCowardXMove(0);
                coward.changeCowardYMove(0);
            }
        }
    }
}
