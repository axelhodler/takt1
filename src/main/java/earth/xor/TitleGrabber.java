package earth.xor;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class TitleGrabber {

    public String grabTitle(String url) {

	Document doc;
	try {
	    doc = Jsoup.connect(url).get();
	    return doc.title();
	} catch (IOException e) {
	    e.printStackTrace();
	}

	return null;
    }
}
