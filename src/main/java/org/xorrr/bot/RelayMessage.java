package org.xorrr.bot;

import org.xorrr.bot.finder.TitleFinder;
import org.xorrr.bot.finder.UrlFinder;

import com.google.inject.Inject;

public class RelayMessage {

  private UrlFinder urlFinder;
  private TitleFinder titleFinder;

  @Inject
  public RelayMessage(UrlFinder urlFinder, TitleFinder titleFinder) {
    this.urlFinder = urlFinder;
    this.titleFinder = titleFinder;
  }

  public void relay(String message) {
    String extractedUrl = urlFinder.extractUrlIn(message);
    if (extractedUrl != null) {
      titleFinder.findTitle(extractedUrl);
    };
  }

}
