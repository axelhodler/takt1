package bot;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

import bot.config.EnvironmentVars;
import bot.di.Module;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class BotConfig {
    public Configuration<PircBotX> createConfig() {
        Injector injector = Guice.createInjector(new Module());
        BotHandler listener = injector.getInstance(BotHandler.class);

        Configuration<PircBotX> config = new Configuration.Builder<PircBotX>()
                .setName(System.getenv(EnvironmentVars.NAME))
                .setServerHostname(System.getenv(EnvironmentVars.SERVER))
                .addAutoJoinChannel(System.getenv(EnvironmentVars.CHANNEL))
                .addListener(listener).buildConfiguration();

        return config;
    }
}
