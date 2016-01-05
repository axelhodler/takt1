package org.xorrr.bot.messageextraction;

import org.junit.Test;
import org.xorrr.bot.boundaries.impl.HtmlTitleFetcher;
import org.xorrr.bot.titlefinder.SpotifyTrackTitleFetcher;

import static org.junit.Assert.assertEquals;

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
