package org.xorrr.bot.titlefinder;

import com.google.inject.Inject;
import org.xorrr.bot.boundaries.TitleFetcher;
import org.xorrr.bot.model.SpotifyTrackUri;
import org.xorrr.bot.model.Title;

import java.util.Optional;

public class SpotifyTrackTitleFetcher {

  private TitleFetcher titleFetcher;

  @Inject
  public SpotifyTrackTitleFetcher(TitleFetcher titleFetcher) {
    this.titleFetcher = titleFetcher;
  }

  public Optional<Title> fetchTitleFrom(String uriTrack) {
    return titleFetcher.fetchTitleFrom(
            "http://open.spotify.com/track/"
                    + new SpotifyTrackUri(uriTrack).trackId());
  }

}
