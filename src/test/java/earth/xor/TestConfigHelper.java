package earth.xor;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestConfigHelper {

    @Test
    public void testGettingTheConfig() {
	ConfigHelper config = new ConfigHelper();
	
	String name = config.getBotName();
	
	assertEquals("botname", name);
    }
}
