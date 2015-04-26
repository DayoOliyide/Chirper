package io.chirp.commands;

import io.chirp.Platform;
import io.chirp.messages.Message;
import io.chirp.system.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents the Read command
 */
public class ReadCommand implements Command
{
    public final static Pattern CMD_PATTERN = Pattern.compile("^(\\S+)\\s*$");
    public final static Function<Matcher, Command> CMD_BUILDER = (m) -> new ReadCommand(m.group(1));

    private String username;

    public ReadCommand(String username)
    {
        this.username = username;
    }

    @Override
    public void execute(Platform platform)
    {
        User user = platform.getUserManagement().getOrCreate(username);
        Set<User> userSet = new HashSet<>();
        userSet.add(user);
        List<Message> stream = platform.getMessageLog().getTimeLine(userSet);
        platform.printTimeLine(stream, false);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReadCommand that = (ReadCommand) o;

        if (!username.equals(that.username)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return username.hashCode();
    }
}
