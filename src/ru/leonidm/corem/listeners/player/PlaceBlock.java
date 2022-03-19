package ru.leonidm.corem.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import ru.leonidm.corem.entities.Arguments;

public class PlaceBlock implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Arguments args = new Arguments(true, Arguments.Type.EVENT);

        Location loc = e.getBlock().getLocation();
        String world = loc.getWorld().getName();
        if(world.equals("world")) world = "overworld";

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:" + world + " positioned " + loc.getBlockX() + " " +
                loc.getBlockY() + " " + loc.getBlockZ() + " as " + e.getPlayer().getName() + " run function #corem:events/player/server/place_block");

        e.setCancelled(args.isCancelled());
        args.clear();
    }
}
