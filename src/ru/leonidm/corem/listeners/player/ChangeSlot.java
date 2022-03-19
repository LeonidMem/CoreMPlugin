package ru.leonidm.corem.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import ru.leonidm.corem.entities.Arguments;

public class ChangeSlot implements Listener {

    @EventHandler
    public void onPlace(PlayerItemHeldEvent e) {
        Arguments args = new Arguments(true, Arguments.Type.EVENT);

        args.getKey("previous").setValue(e.getPreviousSlot());
        args.getKey("new").setValue(e.getNewSlot());

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute as " + e.getPlayer().getName() +
                " run function #corem:events/player/server/change_slot");

        e.setCancelled(args.isCancelled());
        args.clear();
    }
}
