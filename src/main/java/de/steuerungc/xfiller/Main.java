package de.steuerungc.xfiller;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Martin on 30.04.2016.
 */
public class Main extends JavaPlugin {

    private PluginLogger log = new PluginLogger(this);
    private Config c;
    private boolean status = false;

    @Override
    public void onEnable() {
        //Start Plugin Metrics
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {
            log.warning("Unable to start Plugin Metrics.");
        }

        //Load / Write Config
        try {
            c = new Config(this);
        } catch (Exception ex) {
            log.warning("The Config is not write or loadable! Disabling...");
            ex.printStackTrace();
            status = false;
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        //Checking Config
        ArrayList<String> errors = new ConfigValidator().validateConfig(c);
        if(errors == null) {
            log.info("The config was loaded successfully");
            status = true;
        } else {
            log.warning("Ouch... There is/are " + errors.size() + " Problem(s) in your config:");
            for (String s : errors) {
                log.warning(s);
            }
            log.warning("Please check these errors. Reload config with /xfiller reload!");
            status = false;
        }

        //Register Events and Commands
        if (status) {
            getCommand("xfiller").setExecutor(new FillCommandHandler(this));
            getCommand("xpinfo").setExecutor(new InfoCommandHandler(this));
            getServer().getPluginManager().registerEvents(new ActionHandler(), this);
        } else {
            //TODO Error while loading?
        }
    }

    protected void performRelaod(CommandSender cs, String savedpre) {
        log.info("RELOAD - Attempting to reload the plugin.");
        try {
            c = new Config(this);
        } catch (Exception ex) {
            log.warning("RELOAD - The Config is not write or loadable! Disabling...");
            ex.printStackTrace();
            status = false;
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        //Checking Config
        ArrayList<String> errors = new ConfigValidator().validateConfig(c);
        if(errors == null) {
            log.info("RELOAD - The config was reloaded successfully");
            status = true;
        } else {
            log.warning(" RELOAD - Ouch... There is/are " + errors.size() + " Problem(s) in your config:");
            for (String s : errors) {
                log.warning(s);
            }
            log.warning("RELOAD - Please check these errors. Reload config with /xfiller reload!");
            status = false;
        }

        //Register Events and Commands
        if (status) {
            cs.sendMessage(Tools.messageMaker("&2Reload completed successfully!", savedpre));
            getCommand("xfiller").setExecutor(new FillCommandHandler(this));
            getCommand("xpinfo").setExecutor(new InfoCommandHandler(this));
            getServer().getPluginManager().registerEvents(new ActionHandler(), this);
        } else {
            cs.sendMessage(Tools.messageMaker("&4&lReload failed! &cThere is an error in your config. Please check the log!", savedpre));
            //TODO Error while loading?
        }
    }

    public Config sendConfig() {
        return c;
    }

    public boolean isWorking() {
        return status;
    }
}
