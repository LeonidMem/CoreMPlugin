package ru.leonidm.corem.events.players;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Quit implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute as " + e.getPlayer().getName() + " run function #corem:events/player/server/quit");
	}
}
