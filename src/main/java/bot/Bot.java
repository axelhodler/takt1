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

    private ConfigHelper configHelper;
    private TitleGrabber titleGrabber = new TitleGrabber();
    private UrlGrabber urlGrabber = new UrlGrabber();
    private static Bot bot = null;

    private PircBotX pircBot;

    private Bot() {
        this.pircBot = new PircBotX();
        this.configHelper = new ConfigHelper();
    };

    public static Bot getInstance() {
        if (bot == null) {
            bot = new Bot();
        }
        return bot;
    }

    public void setPropertiesAndJoin() throws NickAlreadyInUseException,
            IOException, IrcException {
        pircBot.setName(configHelper.getBotName());

        pircBot.setVerbose(true);

        pircBot.getListenerManager().addListener(this);

        pircBot.connect(configHelper.getServer());
        pircBot.joinChannel(configHelper.getChannel());
    }

    public void identifyWithServer(String password) throws InterruptedException {
        pircBot.identify(configHelper.getIdentifyPassword());
        Thread.sleep(5000);
    }

    @Override
    public void onMessage(MessageEvent event) throws Exception {
        String urlInMessage = urlGrabber.grabUrl(event.getMessage());

        if (doesMessageContainUrl(urlInMessage)) {
            sendTitleToChannel(event, titleGrabber.grabTitle(urlInMessage));
        }
    }

    private void sendTitleToChannel(MessageEvent event, String title) {
        if (isTitleExtraced(title)) {
            event.getBot().sendMessage(event.getChannel(), title);
        }
    }

    private boolean isTitleExtraced(String title) {
        return title != null;
    }

    private boolean doesMessageContainUrl(String url) {
        return isTitleExtraced(url);
    }
}
