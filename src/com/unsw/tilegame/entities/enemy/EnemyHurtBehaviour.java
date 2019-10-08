package com.unsw.tilegame.entities.enemy;

/**
 * how enemy will be hurt
 * @author cecilia
 */
public class EnemyHurtBehaviour implements HurtBehaviour {
    private Enemy enemy;

    /**
     * @param enemy
     */
    public EnemyHurtBehaviour(Enemy enemy){
        this.enemy = enemy;
    }

    /**
     * @param amt
     */
    @Override
    public void hurt(int amt) {
        enemy.setHealth(enemy.getHealth()-amt);

        if (enemy.getHealth() <= 0){
            enemy.getEnemyDieBehavior().die();
        }
    }
}
