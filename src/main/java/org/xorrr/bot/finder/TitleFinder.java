package org.xorrr.bot.finder;

import java.io.IOException;
import java.net.UnknownHostException;

import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;

public class TitleFinder implements Finder{

    @Override
    public String findTitle(String url) {
        String title = null;
        try {
            title = getTitle(url);
        } catch (UnsupportedMimeTypeException e) {
            System.out.println("The URL is not a html page");
        } catch (UnknownHostException e) {
            System.out.println("The URL does not lead to an existing resource");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return title;
    }

    private String getTitle(String url) throws IOException {
        Document doc = Jsoup.connect(url).userAgent("Mozilla").get();
        return doc.title();
    }
}
