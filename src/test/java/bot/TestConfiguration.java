package bot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

import bot.config.EnvironmentVars;

@RunWith(MockitoJUnitRunner.class)
public class TestConfiguration {

    @Mock
    PircBotX pircbotx;
    @Mock
    BotHandler handler;

    @Test
    public void configurationComplete() {
        BotConfig conf = new BotConfig(handler);
        Configuration<PircBotX> configuration = conf.createConfig();

        assertEquals(System.getenv(EnvironmentVars.NAME),
                configuration.getName());
        assertEquals(System.getenv(EnvironmentVars.SERVER),
                configuration.getServerHostname());
        assertEquals("{" + System.getenv(EnvironmentVars.CHANNEL) + "=}",
                configuration.getAutoJoinChannels().toString());
        assertTrue(configuration.getListenerManager().listenerExists(handler));
    }
}
