package org.xorrr.bot;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.xorrr.bot.config.BotConfig;
import org.xorrr.bot.di.Module;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

    public static void main(String[] args) throws Exception {
        PircBotX pircbotx = new PircBotX(createConfig());
        Bot bot = new Bot(pircbotx);

        bot.start();
    }

    private static Configuration<PircBotX> createConfig() {
        Injector injector = Guice.createInjector(new Module());
        BotConfig botConfig = new BotConfig(injector.getInstance(BotHandler.class));
        Configuration<PircBotX> config = botConfig.createConfig();
        return config;
    }
}
