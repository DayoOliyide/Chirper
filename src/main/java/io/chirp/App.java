package io.chirp;

import io.chirp.commands.Command;
import io.chirp.messages.Message;
import io.chirp.messages.DefaultMessageLog;
import io.chirp.messages.MessageLog;
import io.chirp.parser.CommandParser;
import io.chirp.parser.DefaultCommandParser;
import io.chirp.system.DefaultUserManagement;
import io.chirp.system.UserManagement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * A simple console based social networking application.
 * It allows users to post messages, read messages and follow each other.
 */
public class App implements Platform
{
    private MessageLog messageLog;
    private UserManagement userManagement;
    private CommandParser commandParser;

    public void setMessageLog(MessageLog messageLog)
    {
        this.messageLog = messageLog;
    }

    public void setUserManagement(UserManagement userManagement) {
        this.userManagement = userManagement;
    }

    public void setCommandParser(CommandParser parser)
    {
        this.commandParser = parser;
    }

    public MessageLog getMessageLog()
    {
        return messageLog;
    }

    public UserManagement getUserManagement()
    {
        return userManagement;
    }

    public void printTimeLine(List<Message> stream, boolean printUser)
    {
        for(Message m : stream)
        {
            System.out.println(m.toString(printUser));
        }
    }

    public void start() throws Exception
    {
        String readLine;
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("> ");
        while(!(readLine = consoleReader.readLine()).isEmpty())
        {
            Command command = commandParser.parseCommand(readLine);
            if(command != null)
            {
                command.execute(this);
            }
            System.out.print("> ");
        }
    }

    public static void main( String[] arguments ) throws Exception
    {
        App theApp = new App();
        theApp.setCommandParser(new DefaultCommandParser());
        theApp.setMessageLog(new DefaultMessageLog());
        theApp.setUserManagement(new DefaultUserManagement());
        theApp.start();
    }

}
