package org.xorrr.bot.boundaries.impl;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.xorrr.bot.boundaries.TitleFetcher;
import org.xorrr.bot.model.Title;

public class JsoupTitleFetcher implements TitleFetcher {

  @Override
  public Optional<Title> fetchTitleFrom(String url) {
    Title title = null;
    try {
      title = new Title(getTitle(url));
    } catch (Exception e) {
      /**
       We dont care if the url is malformed, a link to an image or sth else
       Should this happen the title is empty
       */
    }
    return Optional.ofNullable(title);
  }

  private String getTitle(String url) throws IOException {
    return Jsoup.connect(url).userAgent("Mozilla").get().title();
  }
}
