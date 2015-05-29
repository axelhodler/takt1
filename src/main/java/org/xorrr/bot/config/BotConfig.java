package org.xorrr.bot.config;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.xorrr.bot.HandleChannelMessages;

import com.google.inject.Inject;

public class BotConfig {
  private HandleChannelMessages listener;

  @Inject
  public BotConfig(HandleChannelMessages handler) {
    this.listener = handler;
  }

  public Configuration<PircBotX> createConfig() {
    Configuration<PircBotX> config = new Configuration.Builder<PircBotX>()
        .setName(get(UsedEnvironmentVars.NAME))
        .setServerHostname(get(UsedEnvironmentVars.SERVER))
        .addAutoJoinChannel(get(UsedEnvironmentVars.CHANNEL)).addListener(listener)
        .setLogin(get(UsedEnvironmentVars.IDENT))
        .setNickservPassword(get(UsedEnvironmentVars.IDENT)).buildConfiguration();

    return config;
  }

  private String get(String var) {
    return System.getenv(var);
  }
}
