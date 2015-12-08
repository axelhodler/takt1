package org.xorrr.bot;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.xorrr.bot.config.BotConfig;
import org.xorrr.bot.config.DependencyInjectionModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

  public static void main(String[] args) throws Exception {
    createBot().start();
  }

  private static BotStarter createBot() {
    BotStarter bot = new BotStarter(createPircBotXWithConfig());
    return bot;
  }

  private static PircBotX createPircBotXWithConfig() {
    PircBotX pircbotx = new PircBotX(createConfig());
    return pircbotx;
  }

  private static Configuration<PircBotX> createConfig() {
    Injector injector = Guice.createInjector(new DependencyInjectionModule());
    BotConfig botConfig = new BotConfig(
        injector.getInstance(HandleChannelMessages.class));
    Configuration<PircBotX> config = botConfig.createConfig();
    return config;
  }
}
