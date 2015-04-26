package io.chirp.system;

import java.util.*;

/**
 *
 */
public class DefaultUserManagement implements UserManagement
{
    Map<String, User> allKnownUsers = new HashMap<>();
    Map<User, Set<User>> allKnownFollowees = new HashMap<>();

    @Override
    public User getOrCreate(String username)
    {
        User u = allKnownUsers.get(username);
        if(u == null)
        {
            u = new User(username);
            allKnownUsers.put(username,u);
        }

        return u;
    }

    @Override
    public void follows(User follower, User followee)
    {
        Set<User> followees = allKnownFollowees.get(follower);
        if(followees == null)
        {
            followees = new HashSet<>();
            allKnownFollowees.put(follower, followees);
        }
        followees.add(followee);
    }

    @Override
    public Set<User> getFollowees(User user)
    {
        Set<User> followees = allKnownFollowees.get(user);
        if(followees == null)
        {
            followees = new HashSet<>();
        }
        return new HashSet<>(followees);
    }
}
