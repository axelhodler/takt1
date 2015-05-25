package org.xorrr.bot;

import org.xorrr.bot.finder.SpotifyTrackTitleFinder;
import org.xorrr.bot.finder.SpotifyUriFinder;
import org.xorrr.bot.finder.UrlFinder;

import com.google.inject.Inject;

public class RelayMessage {

  private UrlFinder urlFinder;

  @Inject
  public RelayMessage(UrlFinder urlFinder) {
    this.urlFinder = urlFinder;
  }

  public void relay(String message) {
    urlFinder.extractUrlIn(message);
  }

}
