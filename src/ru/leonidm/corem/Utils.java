package ru.leonidm.corem;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static String snakeToCamel(String snakeCase) {
        StringBuilder sb = new StringBuilder(snakeCase);

        sb.replace(0, 1, String.valueOf(Character.toUpperCase(sb.charAt(0))));

        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '_') {
                sb.deleteCharAt(i);
                sb.replace(i, i + 1, String.valueOf(Character.toUpperCase(sb.charAt(i))));
            }
        }

        return sb.toString();
    }

    public static Entity getEntityWithTag(String tag) {
        for(World w : Bukkit.getWorlds()) {
            for(Entity en : w.getEntities()) {
                if(en.getScoreboardTags().contains(tag)) {
                    return en;
                }
            }
        }
        return null;
    }

    public static Player getPlayerWithTag(String tag) {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(player.getScoreboardTags().contains(tag)) {
                return player;
            }
        }
        return null;
    }

    public static List<Player> getPlayersWithTag(String tag) {
        List<Player> out = new ArrayList<>();

        for(Player player : Bukkit.getOnlinePlayers()) {
            if(player.getScoreboardTags().contains(tag)) {
                out.add(player);
            }
        }
        return out;
    }
}
