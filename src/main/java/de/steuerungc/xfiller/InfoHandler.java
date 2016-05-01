package de.steuerungc.xfiller;

import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by Martin on 01.05.2016.
 */
public class InfoHandler {

    private Main m;

    public InfoHandler(Main m) {
        this.m = m;
    }

    public void sendInfos(Player target, Player reciever) {
        ExperienceCalculator ex = new ExperienceCalculator(target, m.sendConfig().getInteger("settings.xp_per_bottle"));

        if(target.getName().equals(reciever.getName())) {
            HashMap<String, String> rep = new HashMap<>();
            rep.put("%level%", "" + ex.getLevel());
            rep.put("%total_xp%", "" + ex.getTotalXP());
            rep.put("%next%", "" + ex.toNextLevel());
            rep.put("%bottles%", "" + ex.getPossibleBottleCount());
            reciever.sendMessage(Tools.messageMaker(Tools.stringAssembler(rep, m.sendConfig().getString("messages.ownXP_info")), m.sendConfig().getString("messages.prefix")));
        } else {
            HashMap<String, String> rep = new HashMap<>();
            rep.put("%level%", "" + ex.getLevel());
            rep.put("%name%", target.getName());
            rep.put("%total_xp%", "" + ex.getTotalXP());
            rep.put("%next%", "" + ex.toNextLevel());
            rep.put("%bottles%", "" + ex.getPossibleBottleCount());
            reciever.sendMessage(Tools.messageMaker(Tools.stringAssembler(rep, m.sendConfig().getString("messages.foreignXP_info")), m.sendConfig().getString("messages.prefix")));
        }
    }
}
