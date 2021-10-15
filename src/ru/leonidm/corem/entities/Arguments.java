package ru.leonidm.corem.entities;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Objective;

import java.util.HashMap;
import java.util.Map;

public class Arguments {

    private final static Objective events;

    static {
        events = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("corem.events");
    }

    private final Map<String, Key> keys;

    public Arguments(boolean isCancellable) {
        this.keys = new HashMap<>();
        if(isCancellable) {
            getKey("isCancelled");
        }
    }

    public Arguments() {
        this(false);
    }

    public Key getKey(String key) {
        Key outKey = keys.get(key);
        if(outKey != null) return outKey;

        outKey = new Key(events.getScore(key));
        keys.put(key, outKey);
        outKey.setValue(0);
        return outKey;
    }

    public void clear() {
        for(Key key : keys.values()) {
            key.setValue(-1);
        }
        keys.clear();
    }

    public boolean isCancelled() {
        Key key = keys.get("isCancelled");

        if(key == null) throw new IllegalArgumentException("You are trying to cancel an event, which is not cancellable!");

        return key.getValue() == 1;
    }

}
