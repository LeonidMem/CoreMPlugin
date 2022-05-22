package ru.leonidm.corem.entities;

import ru.leonidm.corem.Utils;

import javax.annotation.Nullable;

public enum Command {

    // Player
    OPEN_GUI, CLOSE_GUI, KICK, BAN, SET_MOTION, SET_SELECTED_SLOT,

    // Entity
    PASSENGER, DAMAGE, GET_ARROWS, SET_ARROWS, ATTACK, SET_TARGET,

    // Misc
    CMD, CMD_AS_PLAYER, CMD_FOR_PLAYER;

    private final CommandExecutor executor;

    Command() {
        CommandExecutor outExecutor;

        try {
            Class<? extends CommandExecutor> listenerClass = (Class<? extends CommandExecutor>) Class.forName(generateClassName());
            outExecutor = listenerClass.getConstructor().newInstance();
        } catch(Exception e) {
            e.printStackTrace();
            outExecutor = null;
        }

        executor = outExecutor;
    }

    private String generateClassName() {
        return "ru.leonidm.corem.entities.commands." + Utils.snakeToCamel(this.toString().toLowerCase());
    }

    public void execute(@Nullable String arg) {
        executor.onCommand(arg);
    }

    public static Command get(String name) {
        try {
            return Command.valueOf(Command.class, name);
        } catch(Exception e) {
            return null;
        }
    }
}
