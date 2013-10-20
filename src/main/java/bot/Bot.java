package bot;

import java.io.IOException;

import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.exception.NickAlreadyInUseException;
import org.pircbotx.hooks.Listener;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

@SuppressWarnings("rawtypes")
public class Bot extends ListenerAdapter implements Listener {

    private static ConfigHelper configHelper;
    private TitleGrabber titleGrabber = new TitleGrabber();
    private UrlGrabber urlGrabber = new UrlGrabber();
    private static Bot bot = null;

    private PircBotX pircBot;

    private Bot() {
        this.pircBot = new PircBotX();
    };

    public static Bot getInstance() {
        if (bot == null) {
            bot = new Bot();
        }
        return bot;
    }

    public void launchBot() throws NickAlreadyInUseException, IOException,
            IrcException {
        configHelper = new ConfigHelper();

        pircBot.setName(configHelper.getBotName());

        pircBot.setVerbose(true);

        pircBot.getListenerManager().addListener(new Bot());

        pircBot.connect(configHelper.getServer());

        pircBot.joinChannel(configHelper.getChannel());
    }

    // optional
    public void identifyWithServer(String password) throws InterruptedException {
        pircBot.identify(configHelper.getIdentifyPassword());
        // sleep 5secs before joining the channel
        Thread.sleep(5000);
    }

    @Override
    public void onMessage(MessageEvent event) throws Exception {
        String message = event.getMessage();
        String url = urlGrabber.grabUrl(message);

        if (url != null) {
            String title = titleGrabber.grabTitle(url);
            if (title != null) {
                event.getBot().sendMessage(event.getChannel(), title);
            }
        }
    }
}
