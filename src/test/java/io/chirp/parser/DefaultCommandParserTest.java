package io.chirp.parser;

import io.chirp.commands.*;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test class for the default command parser
 */
public class DefaultCommandParserTest
{
    private DefaultCommandParser commandParser = new DefaultCommandParser();

    @Test
    public void testParsingReadCommand()
    {
        String commandLine = "userA";
        Command actualCommand = commandParser.parseCommand(commandLine);
        ReadCommand expectedCommand = new ReadCommand("userA");
        Assert.assertEquals("ReadCommand is NOT parsed", expectedCommand, actualCommand);
    }

    @Test
    public void testParsingWallCommand()
    {
        String commandLine = "userB wall";
        Command actualCommand = commandParser.parseCommand(commandLine);
        WallCommand expectedCommand = new WallCommand("userB");
        Assert.assertEquals("WallCommand is NOT parsed", expectedCommand, actualCommand);
    }

    @Test
    public void testParsingPostCommand()
    {
        String commandLine = "userC -> A multi part and long message";
        Command actualCommand = commandParser.parseCommand(commandLine);
        PostCommand expectedCommand = new PostCommand("userC", "A multi part and long message");
        Assert.assertEquals("PostCommand is NOT parsed", expectedCommand, actualCommand);
    }

    @Test
    public void testParsingFollowCommand()
    {
        String commandLine = "userD follows userA";
        Command actualCommand = commandParser.parseCommand(commandLine);
        FollowCommand expectedCommand = new FollowCommand("userD", "userA");
        Assert.assertEquals("FollowCommand is NOT parsed", expectedCommand, actualCommand);
    }

    @Test
    public void testParsingUnknownCommand()
    {
        String commandLine = "userX does the unexpected";
        Command actualUnknownCommand = commandParser.parseCommand(commandLine);
        Command expectedUnknownCommand = null;
        Assert.assertEquals("This command should NOT be deduced!", expectedUnknownCommand, actualUnknownCommand);
    }

}
