package org.xorrr.bot.messageextraction;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xorrr.bot.messageextraction.ChannelResponseFinder;
import org.xorrr.bot.messageextraction.SpotifyUriExtractor;
import org.xorrr.bot.messageextraction.UrlExtractor;
import org.xorrr.bot.titlefinder.HtmlTitleFetcher;
import org.xorrr.bot.titlefinder.SpotifyTrackTitleFetcher;

@RunWith(MockitoJUnitRunner.class)
public class ChannelResponseFinderTest {

  @Mock
  UrlExtractor urlFinder;
  @Mock
  HtmlTitleFetcher titleFinder;
  @Mock
  SpotifyUriExtractor spotifyUriFinder;
  @Mock
  SpotifyTrackTitleFetcher spotifyTrackTitleFinder;

  private ChannelResponseFinder messageRelais;

  @Before
  public void initialize() {
    messageRelais = new ChannelResponseFinder(urlFinder, titleFinder,
        spotifyUriFinder, spotifyTrackTitleFinder);
  }

  @Test
  public void checksForHttpUrls() {
    messageRelais.decideResponseTo("message");

    verify(urlFinder).urlExtractableIn("message");
  }

  @Test
  public void fetchTitleOfExtractedUrl() {
    String messageContainingAUrl = "Hi, check this out www.foobar.org";
    given(urlFinder.urlExtractableIn(messageContainingAUrl)).willReturn(true);
    given(urlFinder.extractUrlIn(messageContainingAUrl)).willReturn(
        "www.foobar.org");

    messageRelais.decideResponseTo(messageContainingAUrl);

    verify(titleFinder).fetchTitleFrom("www.foobar.org");
  }

  @Test
  public void checkForSpotifyUrisInMessage() {
    given(urlFinder.urlExtractableIn("message")).willReturn(false);

    messageRelais.decideResponseTo("message");

    verify(spotifyUriFinder).isUriExtractableIn("message");
  }

  @Test
  public void getTitleOfSpotifyUriIfUriWasFound() {
    given(urlFinder.urlExtractableIn("message")).willReturn(false);
    given(spotifyUriFinder.isUriExtractableIn("message")).willReturn(true);

    messageRelais.decideResponseTo("message");

    verify(spotifyUriFinder).findUri("message");
  }
}
