package io.chirp.messages;

import io.chirp.system.User;

import java.util.List;
import java.util.Set;

/**
 *
 */
public interface MessageLog
{
    void addMessage(Message message);

    List<Message> getTimeLine(Set<User> users);
}
