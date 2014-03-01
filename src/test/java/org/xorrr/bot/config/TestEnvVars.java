package org.xorrr.bot.config;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class TestEnvVars {

    @Test
    public void constantsForConfigEnvVars() {
        assertNotNull(EnvironmentVars.PORT);
        assertNotNull(EnvironmentVars.NAME);
        assertNotNull(EnvironmentVars.SERVER);
        assertNotNull(EnvironmentVars.CHANNEL);
    }
}
