package io.chirp.commands;

import io.chirp.App;
import io.chirp.messages.MessageLog;
import io.chirp.system.User;
import io.chirp.system.UserManagement;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test class for the wall command
 */
public class WallCommandTest
{
    @Test
    public void testWallCommand()
    {
        String testUsernameA = "testUserA";
        User testUserA = new User(testUsernameA);
        String testUsernameB = "testUserB";
        User testUserB = new User(testUsernameB);
        String testUsernameC = "testUserC";
        User testUserC = new User(testUsernameC);
        Set<User> managementSetResult = new HashSet<>();
        managementSetResult.add(testUserB); managementSetResult.add(testUserC);
        Set<User> timeLineArg = new HashSet<>(managementSetResult);
        timeLineArg.add(testUserA);

        WallCommand wallCommand = new WallCommand(testUsernameA);
        App theApp = new App();

        //mock userManagement and messageLog
        UserManagement mockManagement = mock(UserManagement.class);
        MessageLog mockMessageLog = mock(MessageLog.class);
        theApp.setUserManagement(mockManagement);
        theApp.setMessageLog(mockMessageLog);
        //stub some methods
        (when(mockManagement.getOrCreate(testUsernameA))).thenReturn(testUserA);
        (when(mockManagement.getFollowees(testUserA))).thenReturn(managementSetResult);
        (when(mockMessageLog.getTimeLine(timeLineArg))).thenReturn(new LinkedList<>());

        wallCommand.execute(theApp);
        verify(mockManagement).getOrCreate(testUsernameA);
        verify(mockManagement).getFollowees(testUserA);
        verify(mockMessageLog).getTimeLine(timeLineArg);
    }
}
