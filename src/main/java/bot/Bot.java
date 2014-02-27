package bot;

import java.io.IOException;

import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

import com.google.inject.Inject;

public class Bot {
    private PircBotX pircBotX;

    @Inject
    public Bot(PircBotX pircbotx) {
        this.pircBotX = pircbotx;
    };

    public void start() {
        try {
            pircBotX.startBot();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IrcException e) {
            e.printStackTrace();
        }
    }
}
