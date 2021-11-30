package lemmensthijmen.hideandseek;

import lemmensthijmen.hideandseek.Enum.GameState;
import lemmensthijmen.hideandseek.config.Config;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable {

    private Arena arena;
    private int seconds;

    public Countdown(Arena arena) {
        this.arena = arena;
        this.seconds = Config.getCountdown();
    }

    public void begin() {
        arena.setGameState(GameState.COUNTDOWN);
        this.runTaskTimer(HideAndSeek.getInstance(), 0, 20);
    }

    @Override
    public void run() {
        if (seconds == 0) {
            cancel();
            arena.start();
            return;
        }

        if (seconds % 30 == 0 || seconds <= 10) {
            if (seconds == 1) {
                arena.sendMessage(ChatColor.GREEN + "game is starting");
            } else {
                arena.sendMessage(ChatColor.GREEN + "game is starting in " + seconds);
            }
        }

        if (arena.getPlayers().size() < Config.getMinPlayers()) {
            cancel();
            arena.setGameState(GameState.PLAYERJOIN);
            arena.sendMessage(ChatColor.RED + "game canceld!");
            return;
        }

        seconds--;
    }
}
