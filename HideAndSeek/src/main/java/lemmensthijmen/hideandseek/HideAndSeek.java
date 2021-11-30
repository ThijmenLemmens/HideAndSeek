package lemmensthijmen.hideandseek;

import lemmensthijmen.hideandseek.commands.ArenaCommand;
import lemmensthijmen.hideandseek.commands.HardRestCommand;
import lemmensthijmen.hideandseek.config.Config;
import lemmensthijmen.hideandseek.listener.GameListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class HideAndSeek extends JavaPlugin {

    private static HideAndSeek instance;

    @Override
    public void onEnable() {
        HideAndSeek.instance = this;

        new Config(this);

        new Manager();

        new ArenaCommand(this);
        new HardRestCommand();

        getServer().getPluginManager().registerEvents(new GameListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static HideAndSeek getInstance() {
        return instance;
    }
}
