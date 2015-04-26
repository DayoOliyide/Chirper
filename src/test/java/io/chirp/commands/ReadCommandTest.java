package io.chirp.commands;

import io.chirp.App;
import io.chirp.messages.Message;
import io.chirp.messages.MessageLog;
import io.chirp.system.User;
import io.chirp.system.UserManagement;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 */
public class ReadCommandTest
{
    @Test
    public void testReadCommand()
    {
        User testUserA = new User("testUserA");
        Set<User> testUserSet = new HashSet<>();
        testUserSet.add(testUserA);
        Message testMessage = new Message(testUserA, "a message");
        List<Message> testMessages = Arrays.asList(testMessage);

        ReadCommand readCommand = new ReadCommand("testUserA");
        App theApp = new App();

        //mock userManagement and messageLog
        UserManagement mockManagement = mock(UserManagement.class);
        MessageLog mockMessageLog = mock(MessageLog.class);
        theApp.setUserManagement(mockManagement);
        theApp.setMessageLog(mockMessageLog);
        //stub some methods
        (when(mockManagement.getOrCreate(testUserA.getUsername()))).thenReturn(testUserA);
        (when(mockMessageLog.getTimeLine(testUserSet))).thenReturn(testMessages);

        readCommand.execute(theApp);
    }
}
