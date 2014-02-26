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
    private String message = "msg";

    @Before
    public void setUp() {
        botHandler = new BotHandler(tg, ug);

        when(event.getMessage()).thenReturn(message);
    }

    @Test
    public void onGenericMessage() throws Exception {
        botHandler.onGenericMessage(event);

        verify(event, times(1)).getMessage();
        verify(ug, times(1)).grabUrl(message);
    }
}
