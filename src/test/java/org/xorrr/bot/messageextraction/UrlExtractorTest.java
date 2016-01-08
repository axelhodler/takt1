package org.xorrr.bot.messageextraction;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UrlExtractorTest {
  private final String TEST_URL = "http://en.wikipedia.org/wiki/Regex";
  private final String TEST_URL_TWO = "http://en.wikipedia.org/wiki/"
      + "Regular_expression";
  private final String TEST_SENTENCE = "foo " + TEST_URL + " bar baz";
  private final String TEST_SENTENCE_TWO = "foo bar " + TEST_URL_TWO + " baz";
  private final String NOURL = "foo bar baz";

  private UrlExtractor urlFinder;

  @Before
  public void setUpTests() {
    urlFinder = new UrlExtractor();
  }

  @Test
  public void testGettingTheUrl() {
    String url = urlFinder.extractUrlIn(TEST_SENTENCE);
    String secondUrl = urlFinder.extractUrlIn(TEST_SENTENCE_TWO);

    assertEquals(TEST_URL, url);
    assertEquals(TEST_URL_TWO, secondUrl);
  }

  @Test
  public void dealWithNoUrlProvided() {
    String url = urlFinder.extractUrlIn(NOURL);

    assertNull(url);
  }

  @Test
  public void canCheckIfUrlInMessage() {
    assertTrue(urlFinder
        .urlExtractableIn("Hi, checkout that video http://www.youtube.com"));
  }

  @Test
  public void tellsIfNoUrlExtractableInMessage() {
    assertFalse(urlFinder.urlExtractableIn("message"));
  }
}
