package com.unsw.tilegame.entities.enemy;

import com.unsw.tilegame.entities.CreatureAlgorithm;

/**
 * moving algo for hunter
 * @author Cecilia
 * @version 2.5
 * @since 2.5
 */
public class HunterMoveAlgorithm implements CreatureAlgorithm{
    Hunter hunter;
    public HunterMoveAlgorithm(Hunter hunter){
        this.hunter = hunter;
    }

    /**
     * @param xMove
     * @param yMove
     */
    @Override
    public void Move(float xMove, float yMove) {
        if (hunter.getEnemyState()) {
            if (hunter.getPlayerX() < hunter.getX()) {
                if (!hunter.checkEntityCollisions(xMove, 0)) {
                    hunter.setxMove(0 - hunter.getDefaultSpeed());
                    hunter.moveX();
                }
            }
            if (hunter.getPlayerX() > hunter.getX()) {
                if (!hunter.checkEntityCollisions(xMove, 0)) {
                    hunter.setxMove(hunter.getDefaultSpeed());
                    hunter.moveX();
                }
            }
            if (hunter.getPlayerY() > hunter.getY()) {
                if (!hunter.checkEntityCollisions(0, yMove)) {
                    hunter.setyMove(hunter.getDefaultSpeed());
                    hunter.moveY();
                }
            }
            if (hunter.getPlayerY() < hunter.getY()) {
                if (!hunter.checkEntityCollisions(0, yMove)) {
                    hunter.setyMove(0 - hunter.getDefaultSpeed());
                    hunter.moveY();
                }
            }
        }
    }
}
