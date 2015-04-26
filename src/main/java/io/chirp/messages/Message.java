package io.chirp.messages;

import io.chirp.system.User;


/**
 *
 */
public class Message
{
    private User user;
    private String contents;
    private long creationTime;

    public Message(User user, String contents)
    {
        this.user = user;
        this.contents = contents;
        this.creationTime = System.currentTimeMillis();
    }

    public User getUser()
    {
        return user;
    }

    public String getContents()
    {
        return contents;
    }

    public long getCreationTime()
    {
        return creationTime;
    }

    private void addRoundedDuration(StringBuilder builder)
    {
        long diff = System.currentTimeMillis() - creationTime;
        long secs = diff / 1000;
        long mins = secs / 60;
        long hours = mins / 60;
        long days = hours / 24;

        builder.append("(");
        if(secs <= 60)
        {
            builder.append(secs).append((secs > 1 ? " seconds ago" : " second ago"));
        }
        else if(mins <= 60)
        {
            builder.append(mins).append((mins > 1 ? " minutes ago" : " minute ago"));
        }
        else if(hours <= 24)
        {
            builder.append(hours).append((hours > 1 ? " hours ago" : " hour ago"));
        }
        else
        {
            builder.append(days).append((days > 1 ? " days ago" : " day ago"));
        }
        builder.append(")");
    }

    public String toString(boolean printUser)
    {
        StringBuilder builder = new StringBuilder();
        if(printUser)
        {
            builder.append(user.getUsername());
            builder.append(" - ");
        }
        builder.append(contents).append(" ");
        addRoundedDuration(builder);
        return builder.toString();
    }

    @Override
    public String toString()
    {
        return "Message{" +
                "user=" + user +
                ", contents='" + contents + '\'' +
                ", creationTime=" + creationTime +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (creationTime != message.creationTime) return false;
        if (!contents.equals(message.contents)) return false;
        if (!user.equals(message.user)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = user.hashCode();
        result = 31 * result + contents.hashCode();
        result = 31 * result + (int) (creationTime ^ (creationTime >>> 32));
        return result;
    }
}
