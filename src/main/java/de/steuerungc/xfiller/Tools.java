package de.steuerungc.xfiller;

import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by Martin on 01.05.2016.
 */
public final class Tools {

    public static String colorMaker(String in) {
        return ChatColor.translateAlternateColorCodes('&', in);
    }

    public static String stringAssembler(HashMap<String, String> fromto, String in) {
        if (in == null || fromto == null) {
            return null;
        }
        Set<String> set = fromto.keySet();
        for (String key : set) {
            in = in.replace(key, fromto.get(key));
        }
        return in;
    }
}
