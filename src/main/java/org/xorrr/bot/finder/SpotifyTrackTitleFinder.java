package org.xorrr.bot.finder;

public class SpotifyTrackTitleFinder implements TitleFetcher {

    private final String URL_BASE = "http://open.spotify.com/track/";

    @Override
    public String fetchTitleFrom(String uriTrack) {
        String id = getTrackId(uriTrack);
        HtmlTitleFetcher tf = new HtmlTitleFetcher();
        return tf.fetchTitleFrom(URL_BASE + id);
    }

    private String getTrackId(String uriTrack) {
        String[] parts = uriTrack.split(":");
        String id = parts[2];
        return id;
    }
}
