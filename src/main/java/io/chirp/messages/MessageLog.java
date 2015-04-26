package io.chirp.messages;

import io.chirp.system.User;

import java.util.List;
import java.util.Set;

/**
 * An interface for the message log
 */
public interface MessageLog
{
    void addMessage(Message message);

    List<Message> getTimeLine(Set<User> users);
}
