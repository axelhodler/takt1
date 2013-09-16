package bot;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import bot.ConfigHelper;

public class TestConfigHelper {

    private ConfigHelper config;
    
    @Before
    public void setUp() {
	config = new ConfigHelper();
    }
    
    @Test
    public void testGettingTheProperties() {
	String botName = config.getBotName();
	String serverName = config.getServer();
	String channelName= config.getChannel();
	
	assertEquals("botname", botName);
	assertEquals("irc.servername.org", serverName);
	assertEquals("#channelname", channelName);
    }
}
