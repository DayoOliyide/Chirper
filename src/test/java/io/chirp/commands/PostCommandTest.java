package io.chirp.commands;

import io.chirp.App;
import io.chirp.messages.Message;
import io.chirp.messages.MessageLog;
import io.chirp.system.User;
import io.chirp.system.UserManagement;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 */
public class PostCommandTest
{
    @Test
    public void testPostCommand()
    {
        User testUserA = new User("testUserA");
        String testMessageContent = "a long long message";
        Message similarMessage = new Message(testUserA, testMessageContent);

        PostCommand postCommand = new PostCommand("testUserA", testMessageContent);
        App theApp = new App();

        //mock userManagement and messageLog
        UserManagement mockManagement = mock(UserManagement.class);
        MessageLog mockMessageLog = mock(MessageLog.class);
        theApp.setUserManagement(mockManagement);
        theApp.setMessageLog(mockMessageLog);
        //stub some methods
        (when(mockManagement.getOrCreate(testUserA.getUsername()))).thenReturn(testUserA);
        Mockito.doNothing().when(mockMessageLog).addMessage(any(Message.class));

        postCommand.execute(theApp);
        ArgumentCaptor<Message> argument = ArgumentCaptor.forClass(Message.class);
        verify(mockMessageLog).addMessage(argument.capture());
        Assert.assertEquals(similarMessage.getUser(), argument.getValue().getUser());
        Assert.assertEquals(similarMessage.getContents(), argument.getValue().getContents());
        Assert.assertNotEquals(similarMessage.getCreationTime(), argument.getValue().getCreationTime());
    }
}
