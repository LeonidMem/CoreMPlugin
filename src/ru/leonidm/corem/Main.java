package ru.leonidm.corem;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Objective;

import ru.leonidm.corem.events.CommandListener;
import ru.leonidm.corem.events.entity.Death;
import ru.leonidm.corem.events.entity.Spawn;
import ru.leonidm.corem.events.players.BreakBlock;
import ru.leonidm.corem.events.players.ChangeWorld;
import ru.leonidm.corem.events.players.InteractBlock;
import ru.leonidm.corem.events.players.InventoryClick;
import ru.leonidm.corem.events.players.Join;
import ru.leonidm.corem.events.players.Quit;
import ru.leonidm.corem.events.players.Teleport;
import ru.leonidm.corem.events.server.ChunkGenerated;

public class Main extends JavaPlugin {

	private static Main instance;
	
	@Override
	public void onEnable() {
		instance = this;
		new BukkitRunnable() {
			@Override
			public void run() {
				Objective versions = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("corem.version");
				if(versions == null) {
					getLogger().warning("Datapack \"CoreM\" isn't loaded or you didn't type \"/reload\"!");
					getLogger().warning("Plugin will be disabled!");
					Bukkit.getPluginManager().disablePlugin(Main.getInstance());
					return;
				}
				if(versions.getScore("corem.version").getScore() < 20000) {
					getLogger().warning("You are using old version of \"CoreM\" datapack (< 2.0.0)!");
					getLogger().warning("Plugin will be disabled!");
					Bukkit.getPluginManager().disablePlugin(Main.getInstance());
					return;
				}
				
				StringBuilder versionToPush = new StringBuilder();
				for(String i : Main.getInstance().getDescription().getVersion().split("\\.")) {
					if(i.length() == 1) versionToPush.append("0");
					versionToPush.append(i);
				}
				
				versions.getScore("corem.plugin").setScore(Integer.valueOf(versionToPush.toString()));
				
				Bukkit.getPluginManager().registerEvents(new CommandListener(), Main.getInstance());
				
				Objective subscribes = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("corem.functions");
				// Player events
				if(subscribes.getScore("event.player.join").getScore() != 0) Bukkit.getPluginManager().registerEvents(new Join(), Main.getInstance());
				if(subscribes.getScore("event.player.quit").getScore() != 0) Bukkit.getPluginManager().registerEvents(new Quit(), Main.getInstance());
				if(subscribes.getScore("event.player.inventoryclick").getScore() != 0) Bukkit.getPluginManager().registerEvents(new InventoryClick(), Main.getInstance());
				if(subscribes.getScore("event.player.interactblock").getScore() != 0) Bukkit.getPluginManager().registerEvents(new InteractBlock(), Main.getInstance());
				if(subscribes.getScore("event.player.teleport").getScore() != 0) Bukkit.getPluginManager().registerEvents(new Teleport(), Main.getInstance());
				if(subscribes.getScore("event.player.changeworld").getScore() != 0) Bukkit.getPluginManager().registerEvents(new ChangeWorld(), Main.getInstance());
				if(subscribes.getScore("event.player.breakblock").getScore() != 0) Bukkit.getPluginManager().registerEvents(new BreakBlock(), Main.getInstance());
				// Server events
				if(subscribes.getScore("event.server.chunkgenerated").getScore() != 0) Bukkit.getPluginManager().registerEvents(new ChunkGenerated(), Main.getInstance());
				// Entity events
				if(subscribes.getScore("event.entity.spawn").getScore() != 0) Bukkit.getPluginManager().registerEvents(new Spawn(), Main.getInstance());
				if(subscribes.getScore("event.entity.death").getScore() != 0) Bukkit.getPluginManager().registerEvents(new Death(), Main.getInstance());
				
				getLogger().info("Advice: set \"broadcast-console-to-ops\" in \"server.properties\" to \"false\" to prevent the spam.");
				getLogger().info("Enabled!");
			}
		}.runTaskLater(this, 2);
	}
	
	@Override
	public void onDisable() {
		getLogger().info("Disabled!");
	}
	
	public static Main getInstance() {
		return instance;
	}

}
