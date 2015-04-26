package io.chirp.messages;

import io.chirp.system.User;
import org.junit.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Test Class for the default Message Log
 */
public class DefaultMessageLogTest
{
    private static DefaultMessageLog messageLog;
    private static Set<User> userSet;
    private static User testUserA;
    private static User testUserB;
    private static User testUserC;
    private static Message testMessageA;
    private static Message testMessageB;
    private static Message testMessageC;

    @BeforeClass
    public static void suiteSetup()
    {
        messageLog = new DefaultMessageLog();

        userSet = new HashSet<>();
        testUserA = new User("testUserA");
        testUserB = new User("testUserB");
        testUserC = new User("testUserC");
        testMessageA = new Message(testUserA, "message from testUserA");
        try{Thread.sleep(100);}catch (InterruptedException ie){ie.printStackTrace();}
        testMessageB = new Message(testUserB, "message from testUserB");
        try{Thread.sleep(100);}catch (InterruptedException ie){ie.printStackTrace();}
        testMessageC = new Message(testUserC, "message from testUserC");
        messageLog.addMessage(testMessageA);
        messageLog.addMessage(testMessageB);
        messageLog.addMessage(testMessageC);
    }

    @After
    public void tearDown()
    {
        userSet.clear();
    }

    @Test
    public void testGetSingleTimeLine()
    {
        List<Message> onlyAMessages = Arrays.asList(testMessageA);
        userSet.add(testUserA);
        List<Message> actualTimeLine = messageLog.getTimeLine(userSet);
        Assert.assertEquals("The timeLine returned doesn't match expected", onlyAMessages,actualTimeLine);
    }

    @Test
    public void testGetMultipleTimeLines()
    {
        List<Message> onlyAandBMessages = Arrays.asList(testMessageB, testMessageA);
        userSet.add(testUserA);
        userSet.add(testUserB);
        List<Message> actualTimeLine = messageLog.getTimeLine(userSet);
        Assert.assertEquals("The timeLine returned doesn't match expected", onlyAandBMessages,actualTimeLine);

    }

    @Test
    public void testGetAllTimelines()
    {
        List<Message> allMessages = Arrays.asList(testMessageC, testMessageB, testMessageA);
        userSet.add(testUserA);
        userSet.add(testUserB);
        userSet.add(testUserC);
        List<Message> actualTimeLine = messageLog.getTimeLine(userSet);
        Assert.assertEquals("The timeLine returned doesn't match expected", allMessages,actualTimeLine);

    }

}
