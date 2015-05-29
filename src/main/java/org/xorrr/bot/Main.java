package org.xorrr.bot;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.xorrr.bot.config.BotConfig;
import org.xorrr.bot.config.DependencyInjectionModule;
import org.xorrr.bot.config.UsedEnvironmentVars;
import org.xorrr.bot.webapp.BotWebInterface;
import org.xorrr.bot.webapp.BotWebInterfaceRunner;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

  public static void main(String[] args) throws Exception {
    createWebAppThread().start();

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

  /**
   * By running a webapp on the server we can allow the bot to stay active
   * on heroku without going to sleep after an hour of inactivity by
   * pinging the webapp each hour
   */
  private static Thread createWebAppThread() {
    Server server = new Server(getPort());
    ServletContextHandler context = new ServletContextHandler(
        ServletContextHandler.SESSIONS);
    BotWebInterface webapp = new BotWebInterface(server, context);

    Thread thread = new Thread(new BotWebInterfaceRunner(webapp));
    return thread;
  }

  private static Integer getPort() {
    return Integer.valueOf(System.getenv(UsedEnvironmentVars.PORT));
  }

  private static Configuration<PircBotX> createConfig() {
    Injector injector = Guice.createInjector(new DependencyInjectionModule());
    BotConfig botConfig = new BotConfig(
        injector.getInstance(HandleChannelMessages.class));
    Configuration<PircBotX> config = botConfig.createConfig();
    return config;
  }
}
