package io.chirp.commands;

import io.chirp.Platform;

/**
 * This interface represents a command
 */
public interface Command
{
    void execute(Platform platform);
}
