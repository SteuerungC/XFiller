package de.steuerungc.xfiller;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;


/**
 * Created by Martin on 30.04.2016.
 */
public class ActionHandler implements Listener {

    @EventHandler
    public boolean onClick(PlayerInteractEvent apple_pie) {
        if (apple_pie.getAction() != Action.RIGHT_CLICK_BLOCK ||
                apple_pie.getClickedBlock() == null ||
                apple_pie.getClickedBlock().getType() != Material.EMERALD_BLOCK ||
                apple_pie.getPlayer() == null) {
            return false;
        }

        Player p = apple_pie.getPlayer();
        ItemStack hand = p.getEquipment().getItemInMainHand();

        if (hand == null || hand.getType() != Material.GLASS_BOTTLE) {
            return false;
        }

        if (!p.isSneaking()) {
            if (!p.hasPermission("xfiller.blockfill")) {
                //TODO Message out: No Permission
                return false;
            }

            //TODO Proceed fill one
        } else {
            if (!p.hasPermission("xfiller.blockfill.all")) {
                //TODO Message out: No Permission
                return false;
            }

            //TODO Proceed fill all
        }
        return false;
    }
}
