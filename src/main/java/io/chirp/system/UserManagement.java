package io.chirp.system;

import java.util.Set;

/**
 *
 */
public interface UserManagement
{
    User getOrCreate(String username);

    void follows(User follower, User followee);

    Set<User> getFollowees(User user);
}
