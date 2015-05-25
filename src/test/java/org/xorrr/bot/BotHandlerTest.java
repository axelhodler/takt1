package org.xorrr.bot;

import static org.mockito.Mockito.never;
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
import org.xorrr.bot.finder.SpotifyTrackTitleFinder;
import org.xorrr.bot.finder.SpotifyUriFinder;
import org.xorrr.bot.finder.HtmlTitleFetcher;
import org.xorrr.bot.finder.UrlFinder;

@RunWith(MockitoJUnitRunner.class)
public class BotHandlerTest {

    @Mock
    MessageEvent<PircBotX> event;
    @Mock
    HtmlTitleFetcher titleFinder;
    @Mock
    UrlFinder urlFinder;
    @Mock
    SpotifyTrackTitleFinder trackTitleFinder;
    @Mock
    SpotifyUriFinder uriFinder;
    @Mock
    Channel channel;
    @Mock
    OutputChannel outputChannel;

    private HandleChannelMessages botHandler;
    private final String MESSAGE = "msg";
    private final String URL = "url";
    private final String TITLE = "title";
    private final String TRACK_URI = "uri";

    @Before
    public void setUp() {
        botHandler = new HandleChannelMessages(titleFinder, urlFinder, trackTitleFinder, uriFinder);

        when(event.getMessage()).thenReturn(MESSAGE);
    }

    @Test
    public void messageAccessed() throws Exception {
        botHandler.onMessage(event);

        verify(event, times(2)).getMessage();
        verify(urlFinder).extractUrlIn(MESSAGE);
    }

    @Test
    public void urlIsNotGrabbed() throws Exception {
        when(urlFinder.extractUrlIn(MESSAGE)).thenReturn(null);

        botHandler.onMessage(event);

        verify(titleFinder, never()).fetchTitleFrom(URL);
    }

    @Test
    public void urlIsGrabbed() throws Exception {
        when(urlFinder.extractUrlIn(MESSAGE)).thenReturn(URL);

        botHandler.onMessage(event);

        verify(titleFinder).fetchTitleFrom(URL);
    }

    @Test
    public void titleFound() throws Exception {
        urlAndTitleAreFound();
        titleSentToCorrectChannel();

        botHandler.onMessage(event);

        verify(event).getChannel();
        verify(channel).send();
        verify(outputChannel).message(TITLE);
    }

    @Test
    public void titleNotFound() throws Exception {
        when(urlFinder.extractUrlIn(MESSAGE)).thenReturn(URL);
        when(titleFinder.fetchTitleFrom(URL)).thenReturn(null);

        botHandler.onMessage(event);

        verify(titleFinder).fetchTitleFrom(URL);
        verify(event, never()).getChannel();
    }

    @Test
    public void trackTitleFound() throws Exception {
        uriAndTitleAreFound();
        titleSentToCorrectChannel();

        botHandler.onMessage(event);

        verify(uriFinder).findUri(MESSAGE);
        verify(trackTitleFinder).fetchTitleFrom(TRACK_URI);
    }

    private void uriAndTitleAreFound() {
        when(uriFinder.findUri(MESSAGE)).thenReturn(TRACK_URI);
        when(titleFinder.fetchTitleFrom(URL)).thenReturn(TITLE);
    }

    private void titleSentToCorrectChannel() {
        when(event.getChannel()).thenReturn(channel);
        when(channel.send()).thenReturn(outputChannel);
    }

    private void urlAndTitleAreFound() {
        when(urlFinder.extractUrlIn(MESSAGE)).thenReturn(URL);
        when(titleFinder.fetchTitleFrom(URL)).thenReturn(TITLE);
    }
}
