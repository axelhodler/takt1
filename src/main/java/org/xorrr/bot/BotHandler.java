package org.xorrr.bot;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import org.xorrr.bot.finder.TitleFinder;
import org.xorrr.bot.finder.UrlFinder;

import com.google.inject.Inject;

public class BotHandler extends ListenerAdapter<PircBotX> {

    private TitleFinder tg;
    private UrlFinder ug;

    @Inject
    public BotHandler(TitleFinder tg, UrlFinder ug) {
        this.tg = tg;
        this.ug = ug;
    }

    @Override
    public void onMessage(MessageEvent<PircBotX> event)
            throws Exception {
        ifUrlWasPostedGetAndPostItsTitle(event);
    }

    private void ifUrlWasPostedGetAndPostItsTitle(MessageEvent<PircBotX> event) {
        String url = ug.findUrl(event.getMessage());

        if (isNotNull(url)) {
            String title = tg.findTitle(url);
            checkIfRespondWithTitle(event, title);
        }
    }

    private void checkIfRespondWithTitle(MessageEvent<PircBotX> event,
            String title) {
        if (isNotNull(title))
            event.getChannel().send().message(title);
    }

    private boolean isNotNull(String title) {
        return title != null;
    }
}
