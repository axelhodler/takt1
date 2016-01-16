package org.xorrr.bot.titlefinder;

import com.google.inject.Inject;
import org.xorrr.bot.boundaries.TitleFetcher;

import java.util.Optional;

public class SpotifyTrackTitleFetcher {

  private final String URL_BASE = "http://open.spotify.com/track/";

  private TitleFetcher titleFetcher;

  @Inject
  public SpotifyTrackTitleFetcher(TitleFetcher titleFetcher) {
    this.titleFetcher = titleFetcher;
  }

  public Optional<String> fetchTitleFrom(String uriTrack) {
    String id = getTrackId(uriTrack);
    return titleFetcher.fetchTitleFrom(URL_BASE + id);
  }

  private String getTrackId(String uriTrack) {
    String[] parts = uriTrack.split(":");
    return parts[2];
  }
}
