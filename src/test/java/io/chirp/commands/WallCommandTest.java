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
import static org.mockito.Mockito.when;

/**
 *
 */
public class WallCommandTest
{
    @Test
    public void testWallCommand()
    {
        User testUserA = new User("testUserA");
        User testUserB = new User("testUserB");
        User testUserC = new User("testUserC");
        Set<User> managementSetResult = new HashSet<>();
        managementSetResult.add(testUserB); managementSetResult.add(testUserC);
        Set<User> timeLineArg = new HashSet<>(managementSetResult);
        timeLineArg.add(testUserA);

        WallCommand wallCommand = new WallCommand("testUserA");
        App theApp = new App();

        //mock userManagement and messageLog
        UserManagement mockManagement = mock(UserManagement.class);
        MessageLog mockMessageLog = mock(MessageLog.class);
        theApp.setUserManagement(mockManagement);
        theApp.setMessageLog(mockMessageLog);
        //stub some methods
        (when(mockManagement.getOrCreate("testUserA"))).thenReturn(testUserA);
        (when(mockManagement.getFollowees(testUserA))).thenReturn(managementSetResult);
        (when(mockMessageLog.getTimeLine(timeLineArg))).thenReturn(new LinkedList<>());

        wallCommand.execute(theApp);
    }
}
