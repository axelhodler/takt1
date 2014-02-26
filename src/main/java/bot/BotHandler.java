package bot;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.types.GenericMessageEvent;

import com.google.inject.Inject;

public class BotHandler extends ListenerAdapter {

    private TitleGrabber tg;
    private UrlGrabber ug;

    @Inject
    public BotHandler(TitleGrabber tg, UrlGrabber ug) {
        this.tg = tg;
        this.ug = ug;
    }

    @Override
    public void onGenericMessage(final GenericMessageEvent event)
            throws Exception {
        String url = ug.grabUrl(event.getMessage());
        if (url != null) {
            String title = tg.grabTitle(url);
            event.respond(title);
        }
    }
}
