package org.xorrr.bot;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xorrr.bot.finder.HtmlTitleFetcher;
import org.xorrr.bot.finder.SpotifyTrackTitleFinder;
import org.xorrr.bot.finder.SpotifyUriFinder;
import org.xorrr.bot.finder.UrlFinder;

@RunWith(MockitoJUnitRunner.class)
public class ResponseFinderTest {

  @Mock
  UrlFinder urlFinder;
  @Mock
  HtmlTitleFetcher titleFinder;
  @Mock
  SpotifyUriFinder spotifyUriFinder;
  @Mock
  SpotifyTrackTitleFinder spotifyTrackTitleFinder;

  private ResponseFinder messageRelais;

  @Before
  public void initialize() {
    messageRelais = new ResponseFinder(urlFinder, titleFinder,
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
