package io.chirp.parser;

import io.chirp.commands.Command;

/**
 * An interface for the command parser
 */
public interface CommandParser
{
    public Command parseCommand(String commandLine);
}
