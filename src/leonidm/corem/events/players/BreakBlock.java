package leonidm.corem.events.players;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakBlock implements Listener {

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Location loc = e.getBlock().getLocation();
		String world = loc.getWorld().toString();
		if(world.equals("world")) world = "overworld";
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:" + world + " positioned " + " " + loc.getBlockX() + " " + 
				loc.getBlockY() + " " + loc.getBlockZ() + " as " + e.getPlayer().getName() + " run function #corem:events/player/server/blockbreak");
	}
}
