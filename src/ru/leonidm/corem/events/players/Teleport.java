package ru.leonidm.corem.events.players;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class Teleport implements Listener {

	@EventHandler
	public void onTeleport(PlayerTeleportEvent e) {
		String world = e.getFrom().getWorld().toString();
		if(world.equals("world")) world = "overworld";
		Location loc = e.getFrom().clone();
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at minecraft:" + world + " positioned " + loc.getX() + " " + 
				loc.getY() + " " + + loc.getZ() + " as " + e.getPlayer().getName() + " run function #corem:events/player/server/teleport");
	}
}
