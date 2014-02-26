package bot;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.pircbotx.PircBotX;

@RunWith(MockitoJUnitRunner.class)
public class TestBot {

    @Mock
    PircBotX pircbot;
    
    private Bot bot;

    @Before
    public void setUp() {
        bot = new Bot(pircbot);
    }
}
