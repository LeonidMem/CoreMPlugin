package leonidm.corem.events.players;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scoreboard.Objective;

public class InventoryClick implements Listener {

	private Objective events;
	private Map<String, Location> players = new HashMap<>();
	private List<Material> detect = Arrays.asList(Material.CHEST, Material.TRAPPED_CHEST, Material.BARREL, Material.DROPPER, 
			Material.DISPENSER, Material.SHULKER_BOX);
	
	public InventoryClick() {
		events = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("corem.events");
	}
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		if(e.getWhoClicked().getEnderChest().equals(e.getClickedInventory())) {
			events.getScore("isEnderchest").setScore(1);
		}
		else if(e.getWhoClicked().getInventory().equals(e.getClickedInventory())) {
			events.getScore("isOwnerInventory").setScore(1);
		}
		else if(players.containsKey(e.getWhoClicked().getName())) {
			if(e.getInventory().getType() == InventoryType.CHEST) events.getScore("isChest").setScore(1);
			else if(e.getInventory().getType() == InventoryType.DROPPER) events.getScore("isDropper").setScore(1);
			else if(e.getInventory().getType() == InventoryType.DISPENSER) events.getScore("isDispenser").setScore(1);
			else if(e.getInventory().getType() == InventoryType.BARREL) events.getScore("isBarrel").setScore(1);
			else if(e.getInventory().getType() == InventoryType.SHULKER_BOX) events.getScore("isShulkerBox").setScore(1);
			events.getScore("isContainer").setScore(1);
		}
		else {
			System.out.println(players);
			return;
		}

		events.getScore("clickedSlot").setScore(e.getSlot());
		
//		ItemStack is = e.getCursor();
//		System.out.println("Cursor: " + (is == null ? "null" : is.getType()));
//		
//		is = e.getCurrentItem();
//		System.out.println("CurrentItem: " + (is == null ? "null" : is.getType()));
		
		Location loc = players.get(e.getWhoClicked().getName());
		if(loc != null) Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute as " + e.getWhoClicked().getName() + " positioned " + 
				loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + " run function #corem:events/player/server/inventory_click");
		else Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute as " + e.getWhoClicked().getName() + 
				" run function #corem:events/player/server/inventory_click");
		for(String i : Arrays.asList("isEnderchest", "isOwnerInventory", "isContainer", "isChest", "isDropper", "isDispenser", 
				"isBarrel", "isShulkerBox", "clickedSlot")) {
			events.getScore(i).setScore(-1);
		}
	}
	
	@EventHandler
	public void onIntBlock(PlayerInteractEvent e) {
		if(e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		if(!detect.contains(e.getClickedBlock().getType())) return;
		players.put(e.getPlayer().getName(), e.getClickedBlock().getLocation());
		System.out.println(players);
	}
	
	@EventHandler
	public void onInvClose(InventoryCloseEvent e) {
		players.remove(e.getPlayer().getName());
	}
}
