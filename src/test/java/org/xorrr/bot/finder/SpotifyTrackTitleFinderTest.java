package org.xorrr.bot.finder;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SpotifyTrackTitleFinderTest {

  @Mock
  HtmlTitleFetcher titleFetcher;

  private final String SPOTIFY_TRACK_ID = "4MYBNI0m7Ot8Llm2OsjzXQ";
  private final String SPOTIFY_TRACK_URI = "spotify:track:" + SPOTIFY_TRACK_ID;
  private final String URL_BASE = "http://open.spotify.com/track/";

  @Test
  public void usesHtmlTitleFetcherToFetchTitle() {
    SpotifyTrackTitleFinder trackNameFinder = new SpotifyTrackTitleFinder(titleFetcher);

    trackNameFinder.fetchTitleFrom(SPOTIFY_TRACK_URI);

    verify(titleFetcher).fetchTitleFrom(URL_BASE + SPOTIFY_TRACK_ID);
  }
}
