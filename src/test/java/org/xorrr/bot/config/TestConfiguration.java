package org.xorrr.bot.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.xorrr.bot.BotHandler;
import org.xorrr.bot.config.BotConfig;
import org.xorrr.bot.config.EnvironmentVars;

@RunWith(MockitoJUnitRunner.class)
public class TestConfiguration {

    @Mock
    BotHandler handler;

    private Configuration<PircBotX> configuration;

    @Before
    public void setUp() {
        BotConfig conf = new BotConfig(handler);
        this.configuration = conf.createConfig();
    }

    @Test
    public void nameIsSet() {
        assertEquals(System.getenv(EnvironmentVars.NAME),
                configuration.getName());
    }

    @Test
    public void serverHostnameIsSet() {
        assertEquals(System.getenv(EnvironmentVars.SERVER),
                configuration.getServerHostname());
    }

    @Test
    public void channelIsSet() {
        assertTrue(configuration.getAutoJoinChannels().asMultimap()
                .containsKey(System.getenv(EnvironmentVars.CHANNEL)));
    }

    @Test
    public void listenerIsSet() {
        assertTrue(configuration.getListenerManager().listenerExists(handler));
    }
}
