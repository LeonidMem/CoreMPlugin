package ru.leonidm.corem.entities;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.Objective;
import ru.leonidm.corem.CoreM;
import ru.leonidm.corem.Utils;

public enum Event {

    ENTITY_DEATH, ENTITY_SPAWN,

    PLAYER_BREAK_BLOCK, PLAYER_CHANGE_WORLD, PLAYER_INVENTORY_CLICK, PLAYER_JOIN, PLAYER_QUIT, PLAYER_TELEPORT,
    PLAYER_INTERACT_BLOCK,

    SERVER_CHUNK_GENERATED;

    private final Listener listener;

    Event() {

        Listener outListener;
        try {
            Class<? extends Listener> listenerClass = (Class<? extends Listener>) Class.forName(generateClassName());
            outListener = listenerClass.getConstructor().newInstance();
        } catch(Exception e) {
            e.printStackTrace();
            outListener = null;
        }

        this.listener = outListener;
    }

    private String generateClassName() {
        String[] split = this.toString().toLowerCase().split("_", 2);
        return "ru.leonidm.corem.listeners." + split[0] + "." + Utils.snakeToCamel(split[1]);
    }

    public static void register() {
        Objective subscribes = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("corem.functions");

        for(Event event : Event.values()) {
            if(subscribes.getScore("event." + event).getScore() != 0 && event.listener != null) {
                Bukkit.getPluginManager().registerEvents(event.listener, CoreM.getInstance());
            }
        }
    }
}
