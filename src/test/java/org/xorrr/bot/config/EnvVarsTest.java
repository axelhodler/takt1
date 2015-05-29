package org.xorrr.bot.config;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class EnvVarsTest {

  @Test
  public void constantsForConfigEnvVars() {
    assertNotNull(UsedEnvironmentVars.PORT);
    assertNotNull(UsedEnvironmentVars.NAME);
    assertNotNull(UsedEnvironmentVars.SERVER);
    assertNotNull(UsedEnvironmentVars.CHANNEL);
  }
}
