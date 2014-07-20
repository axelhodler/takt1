package org.xorrr.bot;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.output.OutputChannel;
import org.xorrr.bot.BotHandler;
import org.xorrr.bot.finder.TitleFinder;
import org.xorrr.bot.finder.UrlFinder;

@RunWith(MockitoJUnitRunner.class)
public class TestBotHandler {

    @Mock
    MessageEvent<PircBotX> event;
    @Mock
    TitleFinder tg;
    @Mock
    UrlFinder ug;
    @Mock
    Channel channel;
    @Mock
    OutputChannel outputChannel;

    private BotHandler botHandler;
    private final String MESSAGE = "msg";
    private final String URL = "url";
    private final String TITLE = "title";

    @Before
    public void setUp() {
        botHandler = new BotHandler(tg, ug);

        when(event.getMessage()).thenReturn(MESSAGE);
    }

    @Test
    public void messageAccessed() throws Exception {
        botHandler.onMessage(event);

        verify(event, times(1)).getMessage();
        verify(ug, times(1)).findUrl(MESSAGE);
    }

    @Test
    public void urlIsNotGrabbed() throws Exception {
        when(ug.findUrl(MESSAGE)).thenReturn(null);

        botHandler.onMessage(event);

        verify(tg, times(0)).findTitle(URL);
    }

    @Test
    public void urlIsGrabbed() throws Exception {
        when(ug.findUrl(MESSAGE)).thenReturn(URL);

        botHandler.onMessage(event);

        verify(tg, times(1)).findTitle(URL);
    }

    @Test
    public void titleFound() throws Exception {
        when(ug.findUrl(MESSAGE)).thenReturn(URL);
        when(tg.findTitle(URL)).thenReturn(TITLE);
        
        when(event.getChannel()).thenReturn(channel);
        when(channel.send()).thenReturn(outputChannel);

        botHandler.onMessage(event);

        verify(event, times(1)).getChannel();
        verify(channel, times(1)).send();
        verify(outputChannel, times(1)).message(TITLE);
    }

    @Test
    public void titleNotFound() throws Exception {
        when(ug.findUrl(MESSAGE)).thenReturn(URL);
        when(tg.findTitle(URL)).thenReturn(null);

        botHandler.onMessage(event);

        verify(tg, times(1)).findTitle(URL);
        verify(event, times(0)).getChannel();
    }
}
