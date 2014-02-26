package bot;

import org.pircbotx.Configuration;

import bot.config.EnvironmentVars;

public class BotConfig {
    public Configuration getConfigurationByEnvVars() {
        Configuration config = new Configuration.Builder()
            .setName(System.getenv(EnvironmentVars.NAME))
            .setServerHostname(System.getenv(EnvironmentVars.SERVER))
            .buildConfiguration();
        
        return config; 
    }
}
