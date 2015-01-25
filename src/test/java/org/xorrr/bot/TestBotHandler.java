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
import org.xorrr.bot.finder.TitleFinder;
import org.xorrr.bot.finder.UrlFinder;

@RunWith(MockitoJUnitRunner.class)
public class TestBotHandler {

    @Mock
    MessageEvent<PircBotX> event;
    @Mock
    TitleFinder titleFinder;
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

    private MessageHandler botHandler;
    private final String MESSAGE = "msg";
    private final String URL = "url";
    private final String TITLE = "title";
    private final String TRACK_URI = "uri";

    @Before
    public void setUp() {
        botHandler = new MessageHandler(titleFinder, urlFinder, trackTitleFinder, uriFinder);

        when(event.getMessage()).thenReturn(MESSAGE);
    }

    @Test
    public void messageAccessed() throws Exception {
        botHandler.onMessage(event);

        verify(event, times(2)).getMessage();
        verify(urlFinder).findUrl(MESSAGE);
    }

    @Test
    public void urlIsNotGrabbed() throws Exception {
        when(urlFinder.findUrl(MESSAGE)).thenReturn(null);

        botHandler.onMessage(event);

        verify(titleFinder, never()).findTitle(URL);
    }

    @Test
    public void urlIsGrabbed() throws Exception {
        when(urlFinder.findUrl(MESSAGE)).thenReturn(URL);

        botHandler.onMessage(event);

        verify(titleFinder).findTitle(URL);
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
        when(urlFinder.findUrl(MESSAGE)).thenReturn(URL);
        when(titleFinder.findTitle(URL)).thenReturn(null);

        botHandler.onMessage(event);

        verify(titleFinder).findTitle(URL);
        verify(event, never()).getChannel();
    }

    @Test
    public void trackTitleFound() throws Exception {
        uriAndTitleAreFound();
        titleSentToCorrectChannel();

        botHandler.onMessage(event);

        verify(uriFinder).findUri(MESSAGE);
        verify(trackTitleFinder).findTitle(TRACK_URI);
    }

    private void uriAndTitleAreFound() {
        when(uriFinder.findUri(MESSAGE)).thenReturn(TRACK_URI);
        when(titleFinder.findTitle(URL)).thenReturn(TITLE);
    }

    private void titleSentToCorrectChannel() {
        when(event.getChannel()).thenReturn(channel);
        when(channel.send()).thenReturn(outputChannel);
    }

    private void urlAndTitleAreFound() {
        when(urlFinder.findUrl(MESSAGE)).thenReturn(URL);
        when(titleFinder.findTitle(URL)).thenReturn(TITLE);
    }
}
