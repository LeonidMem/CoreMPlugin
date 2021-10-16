package ru.leonidm.corem.entities.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.entity.Player;
import ru.leonidm.corem.Utils;
import ru.leonidm.corem.entities.CommandExecutor;

import javax.annotation.Nullable;

public class OpenGui implements CommandExecutor {

    private static final World world = Bukkit.getWorld("world");

    @Override
    public void onCommand(@Nullable String arg) {
        Player player = Utils.getPlayerWithTag("corem.open_gui");
        if(player == null) return;

        int x = ARGUMENTS.getKey("open_gui.x").getValue();
        int y = ARGUMENTS.getKey("open_gui.y").getValue();
        int z = ARGUMENTS.getKey("open_gui.z").getValue();

        Block block = world.getBlockAt(x, y, z);

        if(block.getState() instanceof Container container) {
            player.openInventory(container.getInventory());
        }

        player.removeScoreboardTag("corem.open_gui");

        ARGUMENTS.clear(0);
    }
}
