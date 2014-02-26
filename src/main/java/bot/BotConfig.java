package bot;

import org.pircbotx.Configuration;

import bot.config.EnvironmentVars;
import bot.di.Module;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class BotConfig {
    public Configuration createConfig() {
        Injector injector = Guice.createInjector(new Module());
        TitleGrabber tg = injector.getInstance(TitleGrabber.class);
        UrlGrabber ug = injector.getInstance(UrlGrabber.class);

        Configuration config = new Configuration.Builder()
                .setName(System.getenv(EnvironmentVars.NAME))
                .setServerHostname(System.getenv(EnvironmentVars.SERVER))
                .addAutoJoinChannel(System.getenv(EnvironmentVars.CHANNEL))
                .addListener(new BotHandler(tg, ug)).buildConfiguration();

        return config;
    }
}
