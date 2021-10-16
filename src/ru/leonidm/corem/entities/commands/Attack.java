package ru.leonidm.corem.entities.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import ru.leonidm.corem.entities.CommandExecutor;

import javax.annotation.Nullable;

public class Attack implements CommandExecutor {

    @Override
    public void onCommand(@Nullable String arg) {
        Entity damager = null, entity = null;
        for(World w : Bukkit.getWorlds()) {
            for(Entity en : w.getEntities()) {
                if(damager == null && en.getScoreboardTags().contains("corem.damager")) {
                    damager = en;
                    en.removeScoreboardTag("corem.damager");
                }
                if(entity == null && en.getScoreboardTags().contains("corem.entity")) {
                    entity = en;
                    en.removeScoreboardTag("corem.entity");
                }
                if(damager != null && entity != null) {
                    if(damager == entity) return;

                    if(damager instanceof LivingEntity leDamager && entity instanceof LivingEntity leEntity) {
                        leDamager.attack(leEntity);
                    }
                    return;
                }
            }
        }
    }
}
