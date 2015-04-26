package io.chirp.system;

/**
 * A POJO representing a User
 */
public class User
{
    private String username;

    public User(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!username.equals(user.username)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return username.hashCode();
    }

    @Override
    public String toString()
    {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }
}
