package spikes;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

public class ImdbTrailerTitle {

    @Test
    public void testGettingImdbTrailerTitle() throws IOException {
        String url = "http://www.imdb.com/video/imdb/vi113747993";

        Document doc = Jsoup.connect(url).userAgent("Mozilla").get();

        assertEquals("Ride Along Trailer (Trailer #2) - IMDb", doc.title());
    }
}
