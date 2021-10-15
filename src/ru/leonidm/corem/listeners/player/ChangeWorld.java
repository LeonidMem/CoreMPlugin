package ru.leonidm.corem.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class ChangeWorld implements Listener {

	@EventHandler
	public void onChangeWorld(PlayerChangedWorldEvent e) {
		String world = e.getFrom().getName();
		if(world.equals("world")) world = "overworld";
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:" + world +
				" run function #corem:events/player/server/change_world");
	}
}
