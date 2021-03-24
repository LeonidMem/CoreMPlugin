package leonidm.corem.events.entity;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class Spawn implements Listener {

	@EventHandler
	public void onSpawn(EntitySpawnEvent e) {
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute as " + e.getEntity().getUniqueId() + " at @s run function #corem:events/entity/server/spawn");
	}
}
