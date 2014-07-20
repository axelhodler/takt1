package org.xorrr.bot.finder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.xorrr.bot.finder.TitleFinder;
import org.xorrr.bot.helpers.IntegrationTest;

@Category(IntegrationTest.class)
public class TestTitleFinder {

    private final String TITLE = "Regular expression - Wikipedia, the "
            + "free encyclopedia";
    private final String URL = "http://en.wikipedia.org/wiki/Regex";
    private String NON_EXISTING_URL = "http://www.test.test";
    private String IMAGE_URL = "http://upload.wikimedia.org/wikipedia/commons"
            + "/thumb/8/8e/Thompson-kleene-star.svg/503px-Thompson-kleene-"
            + "star.svg.png";

    private TitleFinder tg;

    @Before
    public void setUp() {
        tg = new TitleFinder();
    }

    @Test
    public void testGrabbingTheTitle() {
        String grabbedTitle = tg.findTitle(URL);

        assertEquals(TITLE, grabbedTitle);
    }

    @Test
    public void testGrabbingTheTitleWithAnImage() {
        String grabbedTitle = tg.findTitle(IMAGE_URL);

        assertNull(grabbedTitle);
    }

    @Test
    public void testGrabbingTheTitleOfANonExistingUrl() {
        String grabbedTitle = tg.findTitle(NON_EXISTING_URL);

        assertNull(grabbedTitle);
    }
}