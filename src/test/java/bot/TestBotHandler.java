package bot;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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

    @Before
    public void setUp() {
        botHandler = new BotHandler(tg, ug);
    }

    @Test
    public void onGenericMessage() throws Exception {
        botHandler.onGenericMessage(event);

        verify(event, times(1)).getMessage();
    }
}
