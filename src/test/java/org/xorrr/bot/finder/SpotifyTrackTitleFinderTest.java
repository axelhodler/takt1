package org.xorrr.bot.finder;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.xorrr.bot.helpers.IntegrationTest;

@Category(IntegrationTest.class)
public class SpotifyTrackTitleFinderTest {

    private final String URI_TRACK = "spotify:track:47vFyxAv24QxAOfdMuE3oV";
    private final String TITLE = "Unearthed by E.S. Posthumus on Spotify";

    @Test
    public void canGetTrackId() {
        SpotifyTrackTitleFinder finder = new SpotifyTrackTitleFinder();

        String title = finder.fetchTitleFrom(URI_TRACK);

        assertEquals(TITLE, title);
    }
}
