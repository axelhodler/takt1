package bot;

import org.pircbotx.Configuration;
import org.pircbotx.hooks.Listener;

import bot.config.EnvironmentVars;

public class BotConfig {
    public Configuration createConfig(Listener listener) {
        Configuration config = new Configuration.Builder()
            .setName(System.getenv(EnvironmentVars.NAME))
            .setServerHostname(System.getenv(EnvironmentVars.SERVER))
            .addAutoJoinChannel(System.getenv(EnvironmentVars.CHANNEL))
            .addListener(listener)
            .buildConfiguration();
        
        return config; 
    }
}
