package bot;

import java.io.IOException;

import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.exception.NickAlreadyInUseException;
import org.pircbotx.hooks.Listener;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import bot.config.EnvironmentVars;
import bot.restcalls.Link;
import bot.restcalls.RestAccessor;

@SuppressWarnings("rawtypes")
public class Bot extends ListenerAdapter implements Listener {

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

    public void setPropertiesAndJoin() throws NickAlreadyInUseException,
            IOException, IrcException, InterruptedException {
        pircBot.setName(System.getenv(EnvironmentVars.NAME));

        pircBot.setVerbose(true);
        pircBot.setLogin(System.getenv(EnvironmentVars.IDENT));
        pircBot.getListenerManager().addListener(this);

        pircBot.connect(System.getenv(EnvironmentVars.SERVER));

        pircBot.identify(System.getenv(EnvironmentVars.PASS));
        Thread.sleep(5000);

        pircBot.joinChannel(System.getenv(EnvironmentVars.CHANNEL));
    }

    @Override
    public void onMessage(MessageEvent event) throws Exception {
        String urlInMessage = urlGrabber.grabUrl(event.getMessage());
        if (urlInMessage != null) {
            String title = titleGrabber.grabTitle(urlInMessage);
            sendTitleToChannelIfFound(event, title);
            title = setTitleEmptyStringIfNotFound(title);

            Link link = createLinkToSave(event, urlInMessage, title);
            RestAccessor.getInstance().addLink(link);
        }
    }

    private String setTitleEmptyStringIfNotFound(String title) {
        if (title == null) {
            title = "";
        }
        return title;
    }

    private void sendTitleToChannelIfFound(MessageEvent event, String title) {
        if (isTitleExtraced(title)) {
            sendTitleToChannel(event, title);
        }
    }

    private Link createLinkToSave(MessageEvent event, String urlInMessage,
            String title) {
        Link link = new Link().setTitle(title).setUrl(urlInMessage)
                .setUser(event.getUser().getNick());
        return link;
    }

    private void sendTitleToChannel(MessageEvent event, String title) {
        event.getBot().sendMessage(event.getChannel(), title);
    }

    private boolean isTitleExtraced(String title) {
        return title != null;
    }
}
