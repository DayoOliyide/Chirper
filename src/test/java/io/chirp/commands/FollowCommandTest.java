package io.chirp.commands;

import io.chirp.App;
import io.chirp.system.User;
import io.chirp.system.UserManagement;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 */
public class FollowCommandTest
{

    @Test
    public void testFollowCommand()
    {
        User testUserA = new User("testUserA");
        User testUserB = new User("testUserB");

        FollowCommand followCommand = new FollowCommand("testUserA", "testUserB");
        App theApp = new App();

        //mock userManagement
        UserManagement mockManagement = mock(UserManagement.class);
        theApp.setUserManagement(mockManagement);
        //stub some methods
        (when(mockManagement.getOrCreate(testUserA.getUsername()))).thenReturn(testUserA);
        (when(mockManagement.getOrCreate(testUserB.getUsername()))).thenReturn(testUserB);
        Mockito.doNothing().when(mockManagement).follows(testUserA, testUserB);

        followCommand.execute(theApp);

    }
}
