package earth.xor;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class TestTitleGrabber {

    private String title = "Regular expression - Wikipedia, the free encyclopedia";
    private String url = "http://en.wikipedia.org/wiki/Regex";
    
    private String nonExistingUrl = "http://www.test.test";

    private String imageUrl = "http://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Thompson-kleene-star.svg/503px-Thompson-kleene-star.svg.png";

    private TitleGrabber tg;
    
    @Before
    public void setUp() {
	tg = new TitleGrabber();
    }
    
    @Test
    public void testGrabbingTheTitle() {

	String grabbedTitle = tg.grabTitle(url);

	assertEquals(title, grabbedTitle);
    }
    
    @Test
    public void testGrabbingTheTitleWithAnImage() {
	
	String grabbedTitel = tg.grabTitle(imageUrl);
	
	assertEquals(null, grabbedTitel);
    }
    
    @Test
    public void testGrabbingTheTitleOfANonExistingUrl() {
	
	String grabbedTitle = tg.grabTitle(nonExistingUrl);
	
	assertEquals(null, grabbedTitle);
    }
}
