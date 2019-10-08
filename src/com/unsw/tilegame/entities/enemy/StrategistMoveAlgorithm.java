package com.unsw.tilegame.entities.enemy;

import com.unsw.tilegame.entities.CreatureAlgorithm;
import com.unsw.tilegame.tiles.Tile;
import javafx.scene.shape.Rectangle;

/**
 * moving algo for strategist
 * @author cecilian
 * @version 2.5
 * @since 2,4
 * @see com.unsw.tilegame.entities.CreatureAlgorithm
 */
public class StrategistMoveAlgorithm implements CreatureAlgorithm {
    private Strategist strategist;
    private int turnLeft = 0;
    private int turnRight = 0;
    private int turnUp = 0;
    private int turnDown = 0;
    private int direct = 0;

    /**
     * @param strategist
     */
    public StrategistMoveAlgorithm(Strategist strategist){
        this.strategist = strategist;
    }

    /**
     * @param xMove
     * @param yMove
     */
    @Override
    public void Move(float xMove, float yMove) {
        double yOriginal = strategist.getyOriginal();
        double xOriginal = strategist.getxOriginal();
        float x = strategist.getX();
        float y  = strategist.getY();
        Rectangle bounds = strategist.getBounds();
        float speed = strategist.getSpeed();

        if (strategist.getEnemyState())
            if (turnUp == 35) {
                turnUp = 0;
            }
        if (turnDown == 35) {
            turnDown = 0;
        }
        if (turnLeft == 35) {
            turnLeft = 0;
        }
        if (turnRight == 35) {
            turnRight = 0;
        }
        if (turnUp == 0 && turnLeft == 0 && turnRight == 0 && turnDown == 0)
            direct = (int)(Math.random()*4);
        switch(direct) {
            case 0:
                if (!strategist.checkEntityCollisions(0,0-speed) &&
                        y> yOriginal-200 && y < yOriginal+200) {
                    int tx = (int)(x+xMove+bounds.getX())/ Tile.TILEWIDTH;
                    if (strategist.calculateXMoveSituation(tx)) {
                        strategist.setY(y-=speed);
                        turnUp++;
                    } else {
                        strategist.setY(y+=speed);
                        turnUp = 0;
                    }
                } else {
                    strategist.setY(y+=speed*2);
                    turnUp = 0;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 1:
                if (!strategist.checkEntityCollisions(0,speed) && y <  yOriginal+200 && y>yOriginal-200) {
                    int tx = (int)(x+xMove+bounds.getX())/Tile.TILEWIDTH;
                    if (strategist.calculateXMoveSituation(tx)) {
                        strategist.setY(y+=speed);
                        strategist.setyMove(speed);
                        strategist.setxMove(0);
                        turnDown++;
                    } else {
                        strategist.setY(y-=speed);
                        strategist.setyMove(0- speed);
                        strategist.setxMove(0);
                        turnDown = 0;
                    }
                } else {
                    strategist.setY(y-=speed*2);
                    strategist.setyMove(0-speed);
                    turnDown = 0;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                if (!strategist.checkEntityCollisions(0-speed,0) && x < xOriginal+300 && x>xOriginal-300) {
                    int ty = (int)(y+yMove+bounds.getY())/Tile.TILEHEIGHT;
                    if (strategist.calculateYMoveSituation(ty)) {
                        strategist.setX(x-=speed);
                        strategist.setxMove(0- speed);
                        strategist.setyMove(0);
                        turnLeft++;
                    } else {
                        strategist.setX(x+=speed);
                        strategist.setxMove(speed);
                        strategist.setyMove(0);
                        turnLeft = 0;
                    }
                } else {
                    strategist.setX(x+=speed*2);
                    strategist.setxMove(speed);
                    turnLeft = 0;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                if (!strategist.checkEntityCollisions(speed,0) && x > xOriginal-300 && x < xOriginal+300 ) {
                    int ty = (int)(y+yMove+bounds.getY())/Tile.TILEHEIGHT;
                    if (strategist.calculateYMoveSituation(ty)) {
                        strategist.setX(x+=speed);
                        strategist.setxMove(speed);
                        strategist.setyMove(0);
                        turnRight++;
                    } else {
                        strategist.setX(x-=speed);
                        strategist.setxMove(0-speed);
                        strategist.setyMove(0);
                        turnRight = 0;
                    }
                } else {
                    strategist.setX(x-=speed*2);
                    strategist.setxMove(0-speed);
                    turnRight = 0;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}
