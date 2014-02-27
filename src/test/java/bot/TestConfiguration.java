package bot;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mock;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

import bot.config.EnvironmentVars;

public class TestConfiguration {

    @Mock
    PircBotX pircbotx;
    
    @Test
    public void configurationComplete() {
        BotConfig conf = new BotConfig();
        Configuration<PircBotX> configuration = conf.createConfig();

        assertEquals(System.getenv(EnvironmentVars.NAME),
                configuration.getName());
        assertEquals(System.getenv(EnvironmentVars.SERVER),
                configuration.getServerHostname());
        assertEquals("{" + System.getenv(EnvironmentVars.CHANNEL) + "=}",
                configuration.getAutoJoinChannels().toString());
    }
}
