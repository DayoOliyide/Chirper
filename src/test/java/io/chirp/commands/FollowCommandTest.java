package io.chirp.commands;

import io.chirp.App;
import io.chirp.system.User;
import io.chirp.system.UserManagement;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test class for the follows command
 */
public class FollowCommandTest
{

    @Test
    public void testFollowCommand()
    {
        String testUsernameA = "testUserA";
        User testUserA = new User(testUsernameA);
        String testUsernameB = "testUserB";
        User testUserB = new User(testUsernameB);

        FollowCommand followCommand = new FollowCommand(testUsernameA, testUsernameB);
        App theApp = new App();

        //mock userManagement
        UserManagement mockManagement = mock(UserManagement.class);
        theApp.setUserManagement(mockManagement);
        //stub some methods
        (when(mockManagement.getOrCreate(testUsernameA))).thenReturn(testUserA);
        (when(mockManagement.getOrCreate(testUsernameB))).thenReturn(testUserB);
        Mockito.doNothing().when(mockManagement).follows(testUserA, testUserB);

        followCommand.execute(theApp);
        verify(mockManagement).getOrCreate(testUsernameA);
        verify(mockManagement).getOrCreate(testUsernameB);
        verify(mockManagement).follows(testUserA, testUserB);
    }
}
