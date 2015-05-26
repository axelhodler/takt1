package org.xorrr.bot.finder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpotifyUriFinder {

    private String urlPattern = "spotify:track:\\w{22}";

    public boolean isUriExtractableIn(String message) {
      Matcher m = findMatchesIn(message);

      return foundUri(m);
    }

    public String findUri(String message) {
      Matcher m = findMatchesIn(message);

      return getMatch(m);
    }

    private Matcher findMatchesIn(String message) {
      Pattern p = Pattern.compile(urlPattern);
      Matcher m = p.matcher(message);

      return m;
    }

    private String getMatch(Matcher m) {
        String uri = null;

        if (foundUri(m))
            uri = getFoundUri(m);

        return uri;
    }

    private String getFoundUri(Matcher m) {
        return m.group(0);
    }

    private boolean foundUri(Matcher m) {
        return m.find();
    }

}
