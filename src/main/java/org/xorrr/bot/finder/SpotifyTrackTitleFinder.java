package org.xorrr.bot.finder;

public class SpotifyTrackTitleFinder {

    private final String URL_BASE = "http://open.spotify.com/track/";

    public String findTitle(String uriTrack) {
        String id = getTrackId(uriTrack);
        TitleFinder tf = new TitleFinder();
        return tf.findTitle(URL_BASE + id);
    }

    private String getTrackId(String uriTrack) {
        String[] parts = uriTrack.split(":");
        String id = parts[2];
        return id;
    }
}
