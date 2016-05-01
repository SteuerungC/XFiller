package de.steuerungc.xfiller;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by Martin on 01.05.2016.
 */
public class InfoCommandHandler implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (commandSender instanceof Player) {
            Player p = (Player)commandSender;
        } else {
            commandSender.sendMessage(Tools.colorMaker("&4This command can't be executed from console!"));
            return true;
        }

        if (args.length == 0 || args[0].equals("") || args.length > 1) {
            //TODO Message out: own XP
            return true;
        } else {
            Player p1 = Bukkit.getPlayer(args[0]);
            if (p1 != null) {
                //TODO Message out: foreign XP
                return true;
            } else {
                //TODO Message out: Unknown Player
                return true;
            }
        }
    }
}
