package ru.leonidm.corem.entities.commands;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import ru.leonidm.corem.Utils;
import ru.leonidm.corem.entities.CommandExecutor;

import javax.annotation.Nullable;

public class SetMotion implements CommandExecutor {

    @Override
    public void onCommand(@Nullable String arg) {
        Player player = Utils.getPlayerWithTag("corem.set_motion");
        if(player == null) return;

        double x = ARGUMENTS.getKey("set_motion.x").getValue();
        double y = ARGUMENTS.getKey("set_motion.y").getValue();
        double z = ARGUMENTS.getKey("set_motion.z").getValue();

        player.setVelocity(new Vector(x / 1000, y / 1000, z / 1000));
        player.removeScoreboardTag("corem.set_motion");

        ARGUMENTS.clear(0);
    }
}
