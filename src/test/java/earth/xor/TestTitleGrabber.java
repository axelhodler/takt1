package earth.xor;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestTitleGrabber {

    private String title = "Regular expression - Wikipedia, the free encyclopedia";
    private String url = "http://en.wikipedia.org/wiki/Regex";

    private String imageUrl = "http://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Thompson-kleene-star.svg/503px-Thompson-kleene-star.svg.png";

    @Test
    public void testGrabbingTheTitle() {
	TitleGrabber tg = new TitleGrabber();

	String grabbedTitle = tg.grabTitle(url);

	assertEquals(title, grabbedTitle);
    }
    
    @Test
    public void testGrabbingTheTitleWithAnImage() {
	TitleGrabber tg = new TitleGrabber();
	
	String grabbedTitel = tg.grabTitle(imageUrl);
	
	assertEquals(null, grabbedTitel);
    }
}
