package bot;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mock;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.Listener;

import bot.config.EnvironmentVars;

public class TestConfiguration {

    @Mock
    PircBotX pircbotx;
    
    @Test
    public void configurationComplete() {
        Listener listener = new Bot(pircbotx);
        BotConfig conf = new BotConfig();
        Configuration configuration = conf.createConfig(listener);

        assertEquals(System.getenv(EnvironmentVars.NAME),
                configuration.getName());
        assertEquals(System.getenv(EnvironmentVars.SERVER),
                configuration.getServerHostname());
        assertEquals("{" + System.getenv(EnvironmentVars.CHANNEL) + "=}",
                configuration.getAutoJoinChannels().toString());
    }
}
