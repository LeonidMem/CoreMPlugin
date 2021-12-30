package ru.leonidm.corem.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import ru.leonidm.corem.entities.Arguments;
import ru.leonidm.corem.entities.Key;

public class Interact implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if(e.getAction() == Action.PHYSICAL) return;
        if(e.getHand() == EquipmentSlot.OFF_HAND) return;

        Arguments args = new Arguments(true, Arguments.Type.EVENT);

        Key isRight = args.getKey("isRight");

        isRight.setValue(e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR);

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute as " + e.getPlayer().getName() +
                " run function #corem:events/player/server/interact");

        e.setCancelled(args.isCancelled());
        args.clear();
    }
}
