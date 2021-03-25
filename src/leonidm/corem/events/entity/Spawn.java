package leonidm.corem.events.entity;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

import leonidm.corem.Main;

public class Spawn implements Listener {

	@EventHandler
	public void onSpawn(EntitySpawnEvent e) {
		new BukkitRunnable() {
			@Override
			public void run() {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute as " + e.getEntity().getUniqueId() + " at @s run function #corem:events/entity/server/spawn");
			}
		}.runTask(Main.getInstance());
	}
}
