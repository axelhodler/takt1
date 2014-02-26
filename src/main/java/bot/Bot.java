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

    private PircBotX pircBot;

    public Bot() {
        this.pircBot = new PircBotX();
    };

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
        String url = urlGrabber.grabUrl(event.getMessage());
        if (url != null) {
            String title = titleGrabber.grabTitle(url);
            sendTitleToChannelIfFound(event, title);

            Link link = createLinkToSave(event, url,
                    setTitleEmptyStringIfNotFound(title));
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
