package bot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class TestTitleGrabber {

    private final String TITLE = "Regular expression - Wikipedia, the "
            + "free encyclopedia";
    private final String URL = "http://en.wikipedia.org/wiki/Regex";
    private String NON_EXISTING_URL = "http://www.test.test";
    private String IMAGE_URL = "http://upload.wikimedia.org/wikipedia/commons"
            + "/thumb/8/8e/Thompson-kleene-star.svg/503px-Thompson-kleene-"
            + "star.svg.png";

    private TitleGrabber tg;

    @Before
    public void setUp() {
        tg = new TitleGrabber();
    }

    @Test
    public void testGrabbingTheTitle() {
        String grabbedTitle = tg.grabTitle(URL);

        assertEquals(TITLE, grabbedTitle);
    }

    @Test
    public void testGrabbingTheTitleWithAnImage() {
        String grabbedTitle = tg.grabTitle(IMAGE_URL);

        assertNull(grabbedTitle);
    }

    @Test
    public void testGrabbingTheTitleOfANonExistingUrl() {
        String grabbedTitle = tg.grabTitle(NON_EXISTING_URL);

        assertNull(grabbedTitle);
    }
}
