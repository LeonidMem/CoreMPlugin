package ru.leonidm.corem.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import ru.leonidm.corem.entities.Arguments;

public class InteractAtEntity implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent e) {
        if(e.getHand() == EquipmentSlot.OFF_HAND) return;

        Arguments args = new Arguments(true, Arguments.Type.EVENT);

        e.getRightClicked().addScoreboardTag("corem.right_clicked");

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute as " + e.getPlayer().getName() +
                " run function #corem:events/player/server/interact_at_entity");

        e.getRightClicked().removeScoreboardTag("corem.right_clicked");

        e.setCancelled(args.isCancelled());
        args.clear();
    }
}
