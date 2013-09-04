package earth.xor;

import java.io.IOException;
import java.net.UnknownHostException;

import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;

public class TitleGrabber {

    public String grabTitle(String url) {

	Document doc;
	try {
	    doc = Jsoup.connect(url).get();
	    return doc.title();
	} catch (UnsupportedMimeTypeException e) {
	    System.out.println("The URL is not a html page");
	} catch (UnknownHostException e) {
	    System.out.println("The URL does not lead to an existing resource");
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return null;
    }
}
