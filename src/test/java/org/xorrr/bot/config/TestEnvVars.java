package org.xorrr.bot.config;

import static org.junit.Assert.*;

import org.junit.Test;
import org.xorrr.bot.config.EnvironmentVars;

public class TestEnvVars {

    @Test
    public void constantsForConfigEnvVars() {
        assertNotNull(EnvironmentVars.PORT);
        assertNotNull(EnvironmentVars.NAME);
        assertNotNull(EnvironmentVars.SERVER);
        assertNotNull(EnvironmentVars.CHANNEL);
        assertNotNull(EnvironmentVars.IDENT);
        assertNotNull(EnvironmentVars.PASS);
        assertNotNull(EnvironmentVars.RESTAPIURL);
    }

}
