package ru.leonidm.corem.entities.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import ru.leonidm.corem.entities.CommandExecutor;

import javax.annotation.Nullable;

public class Passenger implements CommandExecutor {

    @Override
    public void onCommand(@Nullable String arg) {
        Entity passenger = null, driver = null;
        for(World w : Bukkit.getWorlds()) {
            for(Entity en : w.getEntities()) {
                if(passenger == null && en.getScoreboardTags().contains("corem.passenger")) {
                    passenger = en;
                    en.removeScoreboardTag("corem.passenger");
                }
                if(driver == null && en.getScoreboardTags().contains("corem.driver")) {
                    driver = en;
                    en.removeScoreboardTag("corem.driver");
                }
                if(passenger != null && driver != null) {
                    if(passenger == driver) return;

                    driver.addPassenger(passenger);
                    return;
                }
            }
        }
    }
}
