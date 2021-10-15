package ru.leonidm.corem.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import ru.leonidm.corem.entities.Arguments;
import ru.leonidm.corem.entities.Key;

public class InteractBlock implements Listener {

	private final Objective events;
	
	public InteractBlock() {
		events = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("corem.events");
	}
	
	@EventHandler
	public void onIntBlock(PlayerInteractEvent e) {
		if(e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.LEFT_CLICK_BLOCK) return;
		if(e.getHand() == EquipmentSlot.OFF_HAND) return;

		Arguments args = new Arguments(true);

		Key isRight = args.getKey("isRight");

		isRight.setValue(e.getAction() == Action.RIGHT_CLICK_BLOCK);

		Location loc = e.getClickedBlock().getLocation();
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute as " + e.getPlayer().getName() + " positioned " + loc.getBlockX() + " " + loc.getBlockY() + " " + 
				loc.getBlockZ() + " run function #corem:events/player/interact_block");

		e.setCancelled(args.isCancelled());
		args.clear();
	}

}
