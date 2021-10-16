package ru.leonidm.corem.entities.commands;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import ru.leonidm.corem.Utils;
import ru.leonidm.corem.entities.CommandExecutor;
import ru.leonidm.corem.entities.Key;

import javax.annotation.Nullable;

public class Damage implements CommandExecutor {

    @Override
    public void onCommand(@Nullable String arg) {
        Entity entity = Utils.getEntityWithTag("corem.damage");
        if(entity == null) return;

        if(entity instanceof LivingEntity livingEntity) {
            Key damage = ARGUMENTS.getKey("damage.amount");
            livingEntity.damage(damage.getValue());
            ARGUMENTS.clear(0);
        }

        entity.removeScoreboardTag("corem.damage");
    }
}
