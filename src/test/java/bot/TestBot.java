package bot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.pircbotx.PircBotX;

@RunWith(MockitoJUnitRunner.class)
public class TestBot {

    @Mock
    PircBotX pircbot;

    @Test
    public void testBot() {
        Bot bot = new Bot(pircbot);
    }

}
