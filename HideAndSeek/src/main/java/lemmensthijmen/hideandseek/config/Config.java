package lemmensthijmen.hideandseek.config;

import lemmensthijmen.hideandseek.HideAndSeek;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Config {

    private static HideAndSeek main;

    public Config(HideAndSeek hideAndSeek) {
        Config.main = hideAndSeek;

        hideAndSeek.getConfig().options().copyDefaults();
        hideAndSeek.saveDefaultConfig();
    }

    public static int getMaxPlayers() {
        return main.getConfig().getInt("max-players");
    }

    public static int getMinPlayers() {
        return main.getConfig().getInt("min-players");
    }

    public static int getCountdown() {
        return main.getConfig().getInt("countdown");
    }

    public static Location getLobbySpawn() {
        return new Location(
                Bukkit.getWorld(main.getConfig().getString("lobby-spawn.world")),
                main.getConfig().getDouble("lobby-spawn.x"),
                main.getConfig().getDouble("lobby-spawn.y"),
                main.getConfig().getDouble("lobby-spawn.z"),
                main.getConfig().getInt("lobby-spawn.yaw"),
                main.getConfig().getInt("lobby-spawn.pitch")
        );
    }

    public static Location getArenaSpawn(int id) {
        return new Location(
                Bukkit.getWorld(main.getConfig().getString("arenas." + id + ".world")),
                main.getConfig().getDouble("arenas." + id + ".x"),
                main.getConfig().getDouble("arenas." + id + ".y"),
                main.getConfig().getDouble("arenas." + id + ".z"),
                main.getConfig().getInt("arenas." + id + ".yaw"),
                main.getConfig().getInt("arenas." + id + ".pitch")
        );
    }

    public static int getArenaAmount() {
        return main.getConfig().getConfigurationSection("arenas").getKeys(false).size();
    }
}
