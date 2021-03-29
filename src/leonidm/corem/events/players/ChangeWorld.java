package leonidm.corem.events.players;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class ChangeWorld implements Listener {

	@EventHandler
	public void onChangeWorld(PlayerChangedWorldEvent e) {
		String world = e.getFrom().toString();
		if(world.equals("world")) world = "overworld";
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute at minecraft:" + world + 
				" run function #corem:events/player/server/change_world");
	}
}
