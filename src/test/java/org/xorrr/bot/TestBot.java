package org.xorrr.bot;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.xorrr.bot.Bot;

@RunWith(MockitoJUnitRunner.class)
public class TestBot {

    @Mock
    PircBotX pircbotx;

    private Bot bot;

    @Before
    public void setUp() {
        bot = new Bot(pircbotx);
    }

    @Test
    public void botStars() throws IOException, IrcException {
        bot.start();

        verify(pircbotx, times(1)).startBot();
    }
}
