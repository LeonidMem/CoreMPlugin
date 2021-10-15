package ru.leonidm.corem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Objective;

import ru.leonidm.corem.entities.Event;
import ru.leonidm.corem.listeners.CommandListener;

public class CoreM extends JavaPlugin {

	private static CoreM instance;
	
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
					Bukkit.getPluginManager().disablePlugin(CoreM.getInstance());
					return;
				}
				if(versions.getScore("corem.version").getScore() < 30000) {
					getLogger().warning("You are using old version of \"CoreM\" datapack (< 3.0.0)!");
					getLogger().warning("Plugin will be disabled!");
					Bukkit.getPluginManager().disablePlugin(CoreM.getInstance());
					return;
				}
				
				StringBuilder versionToPush = new StringBuilder();
				for(String i : CoreM.getInstance().getDescription().getVersion().split("\\.")) {
					if(i.length() == 1) versionToPush.append("0");
					versionToPush.append(i);
				}
				
				versions.getScore("corem.plugin").setScore(Integer.parseInt(versionToPush.toString()));
				
				Bukkit.getPluginManager().registerEvents(new CommandListener(), CoreM.getInstance());

				Event.register();

				Logger logger = (Logger) LogManager.getRootLogger();
				logger.addFilter(new LoggerM());

				getLogger().info("Advice: set \"broadcast-console-to-ops\" in \"server.properties\" to \"false\" to prevent the spam.");
				getLogger().info("Enabled!");
			}
		}.runTaskLater(this, 2);
	}
	
	@Override
	public void onDisable() {
		getLogger().info("Disabled!");
	}
	
	public static CoreM getInstance() {
		return instance;
	}

}
