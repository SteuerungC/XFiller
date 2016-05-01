package de.steuerungc.xfiller;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Martin on 30.04.2016.
 */
public class FillCommandHandler implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (commandSender instanceof Player) {
            Player p = (Player)commandSender;
        } else {
            commandSender.sendMessage(Tools.colorMaker("&4This command can't be executed from console!"));
            return true;
        }

        if (args.length == 0 || args[0].equals("") || args.length > 1) {
            //TODO Proceed fill one
            return true;
        } else {
            switch (args[0].toLowerCase()) {
                case "all":
                    //TODO Proceed Fill all
                    return true;
                case "help":
                    //TODO Message output: Help
                    return true;
                case "info":
                    //TODO Message output: Own Info
                    return true;
                case "reload":
                    //TODO Proceed Reload
                    return true;
                default:
                    int i;
                    try {
                        i = Integer.parseInt(args[0]);
                    } catch (NumberFormatException nfe) {
                        i = -1;
                    }

                    if (i > 0) {
                        //TODO Proceed Fill amount i
                        return true;
                    } else {
                        //TODO Message out: Help
                        return true;
                    }
            }
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {
        return null;
    }
}
