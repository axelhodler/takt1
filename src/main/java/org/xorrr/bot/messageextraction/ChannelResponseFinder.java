package org.xorrr.bot.messageextraction;

import org.springframework.stereotype.Service;
import org.xorrr.bot.boundaries.TitleFetcher;
import org.xorrr.bot.model.Title;
import org.xorrr.bot.titlefinder.SpotifyTrackTitleFetcher;

import java.util.Optional;

@Service
public class ChannelResponseFinder {

  private UrlExtractor urlFinder;
  private TitleFetcher titleFinder;
  private SpotifyUriExtractor spotifyUriFinder;
  private SpotifyTrackTitleFetcher trackTitleFinder;

  public ChannelResponseFinder(UrlExtractor urlFinder, TitleFetcher titleFinder,
      SpotifyUriExtractor spotifyUriFinder,
      SpotifyTrackTitleFetcher trackTitleFinder) {
    this.urlFinder = urlFinder;
    this.titleFinder = titleFinder;
    this.spotifyUriFinder = spotifyUriFinder;
    this.trackTitleFinder = trackTitleFinder;
  }

  public Optional<Title> decideResponseTo(String message) {
    Optional<Title> channelResponse = null;

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
