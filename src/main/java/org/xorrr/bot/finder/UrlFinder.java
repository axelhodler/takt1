package org.xorrr.bot.finder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlFinder {

    private String urlPattern = "((https?):((//)|(\\\\))+[\\w\\d:#@%/;$()"
            + "~_?\\+-=\\\\\\.&]*)";

    public String extractUrlIn(String message) {
        Pattern p = Pattern.compile(urlPattern);
        Matcher m = p.matcher(message);

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

}
