package com.unsw.tilegame.entities;

import com.unsw.tilegame.Game;
import com.unsw.tilegame.entities.creatures.Player;

/**
 * how will player die
 * @author cecilian
 * @version 2.9
 * @since 2.4
 */
public class PlayerDieBehavior implements DieBehavior {
    Player player;

    /**
     * @param player
     */
    public PlayerDieBehavior(Player player){
        this.player = player;
    }

    /**
     （To ask why we fight）
     （Is to ask why the leaves fall）
      (It is in the nature)
      (Perhaps there is a better question)
      (Why do we fight)
      (To protect home and family)
     （To preserve balance and bring harmony）
     （For my kind ）
     （The true question is what is worth fighting for ）
     */
    @Override
    public void die() {
        Game.GameOver = true;
        player.setActive(false);
        player.setOnHover();
        player.setOnInvincible();
        player.setCurrentWeapon();
    }
}
