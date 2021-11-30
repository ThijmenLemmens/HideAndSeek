package lemmensthijmen.hideandseek;

import lemmensthijmen.hideandseek.Enum.GameState;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Game {

    private Arena arena;

    public Game(Arena arena) {
        this.arena = arena;
    }

    public void start() {
        arena.setSeeker();
        arena.setGameState(GameState.LIVE);

        arena.sendMessage(ChatColor.RED + arena.getSeekerPlayer(0).getDisplayName() + ChatColor.GRAY + " is the first seeker!");

        if (arena.getSeeker().size() >= arena.getPlayers().size() - arena.getSeeker().size()) {
            arena.reset();
        }
    }

    public void foundHider(Player seeker, Player hider) {
        if (arena.getSeeker().contains(seeker.getUniqueId())) {
            arena.setSeeker(hider);
            arena.sendMessage(ChatColor.GREEN + hider.getDisplayName() + ChatColor.RED + "has been found!");
        }
    }

}
