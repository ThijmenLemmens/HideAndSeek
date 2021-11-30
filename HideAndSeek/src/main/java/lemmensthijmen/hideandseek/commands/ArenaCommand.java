package lemmensthijmen.hideandseek.commands;

import lemmensthijmen.hideandseek.Arena;
import lemmensthijmen.hideandseek.HideAndSeek;
import lemmensthijmen.hideandseek.Manager;
import lemmensthijmen.hideandseek.config.Config;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArenaCommand implements CommandExecutor {

    private HideAndSeek hideAndSeek;

    public ArenaCommand(HideAndSeek hideAndSeek) {
        this.hideAndSeek = hideAndSeek;
        hideAndSeek.getCommand("arena").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;

            if (args.length == 1 && args[0].equalsIgnoreCase("list")) {
                player.sendMessage("these are the Maps!");
                for (Arena arena : Manager.getArenas()) {
                    player.sendMessage(ChatColor.GREEN + "" + arena.getId());
                }
            } else if (args.length == 1 && args[0].equalsIgnoreCase("leave")) {
                if (Manager.isPlaying(player)) {
                    Manager.getArena(player).removePlayer(player);

                    player.sendMessage(ChatColor.GREEN + "you left the arena!");
                } else {
                    player.sendMessage(ChatColor.RED + "you are not in a arena!");
                }
            } else if (args.length == 2 && args[0].equalsIgnoreCase("join")) {
                try {
                    int id = Integer.parseInt(args[1]);

                    if (id >= 0 && id <= (Config.getArenaAmount() - 1)) {
                        if (Manager.isRecruiting(id)) {
                            Manager.getArena(id).addPlayers(player);

                            player.sendMessage("you are now in a game");
                        } else {
                            player.sendMessage(ChatColor.RED + "cant join this game!");
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "Invalid arena!");
                    }
                } catch (NumberFormatException e) {
                    player.sendMessage(ChatColor.RED + "Invalid arena!");
                }
            } else {
                player.sendMessage("Invalid usage - these are the options:");
                player.sendMessage("- /arena list");
                player.sendMessage("- /arena join [id]");
                player.sendMessage("- /arena leave");
            }

        } else {
            hideAndSeek.getLogger().info("your not a player!");
        }

        return false;
    }
}
