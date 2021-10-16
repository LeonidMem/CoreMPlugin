package ru.leonidm.corem.entities.commands;

import org.bukkit.Bukkit;
import ru.leonidm.corem.entities.CommandExecutor;

import javax.annotation.Nullable;

public class Cmd implements CommandExecutor {

    @Override
    public void onCommand(@Nullable String arg) {
        if(arg == null) return;

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), arg);
    }
}
