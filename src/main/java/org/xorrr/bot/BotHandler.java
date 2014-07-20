package org.xorrr.bot;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import org.xorrr.bot.finder.SpotifyTrackTitleFinder;
import org.xorrr.bot.finder.SpotifyUriFinder;
import org.xorrr.bot.finder.TitleFinder;
import org.xorrr.bot.finder.UrlFinder;

import com.google.inject.Inject;

public class BotHandler extends ListenerAdapter<PircBotX> {

    private TitleFinder tg;
    private UrlFinder ug;
    private SpotifyTrackTitleFinder trackTitleFinder;
    private SpotifyUriFinder trackUriFinder;

    @Inject
    public BotHandler(TitleFinder tg, UrlFinder ug,
            SpotifyTrackTitleFinder ttf, SpotifyUriFinder uf) {
        this.tg = tg;
        this.ug = ug;
        this.trackTitleFinder = ttf;
        this.trackUriFinder = uf;
    }

    @Override
    public void onMessage(MessageEvent<PircBotX> event)
            throws Exception {
        checkForUrls(event);
        checkForTrackUris(event);
    }

    private void checkForTrackUris(MessageEvent<PircBotX> event) {
        String trackUri = trackUriFinder.findUri(getMessage(event));
        respondWithTitle(event, trackUri);
    }

    private String getMessage(MessageEvent<PircBotX> event) {
        return event.getMessage();
    }

    private void respondWithTitle(MessageEvent<PircBotX> event, String trackUri) {
        if (isNotNull(trackUri)) {
            String title = trackTitleFinder.findTitle(trackUri);
            checkIfToRespondWithTitle(event, title);
        }
    }

    private void checkForUrls(MessageEvent<PircBotX> event) {
        String url = ug.findUrl(getMessage(event));

        if (isNotNull(url)) {
            String title = tg.findTitle(url);
            checkIfToRespondWithTitle(event, title);
        }
    }

    private void checkIfToRespondWithTitle(MessageEvent<PircBotX> event,
            String title) {
        if (isNotNull(title))
            event.getChannel().send().message(title);
    }

    private boolean isNotNull(String title) {
        return title != null;
    }
}
