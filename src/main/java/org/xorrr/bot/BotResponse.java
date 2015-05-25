package org.xorrr.bot;

import org.xorrr.bot.finder.TitleFinder;
import org.xorrr.bot.finder.UrlFinder;

import com.google.inject.Inject;

public class BotResponse {

  private UrlFinder urlFinder;
  private TitleFinder titleFinder;

  @Inject
  public BotResponse(UrlFinder urlFinder, TitleFinder titleFinder) {
    this.urlFinder = urlFinder;
    this.titleFinder = titleFinder;
  }

  public String decideResponseTo(String message) {
    String extractedUrl = urlFinder.extractUrlIn(message);
    if (extractedUrl != null) {
      titleFinder.findTitle(extractedUrl);
    };
    return null;
  }

}
