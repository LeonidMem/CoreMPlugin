package ru.leonidm.corem.events;

import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

public class CommandListener implements Listener {
	
	private Objective arguments;
	public CommandListener() {
		arguments = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("corem.arguments");
	}
	
	@EventHandler
	public void onSpawn(EntitySpawnEvent e) {
		if(e.getEntityType() == EntityType.AREA_EFFECT_CLOUD) {
			if(e.getEntity().getCustomName() != null) {
				String[] splitted = e.getEntity().getCustomName().split(";");
				if(splitted[0].equals("COREM_PLUGIN")) {
					e.setCancelled(true);
					if(splitted.length == 1) return;
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
					case "CMD":
						if(splitted.length == 2) return;
						StringBuilder sb = new StringBuilder();
						for(int i = 2; i < splitted.length; i++) {
							sb.append(splitted[i]);
						}
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), sb.toString());
						return;
					case "PASSENGER":
						Entity passenger = null, driver = null;
						for(World w : Bukkit.getWorlds()) {
							for(Entity en : w.getEntities()) {
								if(passenger == null && en.getScoreboardTags().contains("corem.passenger")) {
									passenger = en;
									en.removeScoreboardTag("corem.passenger");
								}
								if(driver == null && en.getScoreboardTags().contains("corem.driver")) {
									driver = en;
									en.removeScoreboardTag("corem.driver");
								}
								if(passenger != null && driver != null) {
									driver.addPassenger(passenger);
									return;
								}
							}
						}
						return;
					case "DAMAGE":
						for(World w : Bukkit.getWorlds()) {
							for(Entity en : w.getEntities()) {
								if(en.getScoreboardTags().contains("corem.damage")) {
									if(en instanceof LivingEntity) {
										Score s = arguments.getScore("damage");
										((LivingEntity) en).damage(s.getScore());
										s.setScore(0);
									}
									en.removeScoreboardTag("corem.damage");
									return;
								}
							}
						}
						return;
					}
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
