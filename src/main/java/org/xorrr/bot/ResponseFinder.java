package org.xorrr.bot;

import org.xorrr.bot.finder.HtmlTitleFetcher;
import org.xorrr.bot.finder.UrlFinder;

import com.google.inject.Inject;

public class ResponseFinder {

  private UrlFinder urlFinder;
  private HtmlTitleFetcher titleFinder;

  @Inject
  public ResponseFinder(UrlFinder urlFinder, HtmlTitleFetcher titleFinder) {
    this.urlFinder = urlFinder;
    this.titleFinder = titleFinder;
  }

  public String decideResponseTo(String message) {
    String extractedUrl = urlFinder.extractUrlIn(message);
    if (extractedUrl != null) {
      titleFinder.fetchTitleFrom(extractedUrl);
    };
    return null;
  }

}
