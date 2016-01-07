package org.xorrr.bot.messageextraction;

import org.xorrr.bot.boundaries.TitleFetcher;
import org.xorrr.bot.boundaries.impl.JsoupTitleFetcher;
import org.xorrr.bot.titlefinder.SpotifyTrackTitleFetcher;

import com.google.inject.Inject;

public class ChannelResponseFinder {

  private UrlExtractor urlFinder;
  private TitleFetcher titleFinder;
  private SpotifyUriExtractor spotifyUriFinder;
  private SpotifyTrackTitleFetcher trackTitleFinder;

  @Inject
  public ChannelResponseFinder(UrlExtractor urlFinder, TitleFetcher titleFinder,
      SpotifyUriExtractor spotifyUriFinder,
      SpotifyTrackTitleFetcher trackTitleFinder) {
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
