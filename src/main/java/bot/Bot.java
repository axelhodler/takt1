package bot;

import java.io.IOException;

import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.pircbotx.hooks.Listener;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import bot.restcalls.Link;
import bot.restcalls.RestAccessor;

import com.google.inject.Inject;

@SuppressWarnings("rawtypes")
public class Bot extends ListenerAdapter implements Listener {

    private TitleGrabber titleGrabber = new TitleGrabber();
    private UrlGrabber urlGrabber = new UrlGrabber();

    private PircBotX pircBotX;

    @Inject
    public Bot(PircBotX pircbotx) {
        this.pircBotX = pircbotx;
    };

    public void start() throws IOException, IrcException {
        pircBotX.startBot();
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
        event.getChannel().send().action(title);
    }

    private boolean isTitleExtraced(String title) {
        return title != null;
    }
}
