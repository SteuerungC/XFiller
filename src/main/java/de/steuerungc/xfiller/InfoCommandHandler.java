package de.steuerungc.xfiller;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Martin on 01.05.2016.
 */
public class InfoCommandHandler implements CommandExecutor {

    private Main m;

    public InfoCommandHandler(Main m) {
        this.m = m;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        Player p = null;
        if (commandSender instanceof Player) {
            p = (Player)commandSender;
        } else {
            commandSender.sendMessage(Tools.colorMaker("&4This command can't be executed from console!"));
            return true;
        }

        if (args.length == 0 || args[0].equals("") || args.length > 1) {
            if (m.isWorking()) {
                if (p.hasPermission("xfiller.info")) {
                    new InfoHandler(m).sendInfos(p, p);
                } else {
                    this.sendNoPerm(p);
                }
            } else {
                this.sendNotWorking(p);
            }
            return true;
        } else {
            Player p1 = Bukkit.getPlayer(args[0]);
            if (p1 != null) {
                if (m.isWorking()) {
                    if (p.hasPermission("xfiller.info.others")) {
                        new InfoHandler(m).sendInfos(p1, p);
                    } else {
                        this.sendNoPerm(p);
                    }
                } else {
                    this.sendNotWorking(p);
                }
                return true;
            } else {
                if (m.isWorking()) {
                    if (p.hasPermission("xfiller.info.others")) {
                        this.sendUnknownPlayer(p);
                    } else {
                        this.sendNoPerm(p);
                    }
                } else {
                    this.sendNotWorking(p);
                }
                return true;
            }
        }
    }

    private void sendNoPerm(Player p) {
        p.sendMessage(Tools.messageMaker(m.sendConfig().getString("messages.no_permission"),
                m.sendConfig().getString("messages.prefix")));
    }

    private void sendNotWorking(Player p) {
        p.sendMessage(Tools.colorMaker("&4The plugins configuration contains errors. Please refer to log for futher information!"));
    }

    private void sendUnknownPlayer(Player p) {
        p.sendMessage(Tools.messageMaker(m.sendConfig().getString("messages.player_not_found"),
                m.sendConfig().getString("messages.prefix")));
    }

}
