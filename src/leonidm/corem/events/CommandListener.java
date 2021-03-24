package leonidm.corem.events;

import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

public class CommandListener implements Listener {
	
	@EventHandler
	public void onSpawn(EntitySpawnEvent e) {
		if(e.getEntityType() == EntityType.AREA_EFFECT_CLOUD) {
			if(e.getEntity().getCustomName() != null && e.getEntity().getCustomName().split(";")[0].equals("COREM_PLUGIN")) {
				e.setCancelled(true);
				switch(e.getEntity().getCustomName().split(";")[1]) {
				case "CLOSE_GUI":
					for(Player p : Bukkit.getOnlinePlayers()) {
						if(p.getScoreboardTags().contains("corem.close_gui")) {
							p.closeInventory();
							p.removeScoreboardTag("corem.close_gui");
							break;
						}
					}
					return;
				case "KICK":
					for(Player p : Bukkit.getOnlinePlayers()) {
						if(p.getScoreboardTags().contains("corem.kick")) {
							p.kickPlayer("Kicked by datapack!");
							p.removeScoreboardTag("corem.kick");
							break;
						}
					}
					return;
				case "BAN":
					for(Player p : Bukkit.getOnlinePlayers()) {
						if(p.getScoreboardTags().contains("corem.ban")) {
							p.kickPlayer("Banned by datapack!");
							Bukkit.getBanList(Type.NAME).addBan(p.getName(), "Banned by datapack!", null, "CoreM");
							p.removeScoreboardTag("corem.ban");
							break;
						}
					}
					return;
				}
			}
		}
	}
	
	@EventHandler
	public void onCmd(PlayerCommandPreprocessEvent e) {
		if(e.getMessage().equals("/reload")) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "function #corem:disable");
		}
	}

	@EventHandler
	public void onCmd(ServerCommandEvent e) {
		if(e.getCommand().equals("reload")) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "function #corem:disable");
		}
	}
}
