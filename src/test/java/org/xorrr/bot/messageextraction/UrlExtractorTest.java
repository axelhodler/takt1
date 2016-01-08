package org.xorrr.bot.messageextraction;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class UrlExtractorTest {
  private final String TEST_URL = "http://en.wikipedia.org/wiki/Regex";
  private final String TEST_URL_TWO = "http://en.wikipedia.org/wiki/"
      + "Regular_expression";
  private final String TEST_SENTENCE = "foo " + TEST_URL + " bar baz";
  private final String TEST_SENTENCE_TWO = "foo bar " + TEST_URL_TWO + " baz";

  private UrlExtractor urlFinder;

  @Before
  public void setUpTests() {
    urlFinder = new UrlExtractor();
  }

  @Test
  public void extractsUrlFromMessage() {
    String url = urlFinder.extractUrlIn(TEST_SENTENCE);
    String secondUrl = urlFinder.extractUrlIn(TEST_SENTENCE_TWO);

    assertThat(url, is(TEST_URL));
    assertThat(secondUrl, is(TEST_URL_TWO));
  }

  @Test
  public void doesNotExtractUrlIfNoneProvided() {
    String url = urlFinder.extractUrlIn("im a message without url");

    assertNull(url);
  }

  @Test
  public void canCheckIfUrlInMessage() {
    assertThat(urlFinder
        .urlExtractableIn("Hi, checkout that video http://www.youtube.com"), is(true));
  }

  @Test
  public void tellsIfNoUrlExtractableInMessage() {
    assertThat(urlFinder.urlExtractableIn("message"), is(false));
  }
}
