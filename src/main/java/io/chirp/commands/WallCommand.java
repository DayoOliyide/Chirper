package io.chirp.commands;

import io.chirp.Platform;
import io.chirp.messages.Message;
import io.chirp.system.User;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents the wall command
 */
public class WallCommand implements Command
{
    public final static Pattern CMD_PATTERN = Pattern.compile("^(\\S+) wall\\s*$");
    public final static Function<Matcher, Command> CMD_BUILDER = (m) -> new WallCommand(m.group(1));

    private String username;

    public WallCommand(String username)
    {
        this.username = username;
    }

    @Override
    public void execute(Platform platform)
    {
        User user = platform.getUserManagement().getOrCreate(username);
        Set<User> followees = platform.getUserManagement().getFollowees(user);
        followees.add(user); //add ourselves
        List<Message> stream = platform.getMessageLog().getTimeLine(followees);
        platform.printTimeLine(stream, true);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WallCommand that = (WallCommand) o;

        if (!username.equals(that.username)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return username.hashCode();
    }
}
