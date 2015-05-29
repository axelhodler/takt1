package org.xorrr.bot.messageextraction;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.xorrr.bot.helpers.IntegrationTest;
import org.xorrr.bot.titlefinder.HtmlTitleFetcher;
import org.xorrr.bot.titlefinder.SpotifyTrackTitleFetcher;

@Category(IntegrationTest.class)
public class SpotifyTrackTitleFinderIT {

  private final String URI_TRACK = "spotify:track:47vFyxAv24QxAOfdMuE3oV";
  private final String TITLE = "Nara (Theme to Cold Case), a song by E.S. Posthumus on Spotify";

  @Test
  public void canGetTrackId() {
    SpotifyTrackTitleFetcher finder = new SpotifyTrackTitleFetcher(
        new HtmlTitleFetcher());

    String title = finder.fetchTitleFrom(URI_TRACK);

    assertEquals(TITLE, title);
  }
}