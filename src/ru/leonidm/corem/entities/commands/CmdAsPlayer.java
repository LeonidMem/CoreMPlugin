package ru.leonidm.corem.entities.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ru.leonidm.corem.Utils;
import ru.leonidm.corem.entities.CommandExecutor;

import javax.annotation.Nullable;
import java.util.List;

public class CmdAsPlayer implements CommandExecutor {

    @Override
    public void onCommand(@Nullable String arg) {
        if(arg == null) return;

        List<Player> players = Utils.getPlayersWithTag("corem.cmd_as_player");

        for(Player player : players) {
            player.removeScoreboardTag("corem.cmd_as_player");

            Bukkit.dispatchCommand(player, arg.replace("%player%", player.getName()));
        }
    }
}
