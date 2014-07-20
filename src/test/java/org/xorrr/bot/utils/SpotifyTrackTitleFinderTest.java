package org.xorrr.bot.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SpotifyTrackTitleFinderTest {

    private final String URI_TRACK = "spotify:track:47vFyxAv24QxAOfdMuE3oV";
    private final String TITLE = "Nara (Theme to Cold Case) by E.S. Posthumus on Spotify";

    @Test
    public void canGetTrackId() {
        SpotifyTrackTitleFinder finder = new SpotifyTrackTitleFinder();

        String title = finder.findTitle(URI_TRACK);

        assertEquals(TITLE, title);
    }
}
