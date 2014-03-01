package org.xorrr.bot;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.xorrr.bot.config.BotConfig;
import org.xorrr.bot.config.EnvironmentVars;
import org.xorrr.bot.di.Module;
import org.xorrr.bot.webapp.WebApp;
import org.xorrr.bot.webapp.WebAppThread;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

    public static void main(String[] args) throws Exception {
        Server server = new Server(Integer.valueOf(System
                .getenv(EnvironmentVars.PORT)));
        ServletContextHandler context = new ServletContextHandler(
                ServletContextHandler.SESSIONS);
        WebApp webapp = new WebApp(server, context);

        Thread thread = new Thread(new WebAppThread(webapp));
        thread.start();

        PircBotX pircbotx = new PircBotX(createConfig());
        Bot bot = new Bot(pircbotx);
        bot.start();
    }

    private static Configuration<PircBotX> createConfig() {
        Injector injector = Guice.createInjector(new Module());
        BotConfig botConfig = new BotConfig(
                injector.getInstance(BotHandler.class));
        Configuration<PircBotX> config = botConfig.createConfig();
        return config;
    }
}
