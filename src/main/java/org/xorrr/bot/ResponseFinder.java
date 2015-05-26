package org.xorrr.bot;

import org.xorrr.bot.finder.HtmlTitleFetcher;
import org.xorrr.bot.finder.SpotifyTrackTitleFinder;
import org.xorrr.bot.finder.SpotifyUriFinder;
import org.xorrr.bot.finder.UrlFinder;

import com.google.inject.Inject;

public class ResponseFinder {

  private UrlFinder urlFinder;
  private HtmlTitleFetcher titleFinder;
  private SpotifyUriFinder spotifyUriFinder;
  private SpotifyTrackTitleFinder trackTitleFinder;

  @Inject
  public ResponseFinder(UrlFinder urlFinder, HtmlTitleFetcher titleFinder,
      SpotifyUriFinder spotifyUriFinder,
      SpotifyTrackTitleFinder trackTitleFinder) {
    this.urlFinder = urlFinder;
    this.titleFinder = titleFinder;
    this.spotifyUriFinder = spotifyUriFinder;
    this.trackTitleFinder = trackTitleFinder;
  }

  public String decideResponseTo(String message) {
    String channelResponse = "";

    if (urlFinder.urlExtractableIn(message)) {
      String extractedUrl = urlFinder.extractUrlIn(message);
      channelResponse = titleFinder.fetchTitleFrom(extractedUrl);
    } else if (spotifyUriFinder.isUriExtractableIn(message)) {
      String foundUri = spotifyUriFinder.findUri(message);
      channelResponse = trackTitleFinder.fetchTitleFrom(foundUri);
    }

    return channelResponse;
  }

}
