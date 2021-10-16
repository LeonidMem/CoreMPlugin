package ru.leonidm.corem.entities.commands;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import ru.leonidm.corem.Utils;
import ru.leonidm.corem.entities.CommandExecutor;
import ru.leonidm.corem.entities.Key;

import javax.annotation.Nullable;

public class GetArrows implements CommandExecutor {

    @Override
    public void onCommand(@Nullable String arg) {
        Entity entity = Utils.getEntityWithTag("corem.get_arrows");
        if(entity == null) return;

        if(entity instanceof LivingEntity livingEntity) {
            Key getArrowsReturn = ARGUMENTS.getKey("get_arrows.return");
            getArrowsReturn.setValue(livingEntity.getArrowsInBody());
        }

        entity.removeScoreboardTag("corem.get_arrows");
    }
}
