package leonidm.corem;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Objective;

import leonidm.corem.events.CommandListener;
import leonidm.corem.events.entity.Spawn;
import leonidm.corem.events.players.InteractBlock;
import leonidm.corem.events.players.InventoryClick;
import leonidm.corem.events.players.Join;
import leonidm.corem.events.players.Quit;
import leonidm.corem.events.server.ChunkGenerated;

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
				if(subscribes.getScore("event.player.join").getScore() != 0) Bukkit.getPluginManager().registerEvents(new Join(), Main.getInstance());
				if(subscribes.getScore("event.player.quit").getScore() != 0) Bukkit.getPluginManager().registerEvents(new Quit(), Main.getInstance());
				if(subscribes.getScore("event.player.inventoryclick").getScore() != 0) Bukkit.getPluginManager().registerEvents(new InventoryClick(), Main.getInstance());
				if(subscribes.getScore("event.player.interactblock").getScore() != 0) Bukkit.getPluginManager().registerEvents(new InteractBlock(), Main.getInstance());
				if(subscribes.getScore("event.server.chunkgenerated").getScore() != 0) Bukkit.getPluginManager().registerEvents(new ChunkGenerated(), Main.getInstance());
				if(subscribes.getScore("event.entity.spawn").getScore() != 0) {
					getLogger().info("Registered!");
					Bukkit.getPluginManager().registerEvents(new Spawn(), Main.getInstance());
				}
				
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
