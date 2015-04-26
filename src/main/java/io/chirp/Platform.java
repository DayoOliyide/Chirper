package io.chirp;

import io.chirp.messages.Message;
import io.chirp.messages.MessageLog;
import io.chirp.system.UserManagement;

import java.util.List;

/**
 *
 */
public interface Platform
{
    public MessageLog getMessageLog();

    public UserManagement getUserManagement();

    public void printTimeLine(List<Message> messages, boolean printUser);
}
