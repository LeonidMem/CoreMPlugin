package leonidm.corem.events.players;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scoreboard.Objective;

public class InteractBlock implements Listener {

	private Objective events;
	
	public InteractBlock() {
		events = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("corem.events");
	}
	
	@EventHandler
	public void onIntBlock(PlayerInteractEvent e) {
		if(e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.LEFT_CLICK_BLOCK) return;
		if(e.getHand() == EquipmentSlot.OFF_HAND) return;
		
		events.getScore("isRight").setScore(e.getAction() == Action.RIGHT_CLICK_BLOCK ? 1 : 0);
		Location loc = e.getClickedBlock().getLocation();
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute as " + e.getPlayer().getName() + " positioned " + loc.getBlockX() + " " + loc.getBlockY() + " " + 
				loc.getBlockZ() + " run function #corem:events/player/interact_block");
		events.getScore("isRight").setScore(-1);
	}

}
