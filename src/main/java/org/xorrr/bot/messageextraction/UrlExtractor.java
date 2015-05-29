package org.xorrr.bot.messageextraction;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlExtractor {

  private String urlPattern = "((https?):((//)|(\\\\))+[\\w\\d:#@%/;$()"
      + "~_?\\+-=\\\\\\.&]*)";

  public boolean urlExtractableIn(String message) {
    Matcher matcher = calculateMatches(message);

    return urlFound(matcher);
  }

  public String extractUrlIn(String message) {
    Matcher m = calculateMatches(message);

    String url = null;

    if (urlFound(m)) {
      url = extractUrl(m);
    }

    return url;
  }

  private String extractUrl(Matcher m) {
    return m.group(1);
  }

  private boolean urlFound(Matcher m) {
    return m.find();
  }

  private Matcher calculateMatches(String message) {
    Pattern pattern = Pattern.compile(urlPattern);
    Matcher matcher = pattern.matcher(message);
    return matcher;
  }

}
