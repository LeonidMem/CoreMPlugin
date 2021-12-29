package ru.leonidm.corem.listeners.entity;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import ru.leonidm.corem.entities.Arguments;

public class DamageByEntity implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        Arguments args = new Arguments(true, Arguments.Type.EVENT);

        e.getDamager().addScoreboardTag("corem.damager");

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute as " + e.getEntity().getUniqueId() + " run function #corem:events/entity/server/damage_by_entity");

        e.getDamager().removeScoreboardTag("corem.damager");

        e.setCancelled(args.isCancelled());
        args.clear();
    }
}
