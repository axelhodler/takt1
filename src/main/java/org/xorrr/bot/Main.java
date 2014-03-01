package org.xorrr.bot;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.xorrr.bot.config.BotConfig;
import org.xorrr.bot.config.EnvironmentVars;
import org.xorrr.bot.di.Module;
import org.xorrr.bot.webapp.WebApp;
import org.xorrr.bot.webapp.WebAppRunnable;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

    public static void main(String[] args) throws Exception {
        createWebAppThread().start();

        createBot().start();
    }

    private static Bot createBot() {
        Bot bot = new Bot(createPircBotXWithConfig());
        return bot;
    }

    private static PircBotX createPircBotXWithConfig() {
        PircBotX pircbotx = new PircBotX(createConfig());
        return pircbotx;
    }

    private static Thread createWebAppThread() {
        Server server = new Server(getPort());
        ServletContextHandler context = new ServletContextHandler(
                ServletContextHandler.SESSIONS);
        WebApp webapp = new WebApp(server, context);

        Thread thread = new Thread(new WebAppRunnable(webapp));
        return thread;
    }

    private static Integer getPort() {
        return Integer.valueOf(System
                .getenv(EnvironmentVars.PORT));
    }

    private static Configuration<PircBotX> createConfig() {
        Injector injector = Guice.createInjector(new Module());
        BotConfig botConfig = new BotConfig(
                injector.getInstance(BotHandler.class));
        Configuration<PircBotX> config = botConfig.createConfig();
        return config;
    }
}
