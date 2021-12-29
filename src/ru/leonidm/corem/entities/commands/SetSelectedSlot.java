package ru.leonidm.corem.entities.commands;

import org.bukkit.entity.Player;
import ru.leonidm.corem.Utils;
import ru.leonidm.corem.entities.CommandExecutor;

import javax.annotation.Nullable;

public class SetSelectedSlot implements CommandExecutor {

    @Override
    public void onCommand(@Nullable String arg) {
        Player player = Utils.getPlayerWithTag("corem.set_selected_slot");
        if(player == null) return;

        int slot = ARGUMENTS.getKey("set_selected_slot.id").getValue();
        if(slot >= 0 && slot <= 8) {
            player.getInventory().setHeldItemSlot(slot);
        }

        ARGUMENTS.clear(0);
    }
}
