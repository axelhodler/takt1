package org.xorrr.bot.finder;

import com.google.inject.Inject;

public class SpotifyTrackTitleFinder {

  private final String URL_BASE = "http://open.spotify.com/track/";

  private HtmlTitleFetcher titleFetcher;

  @Inject
  public SpotifyTrackTitleFinder(HtmlTitleFetcher titleFetcher) {
    this.titleFetcher = titleFetcher;
  }

  public String fetchTitleFrom(String uriTrack) {
    String id = getTrackId(uriTrack);
    return titleFetcher.fetchTitleFrom(URL_BASE + id);
  }

  private String getTrackId(String uriTrack) {
    String[] parts = uriTrack.split(":");
    String id = parts[2];
    return id;
  }
}
