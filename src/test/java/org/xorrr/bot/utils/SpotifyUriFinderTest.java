package org.xorrr.bot.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SpotifyUriFinderTest {

    private static final String URI_TRACK = "spotify:track:47vFyxAv24QxAOfdMuE3oV";
    private static final String MESSAGE = "hey check " + URI_TRACK
            + " this out!";

    @Test
    public void canFindUri() {
        SpotifyUriFinder finder = new SpotifyUriFinder();

        String spotifyUri = finder.findUri(MESSAGE);

        assertEquals(URI_TRACK, spotifyUri);
    }
}
