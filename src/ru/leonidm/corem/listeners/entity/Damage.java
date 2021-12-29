package ru.leonidm.corem.listeners.entity;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import ru.leonidm.corem.entities.Arguments;

public class Damage implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        Arguments args = new Arguments(true, Arguments.Type.EVENT);

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute as " + e.getEntity().getUniqueId() + " run function #corem:events/entity/server/damage");

        e.setCancelled(args.isCancelled());
        args.clear();
    }

}
