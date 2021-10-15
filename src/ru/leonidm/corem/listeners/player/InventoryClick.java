package ru.leonidm.corem.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.BlockInventoryHolder;
import ru.leonidm.corem.entities.Arguments;
import ru.leonidm.corem.entities.Key;

public class InventoryClick implements Listener {
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		if(e.getClickedInventory() == null) return;

		Location location = null;

		Arguments args = new Arguments(true);
		Key isContainer = args.getKey("isContainer"), clickedSlot = args.getKey("clickedSlot"),
				isEnderchest = args.getKey("isEnderchest"), isOwnerInventory = args.getKey("isOwnerInventory"),
				isShiftClick = args.getKey("isShiftClick"), isNumberClick = args.getKey("isNumberClick");

		if(e.getWhoClicked().getEnderChest().equals(e.getClickedInventory())) {
			isEnderchest.setValue(1);
		}
		else if(e.getWhoClicked().getInventory().equals(e.getClickedInventory())) {
			isOwnerInventory.setValue(1);
		}
		else if(e.getClickedInventory().getHolder() instanceof BlockInventoryHolder blockInventoryHolder) {
			location = blockInventoryHolder.getBlock().getLocation();
			isContainer.setValue(1);
		}

		clickedSlot.setValue(e.getSlot());
		isShiftClick.setValue(e.getClick().isShiftClick());
		isNumberClick.setValue(e.getClick() == ClickType.NUMBER_KEY);

		if(location != null) {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute as " + e.getWhoClicked().getName() + " positioned " +
					location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ() + " run function #corem:events/player/server/inventory_click");
		}
		else {
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute as " + e.getWhoClicked().getName() +
					" run function #corem:events/player/server/inventory_click");
		}

		e.setCancelled(args.isCancelled());
		args.clear();
	}
}
