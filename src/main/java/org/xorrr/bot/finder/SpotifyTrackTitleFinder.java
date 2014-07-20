package org.xorrr.bot.finder;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class SpotifyTrackTitleFinder {

    private final String URL_BASE = "http://open.spotify.com/track/";

    public String findTitle(String uriTrack) {
        String id = getTrackId(uriTrack);

        String title = null;
        try {
            Document doc = getHtmlDocument(id);
            title = doc.title();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return title;
    }

    private String getTrackId(String uriTrack) {
        String[] parts = uriTrack.split(":");
        String id = parts[2];
        return id;
    }

    private Document getHtmlDocument(String id) throws IOException {
        return Jsoup.connect(URL_BASE + id).userAgent("Mozilla")
                .get();
    }
}
