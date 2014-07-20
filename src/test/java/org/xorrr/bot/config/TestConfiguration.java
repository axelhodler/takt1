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
import org.xorrr.bot.util.EnvironmentVars;

@RunWith(MockitoJUnitRunner.class)
public class TestConfiguration {

    @Mock
    BotHandler handler;

    private Configuration<PircBotX> configuration;

    private String get(String var) {
        return System.getenv(var);
    }

    @Before
    public void setUp() {
        BotConfig conf = new BotConfig(handler);
        this.configuration = conf.createConfig();
    }

    @Test
    public void nameIsSet() {
        assertEquals(get(EnvironmentVars.NAME),
                configuration.getName());
    }

    @Test
    public void serverHostnameIsSet() {
        assertEquals(get(EnvironmentVars.SERVER),
                configuration.getServerHostname());
    }

    @Test
    public void channelIsSet() {
        assertTrue(configuration.getAutoJoinChannels().asMultimap()
                .containsKey(get(EnvironmentVars.CHANNEL)));
    }

    @Test
    public void listenerIsSet() {
        assertTrue(configuration.getListenerManager().listenerExists(handler));
    }

    @Test
    public void loginIsSet() {
        assertEquals(get(EnvironmentVars.IDENT),
                configuration.getLogin());
    }

    @Test
    public void nickservPasswordIsSet() {
        assertEquals(get(EnvironmentVars.PASSWORD),
                configuration.getNickservPassword());
    }
}
