package io.chirp.messages;

import io.chirp.system.User;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 */
public class DefaultMessageLog implements MessageLog
{
    private List<Message> mainMessageStream = new LinkedList<Message>();

    private static Comparator<Message> TIME_LINE_COMPARATOR =
            (l, r) -> { return Long.compare(r.getCreationTime(), l.getCreationTime());};


    @Override
    public void addMessage(Message message)
    {
        mainMessageStream.add(message);
    }

    @Override
    public List<Message> getTimeLine(Set<User> users)
    {
        return mainMessageStream.stream()
                .filter(m -> users.contains(m.getUser()))
                .sorted(TIME_LINE_COMPARATOR)
                .collect(Collectors.toList());
    }
}
