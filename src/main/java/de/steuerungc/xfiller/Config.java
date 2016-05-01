package de.steuerungc.xfiller;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * Created by Martin on 30.04.2016.
 */
public class Config {
    private static YamlConfiguration config;
    private Main m;

    public Config(Main m) {
        this.m = m;
        File data = new File(m.getDataFolder().toString() + "/config.yml");
        if (!data.exists()) {
            m.saveResource("config.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(data);
    }

    public static ConfigurationSection get(String section) {
        return config.getConfigurationSection(section);
    }

    public static YamlConfiguration getConfig() {
        return config;
    }


    public boolean getBoolean(String path) {
        String[] full = path.split("\\.");
        if (full.length == 1) {
            return Boolean.parseBoolean(config.getString(full[0]));
        } else {
            ConfigurationSection cs = get(full[0]);
            return Boolean.parseBoolean(cs.getString(full[1]));
        }
    }

    public int getInteger(String path) {
        String[] full = path.split("\\.");
        if (full.length == 1) {
            return Integer.parseInt(config.getString(full[0]));
        } else {
            ConfigurationSection cs = get(full[0]);
            return Integer.parseInt(cs.getString(full[1]));
        }
    }

    public String getString(String path) {
        String[] full = path.split("\\.");
        if (full.length == 1) {
            return config.getString(full[0]);
        } else {
            ConfigurationSection cs = get(full[0]);
            return cs.getString(full[1]);
        }
    }

}
