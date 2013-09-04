package earth.xor;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestTitleGrabber {
    
    private String title = "Regular expression - Wikipedia, the free encyclopedia";
    private String url = "http://en.wikipedia.org/wiki/Regex";
    
    @Test
    public void testGrabbingTheTitle() {
	TitleGrabber tg = new TitleGrabber();
	
	String grabbedTitle = tg.grabTitle(url);
	
	assertEquals(title, grabbedTitle);
    }
}
