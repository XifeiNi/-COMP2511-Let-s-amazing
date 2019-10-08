package com.unsw.tilegame.entities.enemy;

import com.unsw.tilegame.entities.DieBehavior;
import com.unsw.tilegame.state.Stop;

/**
 * how enemy will die
 * @author cecilia
 * @version 2.5
 * @since 2.4
 * @see com.unsw.tilegame.entities.DieBehavior
 */
public class EnemyDieBehavior implements DieBehavior {
    private Enemy enemy;

    /**
     * @param enemy
     */
    public EnemyDieBehavior(Enemy enemy){
        this.enemy = enemy;
    }

    @Override
    public void die() {
        enemy.setActive();
        enemy.setState(new Stop());
    }
}
