package ru.leonidm.corem.entities;

import ru.leonidm.corem.entities.Arguments;

import javax.annotation.Nullable;

public interface CommandExecutor {

    Arguments ARGUMENTS = new Arguments(Arguments.Type.COMMAND);

    void onCommand(@Nullable String arg);
}
