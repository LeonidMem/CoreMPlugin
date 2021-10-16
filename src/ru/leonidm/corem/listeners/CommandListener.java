package ru.leonidm.corem.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;
import ru.leonidm.corem.entities.Command;

import java.util.Arrays;

public class CommandListener implements Listener {
	
	@EventHandler
	public void onSpawn(EntitySpawnEvent e) {
		if(e.getEntityType() == EntityType.AREA_EFFECT_CLOUD) {
			if(e.getEntity().getCustomName() != null) {
				String[] split = e.getEntity().getCustomName().split(";", 3);
				if(split[0].equals("COREM")) {
					e.setCancelled(true);
					if(split.length == 1) return;

					Command command = Command.get(split[1]);
					if(command == null) return;

					command.execute(split.length == 2 ? null : split[2]);
				}
			}
		}
	}
	
	@EventHandler
	public void onCmd(PlayerCommandPreprocessEvent e) {
		if(!e.getPlayer().isOp()) return;

		if(e.getMessage().startsWith("/reload") || e.getMessage().startsWith("/stop")
				|| e.getMessage().startsWith("/restart")) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "function #corem:disable");
		}
	}

	@EventHandler
	public void onCmd(ServerCommandEvent e) {
		if(e.getCommand().startsWith("reload") || e.getCommand().startsWith("stop")
				|| e.getCommand().startsWith("restart")) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "function #corem:disable");
		}
	}
}
