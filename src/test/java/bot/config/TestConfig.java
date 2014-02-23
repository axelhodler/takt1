package bot.config;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestConfig {

    @Test
    public void constantsForConfigEnvVars() {
        assertNotNull(EnvironmentVars.PORT);
        assertNotNull(EnvironmentVars.NAME);
        assertNotNull(EnvironmentVars.SERVER);
        assertNotNull(EnvironmentVars.CHANNEL);
        assertNotNull(EnvironmentVars.IDENT);
        assertNotNull(EnvironmentVars.PASS);
        assertNotNull(EnvironmentVars.WEBAPPURL);
        assertNotNull(EnvironmentVars.RESTAPIURL);
    }

}
