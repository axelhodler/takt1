package bot;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
	
	assertNotSame("", botName);
	assertNotSame("", serverName);
	assertNotSame("", channelName);
    }
}
