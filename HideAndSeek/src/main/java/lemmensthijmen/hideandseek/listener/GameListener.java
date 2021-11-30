package lemmensthijmen.hideandseek.listener;

import lemmensthijmen.hideandseek.Enum.GameState;
import lemmensthijmen.hideandseek.Manager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class GameListener implements Listener {

    @EventHandler
    public void damage(EntityDamageByEntityEvent event) {
        Player playerSeeker = (Player) event.getDamager();
        Player playerHider = (Player) event.getEntity();

        if (Manager.isPlaying(playerSeeker) && Manager.isPlaying(playerHider) && Manager.getArena(playerHider).getGameState().equals(GameState.LIVE)) {
            Manager.getArena(playerSeeker).getGame().foundHider(playerSeeker, playerHider);
        }
    }

    @EventHandler
    public void playerLeaveEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (Manager.isPlaying(player)) {
            Manager.getArena(player).removePlayer(player);
        }
    }

}
