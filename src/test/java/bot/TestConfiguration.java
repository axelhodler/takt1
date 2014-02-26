package bot;

import static org.junit.Assert.*;

import org.junit.Test;
import org.pircbotx.Configuration;

import bot.config.EnvironmentVars;

public class TestConfiguration {

    @Test
    public void configurationComplete() {
        BotConfig conf = new BotConfig();
        Configuration configuration = conf.getConfigurationByEnvVars();

        assertEquals(System.getenv(EnvironmentVars.NAME),
                configuration.getName());
        assertEquals(System.getenv(EnvironmentVars.SERVER),
                configuration.getServerHostname());
    }
}
