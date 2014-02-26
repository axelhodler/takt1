package bot;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.pircbotx.hooks.types.GenericMessageEvent;

@RunWith(MockitoJUnitRunner.class)
public class TestBotHandler {

    @Mock
    GenericMessageEvent event;
    @Mock
    TitleGrabber tg;
    @Mock
    UrlGrabber ug;

    private BotHandler botHandler;
    private final String MESSAGE = "msg";
    private final String URL = "url";
    private final String TITLE = "title";

    @Before
    public void setUp() {
        botHandler = new BotHandler(tg, ug);

        when(event.getMessage()).thenReturn(MESSAGE);
    }

    @Test
    public void onGenericMessage() throws Exception {
        botHandler.onGenericMessage(event);

        verify(event, times(1)).getMessage();
        verify(ug, times(1)).grabUrl(MESSAGE);
    }

    @Test
    public void urlIsNotGrabbed() throws Exception {
        when(ug.grabUrl(MESSAGE)).thenReturn(null);

        botHandler.onGenericMessage(event);

        verify(tg, times(0)).grabTitle(URL);
    }

    @Test
    public void urlIsGrabbed() throws Exception {
        when(ug.grabUrl(MESSAGE)).thenReturn(URL);
        when(tg.grabTitle(URL)).thenReturn(TITLE);

        botHandler.onGenericMessage(event);

        verify(tg, times(1)).grabTitle(URL);
        verify(event, times(1)).respond(TITLE);
    }

    @Test
    public void titleNotFound() throws Exception {
        when(ug.grabUrl(MESSAGE)).thenReturn(URL);
        when(tg.grabTitle(URL)).thenReturn(null);

        botHandler.onGenericMessage(event);

        verify(tg, times(1)).grabTitle(URL);
        verify(event, times(0)).respond(null);
    }
}
