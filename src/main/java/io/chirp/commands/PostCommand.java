package io.chirp.commands;

import io.chirp.Platform;
import io.chirp.messages.Message;
import io.chirp.system.User;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents the post command.
 */
public class PostCommand implements Command
{
    public final static Pattern CMD_PATTERN = Pattern.compile("^(\\S+) -> (.*)");
    public final static Function<Matcher, Command> CMD_BUILDER = (m) -> new PostCommand(m.group(1), m.group(2));

    private String username;
    private String messageContent;

    public PostCommand(String username, String messageContent)
    {
        this.username = username;
        this.messageContent = messageContent;
    }

    @Override
    public void execute(Platform platform)
    {
        User user = platform.getUserManagement().getOrCreate(username);
        Message message = new Message(user, messageContent);
        platform.getMessageLog().addMessage(message);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostCommand that = (PostCommand) o;

        if (!messageContent.equals(that.messageContent)) return false;
        if (!username.equals(that.username)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = username.hashCode();
        result = 31 * result + messageContent.hashCode();
        return result;
    }
}
