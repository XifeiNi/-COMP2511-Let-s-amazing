package com.unsw.tilegame.entities.enemy;

import com.unsw.tilegame.entities.CreatureAlgorithm;

/**
 * how hound dog moves
 * @author cecilia
 * @version 1.5
 * @since 1.4
 * @see com.unsw.tilegame.entities.CreatureAlgorithm
 */
public class HoundDogMoveAlgorithm implements CreatureAlgorithm {
    private HoundDog dog;
    private Hunter hunter;
    public HoundDogMoveAlgorithm(HoundDog dog, Hunter hunter){
        this.dog = dog;
        this.hunter = hunter;
    }

    /**
     * @param xMove
     * @param yMove
     */
    @Override
    public void Move(float xMove, float yMove) {
        boolean state = dog.getEnemyState();
        float y = dog.getY();
        float x = dog.getX();
        float speed = dog.getDefaultSpeed();
        if (state) {
            if (dog.getPlayerX() <= hunter.getX() && dog.getPlayerY()>=hunter.getY()) {
                if (y >= dog.getPlayerX() && x <= dog.getPlayerX()) {
                    dog.setxMove(speed);
                    if (!dog.checkEntityCollisions(xMove, 0)) {
                        dog.moveX();
                    }
                    dog.setyMove(0-speed);
                    if (!dog.checkEntityCollisions(0,yMove)) {
                        dog.moveY();
                    }
                } else if (y <= dog.getPlayerY() && x >= dog.getPlayerX() && y <= hunter.getY()){
                    dog.setyMove(0-2*speed);
                    if (!dog.checkEntityCollisions(xMove, 0)) {
                        dog.moveX();
                    }
                    dog.setyMove(2*speed);
                    if (!dog.checkEntityCollisions(0,yMove)) {
                        dog.moveY();
                    }
                } else if (y <= dog.getPlayerY() && x >= dog.getPlayerX() && y > hunter.getY()){
                   dog.setyMove((float)(0-2.2*speed));
                    if (!dog.checkEntityCollisions(xMove, 0)) {
                        dog.moveX();
                    }
                    dog.setyMove((float) (2.2*speed));
                    if (!dog.checkEntityCollisions(0,yMove)) {
                        dog.moveY();
                    }
                } else if (x <= dog.getPlayerX() && y <= dog.getPlayerY()){
                    dog.setxMove((float) (0.6*speed));
                    if (!dog.checkEntityCollisions(xMove, 0)) {
                        dog.moveX();
                    }
                   dog.setyMove ((float)(0.6*speed));
                    if (!dog.checkEntityCollisions(0,yMove)) {
                        dog.moveY();
                    }
                }  else if (x >= dog.getPlayerX() && y >= dog.getPlayerY()){
                    dog.setxMove(0-speed);
                    if (!dog.checkEntityCollisions(xMove, 0)) {
                        dog.moveX();
                    }
                }
            }
            if (dog.getPlayerX() >= hunter.getX() && dog.getPlayerY()<=hunter.getY()) {
                if (y >= dog.getPlayerY() && x <= dog.getPlayerX() && y<=hunter.getY()) {
                    dog.setxMove(2*speed);
                    if (!dog.checkEntityCollisions(xMove, 0)) {
                        dog.moveX();
                    }
                    yMove = 0-2*speed;
                    if (!dog.checkEntityCollisions(0,yMove)) {
                        dog.moveY();
                    }
                } else if (y >= dog.getPlayerY() && x <= dog.getPlayerX() && y>hunter.getY()) {
                    dog.setxMove((float) (2.2*speed));
                    if (!dog.checkEntityCollisions(xMove, 0)) {
                        dog.moveX();
                    }
                    dog.setyMove((float) (0-2.2*speed));
                    if (!dog.checkEntityCollisions(0,yMove)) {
                        dog.moveY();
                    }
                } else if (y <= dog.getPlayerY() && x >= dog.getPlayerX()){
                    dog.setxMove((float) (0-0.6*speed));
                    if (!dog.checkEntityCollisions(xMove, 0)) {
                        dog.moveX();
                    }
                    dog.setyMove((float)(0.6*speed));
                    if (!dog.checkEntityCollisions(0,yMove)) {
                        dog.moveY();
                    }
                } else if (x <=dog.getPlayerX() && y <= dog.getPlayerY()){
                    dog.setxMove(speed);
                    if (!dog.checkEntityCollisions(xMove, 0)) {
                        dog.moveX();
                    }
                }  else if (x >= dog.getPlayerX()&& y >= dog.getPlayerY()){
                    dog.setxMove(0-speed);
                    if (!dog.checkEntityCollisions(xMove, 0)) {
                        dog.moveX();
                    }
                    dog.setyMove(0-speed);
                    if (!dog.checkEntityCollisions(0,yMove)) {
                        dog.moveY();
                    }
                }
            }
            if (dog.getPlayerX()<hunter.getX() && dog.getPlayerY()<hunter.getY()) {
                if (y >= dog.getPlayerY() && x <= dog.getPlayerX()) {
                    dog.setxMove(speed);
                    if (!dog.checkEntityCollisions(xMove, 0)) {
                        dog.moveX();
                    }
                    dog.setyMove(0-speed);
                    if (!dog.checkEntityCollisions(0,yMove)) {
                        dog.moveY();
                    }
                } else if (y <= dog.getPlayerY() && x >= dog.getPlayerX()){
                    dog.setxMove(0-speed);
                    if (!dog.checkEntityCollisions(xMove, 0)) {
                        dog.moveX();
                    }
                    dog.setyMove(speed);
                    if (!dog.checkEntityCollisions(0,yMove)) {
                        dog.moveY();
                    }
                } else if (x <= dog.getPlayerX() && y <= dog.getPlayerY()){
                    dog.setxMove((float) (0.6*speed));
                    if (!dog.checkEntityCollisions(xMove, 0)) {
                        dog.moveX();
                    }
                    dog.setyMove((float) (0.6*speed));
                    if (!dog.checkEntityCollisions(0,yMove)) {
                        dog.moveY();
                    }
                }  else if (x >= dog.getPlayerX() && y >=dog.getPlayerY()&&y<=hunter.getY()){
                    dog.setxMove(0-2*speed);
                    if (!dog.checkEntityCollisions(xMove, 0)) {
                        dog.moveX();
                    }
                    dog.setyMove(0-2*speed);
                    if (!dog.checkEntityCollisions(0,yMove)) {
                        dog.moveY();
                    }
                }  else if (x >= dog.getPlayerX()&& y >= dog.getPlayerY()){
                    dog.setxMove((float) (0-2.2*speed));
                    if (!dog.checkEntityCollisions(xMove, 0)) {
                        dog.moveX();
                    }
                    dog.setyMove((float) (0-2.2*speed));
                    if (!dog.checkEntityCollisions(0,yMove)) {
                        dog.moveY();
                    }
                }
            }
            if (dog.getPlayerY()>=hunter.getX() && dog.getPlayerY()>=hunter.getY()) {
                if (y >= dog.getPlayerY() && x <= dog.getPlayerX()) {
                    dog.setxMove(speed);
                    if (!dog.checkEntityCollisions(xMove, 0)) {
                        dog.moveX();
                    }
                    dog.setyMove(0-speed);
                    if (!dog.checkEntityCollisions(0,yMove)) {
                        dog.moveY();
                    }
                } else if (y <= dog.getPlayerY() && x >= dog.getPlayerX()){
                    dog.setxMove(0-speed);
                    if (!dog.checkEntityCollisions(xMove, 0)) {
                        dog.moveX();
                    }
                    dog.setxMove(speed);
                    if (!dog.checkEntityCollisions(0,yMove)) {
                        dog.moveY();
                    }
                } else if (x <= dog.getPlayerX() && y <= dog.getPlayerY()){
                    dog.setxMove(2*speed);
                    if (!dog.checkEntityCollisions(xMove, 0)) {
                        dog.moveX();
                    }
                    dog.setyMove(2*speed);
                    if (!dog.checkEntityCollisions(0,yMove)) {
                        dog.moveY();
                    }
                } else if (x <= dog.getPlayerX() && y <= dog.getPlayerY()){
                    dog.setxMove((float) (2.2*speed));
                    if (!dog.checkEntityCollisions(xMove, 0)) {
                        dog.moveX();
                    }
                    dog.setyMove((float) (2.2*speed));
                    if (!dog.checkEntityCollisions(0,yMove)) {
                        dog.moveY();
                    }
                }  else if (x >= dog.getPlayerX()&& y >= dog.getPlayerY()){
                    dog.setxMove((float) (0-0.6*speed));
                    if (!dog.checkEntityCollisions(xMove, 0)) {
                        dog.moveX();
                    }
                    dog.setyMove((float) (0-0.6*speed));
                    if (!dog.checkEntityCollisions(0,yMove)) {
                        dog.moveY();
                    }
                }
            }
        }
    }
}
