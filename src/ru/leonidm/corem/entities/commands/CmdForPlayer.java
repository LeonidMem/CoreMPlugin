package ru.leonidm.corem.entities.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.leonidm.corem.Utils;
import ru.leonidm.corem.entities.CommandExecutor;

import javax.annotation.Nullable;
import java.util.List;

public class CmdForPlayer implements CommandExecutor {

    @Override
    public void onCommand(@Nullable String arg) {
        if(arg == null) return;

        List<Player> players = Utils.getPlayersWithTag("corem.cmd_for_player");

        CommandSender consoleSender = Bukkit.getConsoleSender();

        for(Player player : players) {
            player.removeScoreboardTag("corem.cmd_for_player");

            Bukkit.dispatchCommand(consoleSender, arg.replace("%player%", player.getName()));
        }
    }
}
