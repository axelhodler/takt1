package org.xorrr.bot.finder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.xorrr.bot.finder.UrlFinder;
import org.xorrr.bot.helpers.IntegrationTest;

@Category(IntegrationTest.class)
public class UrlFinderTest {
    private final String TEST_URL = "http://en.wikipedia.org/wiki/Regex";
    private final String TEST_URL_TWO = "http://en.wikipedia.org/wiki/"
            + "Regular_expression";
    private final String TEST_SENTENCE = "foo " + TEST_URL + " bar baz";
    private final String TEST_SENTENCE_TWO = "foo bar " + TEST_URL_TWO + " baz";
    private final String NOURL = "foo bar baz";

    private UrlFinder ug;

    @Before
    public void setUpTests() {
        ug = new UrlFinder();
    }

    @Test
    public void testGettingTheUrl() {
        String url = ug.extractUrlIn(TEST_SENTENCE);
        String secondUrl = ug.extractUrlIn(TEST_SENTENCE_TWO);

        assertEquals(TEST_URL, url);
        assertEquals(TEST_URL_TWO, secondUrl);
    }

    @Test
    public void dealWithNoUrlProvided() {
        String url = ug.extractUrlIn(NOURL);

        assertNull(url);
    }
}
