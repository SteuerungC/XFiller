package de.steuerungc.xfiller;

import java.util.ArrayList;

/**
 * Created by Martin on 30.04.2016.
 */
public class ConfigValidator {

    public static String MSG = "messages";
    public static String SET = "settings";

    private Config c = null;
    private ArrayList<String> ret;

    public ArrayList<String> validateConfig(Config c) {
        ret = new ArrayList<>();
        this.c = c;
        this.checkSettings();
        this.checkMessages();

        if (ret.size() <= 0) {
            return null;
        } else {
            return ret;
        }
    }

    private void checkSettings() {
        String s = null;
        try {
            s = c.get(SET).getString("xp_per_bottle");
            if (s == null || s.equals("")) {
                ret.add("- Can't load " + SET + ".xp_per_bottle: The value is not set!");
            } else {
                Integer.parseInt(s);
            }
        } catch (Exception ex) {
            ret.add("- Can't load " + SET + ".xp_per_bottle: " + s + " is not an Integer value!");
        }

        s = null;
        try {
            s = c.get(SET).getString("sound");
            if (s == null || s.equals("")) {
                ret.add("- Can't load " + SET + ".sound: The value is not set!");
            } else {
                Boolean.parseBoolean(s);
            }
        } catch (Exception ex) {
            ret.add("- Can't load " + SET + ".sound: " + s + " is not a boolean value!");
        }

        s = null;
        try {
            s = c.get(SET).getString("success_message");
            if (s == null || s.equals("")) {
                ret.add("- Can't load " + SET + ".success_message: The value is not set!");
            } else {
                Boolean.parseBoolean(s);
            }
        } catch (Exception ex) {
            ret.add("- Can't load " + SET + ".success_message: " + s + " is not a boolean value!");
        }

        s = null;
        try {
            s = c.get(SET).getString("no_permission_message");
            if (s == null || s.equals("")) {
                ret.add("- Can't load " + SET + ".no_permission_message: The value is not set!");
            } else {
                Boolean.parseBoolean(s);
            }
        } catch (Exception ex) {
            ret.add("- Can't load " + SET + ".no_permission_message: " + s + " is not a boolean value!");
        }

        s = null;
        try {
            s = c.get(SET).getString("extensive_log");
            if (s == null || s.equals("")) {
                ret.add("- Can't load " + SET + ".extensive_log: The value is not set!");
            } else {
                Boolean.parseBoolean(s);
            }
        } catch (Exception ex) {
            ret.add("- Can't load " + SET + ".extensive_log: " + s + " is not a boolean value!");
        }
    }

    private void checkMessages() {
        String[] array = {
                "prefix",
                "no_permission",
                "fill_success",
                "ownXP_info",
                "foreignXP_info",
                "not_enough_xp",
                "inventory_full",
                "undercharging_bottles",
                "player_not_found",
                "help_fill"};

        for(String s : array) {
            String s1;
            try {
                s1 = c.get(MSG).getString(s);
                if (s1 == null || s1.equals("")) {
                    ret.add("- Can't load " + MSG + "." + s + ": The value is not set!");
                }
            } catch (Exception ex) {
                ret.add("- " + MSG + "." + s + " does not exists!");
            }
        }
    }
}
