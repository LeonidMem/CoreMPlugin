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
			Material.DISPENSER, Material.ANVIL, Material.BEACON, Material.BLAST_FURNACE, Material.BREWING_STAND, Material.CARTOGRAPHY_TABLE,
			
			Material.SHULKER_BOX, Material.WHITE_SHULKER_BOX, Material.ORANGE_SHULKER_BOX, Material.MAGENTA_SHULKER_BOX,
			Material.LIGHT_BLUE_SHULKER_BOX, Material.YELLOW_SHULKER_BOX, Material.LIME_SHULKER_BOX, Material.PINK_SHULKER_BOX,
			Material.GRAY_SHULKER_BOX, Material.LIGHT_GRAY_SHULKER_BOX, Material.CYAN_SHULKER_BOX, Material.PURPLE_SHULKER_BOX,
			Material.BLUE_SHULKER_BOX, Material.BROWN_SHULKER_BOX, Material.GREEN_SHULKER_BOX, Material.RED_SHULKER_BOX, Material.BLACK_SHULKER_BOX);
	
	public InventoryClick() {
		events = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("corem.events");
	}
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		String isItem = null;
		boolean isContainer = false;
		if(e.getWhoClicked().getEnderChest().equals(e.getClickedInventory())) isItem = "isEnderchest";
		else if(e.getWhoClicked().getInventory().equals(e.getClickedInventory())) isItem = "isOwnerInventory";
		else if(players.containsKey(e.getWhoClicked().getName())) {
			if(e.getInventory().getType() == InventoryType.CHEST) isItem = "isChest";
			else if(e.getInventory().getType() == InventoryType.DROPPER) isItem = "isDropper";
			else if(e.getInventory().getType() == InventoryType.DISPENSER) isItem = "isDispenser";
			else if(e.getInventory().getType() == InventoryType.BARREL) isItem = "isBarrel";
			else if(e.getInventory().getType() == InventoryType.SHULKER_BOX) isItem = "isShulkerBox";
			else if(e.getInventory().getType() == InventoryType.ANVIL) isItem = "isAnvil";
			else if(e.getInventory().getType() == InventoryType.BEACON) isItem = "isBeacon";
			else if(e.getInventory().getType() == InventoryType.BLAST_FURNACE) isItem = "isBlastFurnace";
			else if(e.getInventory().getType() == InventoryType.BREWING) isItem = "isBrewing";
			else if(e.getInventory().getType() == InventoryType.CARTOGRAPHY) isItem = "isCartography";
			isContainer = true;
			events.getScore("isContainer").setScore(1);
		}
		else {
			System.out.println(players);
			return;
		}

		if(isItem != null) events.getScore(isItem).setScore(1);
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
		
		if(isContainer) events.getScore("isContainer").setScore(-1);
		if(isItem != null) events.getScore(isItem).setScore(-1);
		events.getScore("clickedSlot").setScore(-1);
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
