package ru.leonidm.corem;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.regex.Pattern;

public class LoggerM extends AbstractFilter {

    private final Pattern regex = Pattern.compile("Executed ([0-9]+) commands from function '", Pattern.MULTILINE);

    @Override
    public Result filter(LogEvent event) {
        if(event.getLevel() != Level.INFO) return Result.NEUTRAL;

        Optional<? extends Player> optional = Bukkit.getOnlinePlayers().stream().findFirst();

        if("net.minecraft.server.MinecraftServer".equals(event.getLoggerName())) {
            String msg = event.getMessage().getFormattedMessage();
            return regex.matcher(msg).groupCount() == 0 ? Result.NEUTRAL : Result.DENY;
        }

        return Result.NEUTRAL;
    }

}
