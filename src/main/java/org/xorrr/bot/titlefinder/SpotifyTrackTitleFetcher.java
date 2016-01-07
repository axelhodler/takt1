package org.xorrr.bot.titlefinder;

import com.google.inject.Inject;
import org.xorrr.bot.boundaries.impl.JsoupTitleFetcher;

public class SpotifyTrackTitleFetcher {

  private final String URL_BASE = "http://open.spotify.com/track/";

  private JsoupTitleFetcher titleFetcher;

  @Inject
  public SpotifyTrackTitleFetcher(JsoupTitleFetcher titleFetcher) {
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
