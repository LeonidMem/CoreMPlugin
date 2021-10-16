package ru.leonidm.corem.entities.commands;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import ru.leonidm.corem.Utils;
import ru.leonidm.corem.entities.CommandExecutor;
import ru.leonidm.corem.entities.Key;

import javax.annotation.Nullable;

public class SetArrows implements CommandExecutor {

    @Override
    public void onCommand(@Nullable String arg) {
        Entity entity = Utils.getEntityWithTag("corem.set_arrows");
        if(entity == null) return;

        if(entity instanceof LivingEntity livingEntity) {
            Key setArrowsAmount = ARGUMENTS.getKey("set_arrows.amount");
            livingEntity.setArrowsInBody(setArrowsAmount.getValue());
        }

        entity.removeScoreboardTag("corem.set_arrows");
        ARGUMENTS.clear(0);
    }
}
