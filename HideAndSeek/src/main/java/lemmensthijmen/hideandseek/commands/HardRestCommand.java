package lemmensthijmen.hideandseek.commands;

import lemmensthijmen.hideandseek.HideAndSeek;
import lemmensthijmen.hideandseek.Manager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HardRestCommand implements CommandExecutor {

    public HardRestCommand() {
        HideAndSeek.getInstance().getCommand("hardreset").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.isOp()) {
                Manager.getArena(Integer.parseInt(args[0]));
            } else {
                player.sendMessage(ChatColor.RED + "You dont have the perms!");
            }
        } else {
            HideAndSeek.getInstance().getLogger().info("You are not a player!");
        }
        return false;
    }
}
