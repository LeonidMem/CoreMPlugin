package ru.leonidm.corem.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CoreM implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 0) return false;
		switch(args[0]) {
		case "reload":
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "function #minecraft:load");
			return true;
		default:
			return false;
		}
		
	}
}
