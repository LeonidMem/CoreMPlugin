package leonidm.corem.events.server;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkPopulateEvent;

public class ChunkGenerated implements Listener {

	@EventHandler
	public void onChunkGenerated(ChunkPopulateEvent e) {
		Block b = e.getChunk().getBlock(0, 0, 0);
		String world = e.getWorld().getName();
		if(world.equals("world")) world = "overworld";
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in " + world + " positioned " + b.getX() + " " + b.getY() + " " + b.getZ() + " run function #corem:events/server/chunk_generated");
	}
}
