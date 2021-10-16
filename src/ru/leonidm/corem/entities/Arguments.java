package ru.leonidm.corem.entities;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Objective;

import java.util.HashMap;
import java.util.Map;

public class Arguments {

    private final static Objective eventsArguments, commandsArguments;

    static {
        eventsArguments = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("corem.events");
        commandsArguments = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("corem.arguments");
    }

    private final Map<String, Key> keys;
    private final Objective arguments;

    public Arguments(boolean isCancellable, Type type) {
        if(type == Type.EVENT) arguments = eventsArguments;
        else if(type == Type.COMMAND) arguments = commandsArguments;
        else throw new IllegalArgumentException("Wrong arguments' type!");

        this.keys = new HashMap<>();
        if(isCancellable) {
            getKey("isCancelled");
        }
    }

    public Arguments(Type type) {
        this(false, type);
    }

    public Key getKey(String key, int newValue) {
        Key outKey = keys.get(key);
        if(outKey != null) return outKey;

        outKey = new Key(arguments.getScore(key));
        keys.put(key, outKey);
        outKey.setValue(newValue);
        return outKey;
    }

    public Key getKey(String key) {
        Key outKey = keys.get(key);
        if(outKey != null) return outKey;

        outKey = new Key(arguments.getScore(key));
        keys.put(key, outKey);
        return outKey;
    }

    public void clear() {
        clear(-1);
    }

    public void clear(int newKeyValues) {
        for(Key key : keys.values()) {
            key.setValue(newKeyValues);
        }
        keys.clear();
    }

    public boolean isCancelled() {
        Key key = keys.get("isCancelled");

        if(key == null) throw new IllegalArgumentException("You are trying to cancel an event, which is not cancellable!");

        return key.getValue() == 1;
    }

    public enum Type {
        EVENT, COMMAND
    }
}
