package org.xorrr.bot.messageextraction;

import org.junit.Before;
import org.junit.Test;
import org.xorrr.bot.boundaries.impl.JsoupTitleFetcher;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNull;

public class HtmlTitleFetcherIT {

  private final String TITLE = "Regular expression - Wikipedia, the "
      + "free encyclopedia";
  private final String URL = "http://en.wikipedia.org/wiki/Regex";
  private String NON_EXISTING_URL = "http://www.test.test";
  private String IMAGE_URL = "http://upload.wikimedia.org/wikipedia/commons"
      + "/thumb/8/8e/Thompson-kleene-star.svg/503px-Thompson-kleene-"
      + "star.svg.png";

  private JsoupTitleFetcher finder;

  @Before
  public void setUp() {
    finder = new JsoupTitleFetcher();
  }

  @Test
  public void can_find_html_title() {
    String grabbedTitle = finder.fetchTitleFrom(URL);

    assertThat(TITLE, equalTo(grabbedTitle));
  }

  @Test
  public void image_url_does_not_have_html_title() {
    String title = finder.fetchTitleFrom(IMAGE_URL);

    assertNull(title);
  }

  @Test
  public void testGrabbingTheTitleOfANonExistingUrl() {
    String title = finder.fetchTitleFrom(NON_EXISTING_URL);

    assertNull(title);
  }
}
