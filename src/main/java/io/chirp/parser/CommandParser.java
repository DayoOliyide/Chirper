package io.chirp.parser;

import io.chirp.commands.Command;

/**
 *
 */
public interface CommandParser
{
    public Command parseCommand(String commandLine);
}
