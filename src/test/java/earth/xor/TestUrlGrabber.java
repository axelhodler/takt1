package earth.xor;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestUrlGrabber {

    private String testUrl = "http://en.wikipedia.org/wiki/Regex";
    private String secondTestUrl = "http://en.wikipedia.org/wiki/Regular_expression";
    
    private String testSentence = "foo " + testUrl + " bar baz";
    private String secondTestSentence= "foo bar " + secondTestUrl + " baz";
    
    @Test
    public void testGettingTheUrl() {
	
	UrlGrabber ug = new UrlGrabber();
	
	String url = ug.grabUrl(testSentence);
	
	String secondUrl = ug.grabUrl(secondTestSentence);
	
	assertEquals(testUrl, url);
	assertEquals(secondTestUrl, secondUrl);	
    }
}
