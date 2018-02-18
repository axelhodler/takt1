package org.xorrr.bot.messageextraction;

import org.springframework.stereotype.Service;
import org.xorrr.bot.boundaries.TitleFetcher;
import org.xorrr.bot.model.Title;
import org.xorrr.bot.titlefinder.SpotifyTrackTitleFetcher;

import java.util.Optional;

@Service
public class ChannelResponseFinder {

  private TitleFetcher titleFinder;
  private SpotifyTrackTitleFetcher trackTitleFinder;

  public ChannelResponseFinder(TitleFetcher titleFinder,
      SpotifyTrackTitleFetcher trackTitleFinder) {
    this.titleFinder = titleFinder;
    this.trackTitleFinder = trackTitleFinder;
  }

  public Optional<Title> decideResponseTo(String message) {
    Optional<Title> channelResponse = null;
    UrlExtractor urlExtractor = new UrlExtractor();
    SpotifyUriExtractor spotifyUriExtractor = new SpotifyUriExtractor();
    if (urlExtractor.urlExtractableIn(message)) {
      String extractedUrl = urlExtractor.extractUrlIn(message);
      channelResponse = titleFinder.fetchTitleFrom(extractedUrl);
    } else if (spotifyUriExtractor.isUriExtractableIn(message)) {
      String foundUri = spotifyUriExtractor.findUri(message);
      channelResponse = trackTitleFinder.fetchTitleFrom(foundUri);
    }

    return channelResponse;
  }

}
