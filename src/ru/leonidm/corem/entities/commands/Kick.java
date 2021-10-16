package ru.leonidm.corem.entities.commands;

import org.bukkit.entity.Player;
import ru.leonidm.corem.Utils;
import ru.leonidm.corem.entities.CommandExecutor;

import javax.annotation.Nullable;

public class Kick implements CommandExecutor {

    @Override
    public void onCommand(@Nullable String arg) {
        Player player = Utils.getPlayerWithTag("corem.kick");
        if(player == null) return;

        player.kickPlayer("Kicked by datapack!");
        player.removeScoreboardTag("corem.kick");
    }
}
