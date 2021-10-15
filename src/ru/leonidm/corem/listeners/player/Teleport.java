package ru.leonidm.corem.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import ru.leonidm.corem.entities.Arguments;

public class Teleport implements Listener {

	@EventHandler
	public void onTeleport(PlayerTeleportEvent e) {
		Arguments args = new Arguments(true);

		String world = e.getFrom().getWorld().getName();
		if(world.equals("world")) world = "overworld";
		Location loc = e.getFrom().clone();
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:" + world + " positioned " + loc.getX() + " " +
				loc.getY() + " " + + loc.getZ() + " as " + e.getPlayer().getName() + " run function #corem:events/player/server/teleport");

		e.setCancelled(args.isCancelled());
		args.clear();
	}
}
