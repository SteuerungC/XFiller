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

    private Main m;

    public FillCommandHandler(Main m) {
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
                if (p.hasPermission("xfiller.commandfill")) {
                    new TaskHandler().fillAmount(p, 1, false);
                } else {
                    this.sendNoPerm(p);
                }
            } else {
                this.sendNotWorking(p);
            }
            return true;
        } else {
            switch (args[0].toLowerCase()) {
                case "all":
                    if (m.isWorking()) {
                        if (p.hasPermission("xfiller.commandfill.all")) {
                            new TaskHandler().fillAll(p, false);
                        } else {
                            this.sendNoPerm(p);
                        }
                    } else {
                        this.sendNotWorking(p);
                    }
                    return true;
                case "help":
                    if (m.isWorking()) {
                        if (p.hasPermission("xfiller.help")) {
                            this.sendHelp(p);
                        } else {
                            this.sendNoPerm(p);
                        }
                    } else {
                        this.sendNotWorking(p);
                    }
                    return true;
                case "reload":
                    if(m.isWorking()) {
                        if (p.hasPermission("xfiller.reload")) {
                            m.performRelaod(p, m.sendConfig().getString("messages.prefix"));
                        } else {
                            this.sendNoPerm(p);
                        }
                    } else {
                        if (p.hasPermission("xfiller.reload")) {
                            m.performRelaod(p, "&c[Failure Mode]");
                        } else {
                            this.sendNotWorking(p);
                        }
                    }
                    return true;
                default:
                    int i;
                    try {
                        i = Integer.parseInt(args[0]);
                    } catch (NumberFormatException nfe) {
                        i = -1;
                    }

                    if (i > 0) {
                        if (m.isWorking()) {
                            if (p.hasPermission("xfiller.commandfill")) {
                                new TaskHandler().fillAmount(p, i, false);
                            } else {
                                this.sendNoPerm(p);
                            }
                        } else {
                            this.sendNotWorking(p);
                        }
                        return true;
                    } else {
                        if (m.isWorking()) {
                            if (p.hasPermission("xfiller.help")) {
                                this.sendHelp(p);
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
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {
        return null;
    }

    private void sendNoPerm(Player p) {
        p.sendMessage(Tools.messageMaker(m.sendConfig().getString("messages.no_permission"),
                m.sendConfig().getString("messages.prefix")));
    }

    private void sendHelp(Player p) {
        p.sendMessage(Tools.messageMaker(m.sendConfig().getString("messages.help_fill"),
                m.sendConfig().getString("messages.prefix")));
    }

    private void sendNotWorking(Player p) {
        p.sendMessage(Tools.colorMaker("&4The plugins configuration contains errors. Please refer to log for futher information!"));

    }
}
