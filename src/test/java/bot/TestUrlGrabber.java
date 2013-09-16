package bot;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import bot.UrlGrabber;

public class TestUrlGrabber {

    private String testUrl = "http://en.wikipedia.org/wiki/Regex";
    private String testUrlTwo = "http://en.wikipedia.org/wiki/Regular_expression";
    
    private String testSentence = "foo " + testUrl + " bar baz";
    private String testSentenceTwo = "foo bar " + testUrlTwo + " baz";
    
    private String testSentenceWithoutUrl = "foo bar baz";

    private UrlGrabber ug;
    
    @Before
    public void setUpTests() {
	ug = new UrlGrabber();
    }
    
    @Test
    public void testGettingTheUrl() {
	
	String url = ug.grabUrl(testSentence);
	String secondUrl = ug.grabUrl(testSentenceTwo);
	
	assertEquals(testUrl, url);
	assertEquals(testUrlTwo, secondUrl);	
    }
    
    @Test
    public void dealWithNoUrlProvided() {
	String url = ug.grabUrl(testSentenceWithoutUrl);
	
	assertEquals(null, url);		
    }
}
