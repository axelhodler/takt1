package bot;

import org.pircbotx.Configuration;

import bot.config.EnvironmentVars;

public class BotConfig {
    public Configuration createConfig() {
        Configuration config = new Configuration.Builder()
            .setName(System.getenv(EnvironmentVars.NAME))
            .setServerHostname(System.getenv(EnvironmentVars.SERVER))
            .addAutoJoinChannel(System.getenv(EnvironmentVars.CHANNEL))
            .buildConfiguration();
        
        return config; 
    }
}
