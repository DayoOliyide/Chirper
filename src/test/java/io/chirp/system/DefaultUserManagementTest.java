package io.chirp.system;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Test class for the default user management
 */
public class DefaultUserManagementTest
{

    @Test
    public void testFollowing()
    {
        DefaultUserManagement management = new DefaultUserManagement();
        User testUserA = management.getOrCreate("testUserA");
        User testUserB = management.getOrCreate("testUserB");
        User testUserC = management.getOrCreate("testUserC");
        Set<User> expectedFollowees = new HashSet<>();
        expectedFollowees.add(testUserB); expectedFollowees.add(testUserC);

        management.follows(testUserA, testUserB);
        management.follows(testUserA, testUserC);
        Set<User> actualFollowees = management.getFollowees(testUserA);
        Assert.assertEquals("Followees is NOT correct", actualFollowees, expectedFollowees);
    }
}
