package io.chirp.commands;

import io.chirp.Platform;
import io.chirp.system.User;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class FollowCommand implements Command
{
    public final static Pattern CMD_PATTERN = Pattern.compile("^(\\S+) follows (\\S+)$");
    public final static Function<Matcher, Command> CMD_BUILDER = (m) -> new FollowCommand(m.group(1), m.group(2));

    private String username;
    private String otherUsername;

    public FollowCommand(String username, String otherUsername)
    {
        this.username = username;
        this.otherUsername = otherUsername;
    }

    @Override
    public void execute(Platform platform)
    {
        User follower = platform.getUserManagement().getOrCreate(username);
        User followee = platform.getUserManagement().getOrCreate(otherUsername);
        platform.getUserManagement().follows(follower, followee);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FollowCommand that = (FollowCommand) o;

        if (!otherUsername.equals(that.otherUsername)) return false;
        if (!username.equals(that.username)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = username.hashCode();
        result = 31 * result + otherUsername.hashCode();
        return result;
    }
}
