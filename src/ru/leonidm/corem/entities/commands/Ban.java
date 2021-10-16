package ru.leonidm.corem.entities.commands;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ru.leonidm.corem.Utils;
import ru.leonidm.corem.entities.CommandExecutor;

import javax.annotation.Nullable;

public class Ban implements CommandExecutor {

    @Override
    public void onCommand(@Nullable String arg) {
        Player player = Utils.getPlayerWithTag("corem.ban");
        if(player == null) return;

        player.kickPlayer("Banned by datapack!");
        Bukkit.getBanList(BanList.Type.NAME).addBan(player.getName(), "Banned by datapack!", null, "CoreM");
        player.removeScoreboardTag("corem.ban");
    }
}
