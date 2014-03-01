package org.xorrr.bot.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlFinder {

    private String urlPattern = "((https?):((//)|(\\\\))+[\\w\\d:#@%/;$()"
            + "~_?\\+-=\\\\\\.&]*)";

    public String findUrl(String message) {
        Pattern p = Pattern.compile(urlPattern);
        Matcher m = p.matcher(message);

        return getUrlIfFound(m);
    }

    private String getUrlIfFound(Matcher m) {
        String url = null;

        if (m.find()) {
            url = m.group(1);
        }
        return url;
    }
}
