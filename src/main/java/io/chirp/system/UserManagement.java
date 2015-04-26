package io.chirp.system;

import java.util.Set;

/**
 * An interface for the application user management
 */
public interface UserManagement
{
    User getOrCreate(String username);

    void follows(User follower, User followee);

    Set<User> getFollowees(User user);
}
