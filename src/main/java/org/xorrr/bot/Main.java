package org.xorrr.bot;

import org.xorrr.bot.config.DependencyInjectionModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

  public static void main(String[] args) throws Exception {
    PircBot pircBot = new PircBot();
    IrcBotConfiguration ircBotConfig = new IrcBotConfiguration();
    ircBotConfig.initFromEnvironmentVars();
    Injector injector = Guice.createInjector(new DependencyInjectionModule());
    pircBot.start(ircBotConfig, injector.getInstance(HandleChannelMessages.class));
  }

}
