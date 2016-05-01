package de.steuerungc.xfiller;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Martin on 30.04.2016.
 */
public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("xfiller").setExecutor(new FillCommandHandler());
        getCommand("xpinfo").setExecutor(new InfoCommandHandler());
        getServer().getPluginManager().registerEvents(new ActionHandler(), this);
    }
}
