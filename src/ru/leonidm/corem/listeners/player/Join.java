package ru.leonidm.corem.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute as " + e.getPlayer().getName() + " run function #corem:events/player/server/join");
	}
}
