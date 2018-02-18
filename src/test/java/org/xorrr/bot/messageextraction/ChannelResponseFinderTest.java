package org.xorrr.bot.messageextraction;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xorrr.bot.boundaries.TitleFetcher;
import org.xorrr.bot.titlefinder.SpotifyTrackTitleFetcher;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ChannelResponseFinderTest {

  @Mock
  TitleFetcher titleFinder;
  @Mock
  SpotifyTrackTitleFetcher spotifyTrackTitleFinder;

  private ChannelResponseFinder messageRelais;

  @Before
  public void initialize() {
    messageRelais = new ChannelResponseFinder(titleFinder, spotifyTrackTitleFinder);
  }

  @Test
  public void fetchTitleOfExtractedUrl() {
    String messageContainingAUrl = "Hi, check this out http://www.foobar.org";

    messageRelais.decideResponseTo(messageContainingAUrl);

    verify(titleFinder).fetchTitleFrom("http://www.foobar.org");
  }

  @Test
  public void fetchTitleOfExtractedTrack() {
    String messageContainingTrackId = "Foo spotify:track:47vFyxAv24QxAOfdMuE3oV bar";

    messageRelais.decideResponseTo(messageContainingTrackId);

    verify(spotifyTrackTitleFinder).fetchTitleFrom("spotify:track:47vFyxAv24QxAOfdMuE3oV");
  }
}
