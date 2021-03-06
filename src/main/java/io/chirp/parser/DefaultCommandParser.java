package io.chirp.parser;

import io.chirp.commands.*;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents the default parser which uses regular expressions to
 * match the various possible commands.
 */
public class DefaultCommandParser implements CommandParser
{
    private static List<SimpleImmutableEntry<Pattern, Function<Matcher, Command>>>
    COMMAND_LIST = new LinkedList<>();

    static
    {
        //The order could become important in the future
        COMMAND_LIST.add(new SimpleImmutableEntry<>(ReadCommand.CMD_PATTERN, ReadCommand.CMD_BUILDER));
        COMMAND_LIST.add(new SimpleImmutableEntry<>(WallCommand.CMD_PATTERN, WallCommand.CMD_BUILDER));
        COMMAND_LIST.add(new SimpleImmutableEntry<>(PostCommand.CMD_PATTERN, PostCommand.CMD_BUILDER));
        COMMAND_LIST.add(new SimpleImmutableEntry<>(FollowCommand.CMD_PATTERN, FollowCommand.CMD_BUILDER));
    }

    @Override
    /**
     * This method sequentially goes through the command list, uses each pattern to try and
     * match the command line. Once it finds a pattern that matches, then it will use the
     * associated builder to create the appropriate command. This command is then returned.
     */
    public Command parseCommand(String commandLine)
    {
        Matcher matcher = null;
        Function<Matcher,Command> commandBuilder = null;
        Command parsedCommand = null;
        //iterate through the command list looking for a matching command pattern
        for(SimpleImmutableEntry<Pattern, Function<Matcher,Command>> pair : COMMAND_LIST)
        {
            Pattern p = pair.getKey();
            matcher = p.matcher(commandLine);
            if(matcher.matches())
            {
                commandBuilder = pair.getValue();
                break;
            }
        }

        if(commandBuilder != null)
        {
            parsedCommand = commandBuilder.apply(matcher);
        }

        return parsedCommand;
    }
}
