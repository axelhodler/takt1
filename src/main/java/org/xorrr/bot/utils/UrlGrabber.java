package org.xorrr.bot.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlGrabber {

    private String urlPattern = "((https?):((//)|(\\\\))+[\\w\\d:#@%/;$()"
            + "~_?\\+-=\\\\\\.&]*)";

    public String grabUrl(String sentence) {
        Pattern p = Pattern.compile(urlPattern);
        Matcher m = p.matcher(sentence);

        String url = null;

        url = ifMessageContainsUrl(m, url);
        return url;
    }

    private String ifMessageContainsUrl(Matcher m, String url) {
        if (m.find()) {
            url = m.group(1);
        }
        return url;
    }
}
