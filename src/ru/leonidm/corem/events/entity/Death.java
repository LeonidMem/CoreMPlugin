package ru.leonidm.corem.events.entity;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class Death implements Listener {

	@EventHandler
	public void onDeath(EntityDeathEvent e) {
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute as " + e.getEntity().getUniqueId() + " run function #corem:events/entity/server/death");
	}
}
