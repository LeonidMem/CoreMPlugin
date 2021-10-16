package ru.leonidm.corem.entities.commands;

import org.bukkit.entity.Player;
import ru.leonidm.corem.Utils;
import ru.leonidm.corem.entities.CommandExecutor;

import javax.annotation.Nullable;

public class CloseGui implements CommandExecutor {

    @Override
    public void onCommand(@Nullable String arg) {
        Player player = Utils.getPlayerWithTag("corem.close_gui");
        if(player == null) return;

        player.closeInventory();
        player.removeScoreboardTag("corem.close_gui");
    }
}
