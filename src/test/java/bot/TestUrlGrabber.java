package bot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class TestUrlGrabber {
    private final String TEST_URL = "http://en.wikipedia.org/wiki/Regex";
    private final String TEST_URL_TWO = "http://en.wikipedia.org/wiki/"
            + "Regular_expression";
    private final String TEST_SENTENCE = "foo " + TEST_URL + " bar baz";
    private final String TEST_SENTENCE_TWO = "foo bar " + TEST_URL_TWO + " baz";
    private final String NOURL = "foo bar baz";

    private UrlGrabber ug;

    @Before
    public void setUpTests() {
        ug = new UrlGrabber();
    }

    @Test
    public void testGettingTheUrl() {
        String url = ug.grabUrl(TEST_SENTENCE);
        String secondUrl = ug.grabUrl(TEST_SENTENCE_TWO);

        assertEquals(TEST_URL, url);
        assertEquals(TEST_URL_TWO, secondUrl);
    }

    @Test
    public void dealWithNoUrlProvided() {
        String url = ug.grabUrl(NOURL);

        assertNull(url);
    }
}
