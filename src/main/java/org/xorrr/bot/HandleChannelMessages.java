package org.xorrr.bot;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import org.xorrr.bot.finder.SpotifyTrackTitleFinder;
import org.xorrr.bot.finder.SpotifyUriFinder;
import org.xorrr.bot.finder.HtmlTitleFetcher;
import org.xorrr.bot.finder.UrlFinder;

import com.google.inject.Inject;

public class HandleChannelMessages extends ListenerAdapter<PircBotX> {

    private HtmlTitleFetcher titleFinder;
    private UrlFinder urlFinder;
    private SpotifyTrackTitleFinder trackTitleFinder;
    private SpotifyUriFinder trackUriFinder;
    private ResponseFinder messageRelais;
    private ChannelResponder channelResponder;

    @Inject
    public HandleChannelMessages(HtmlTitleFetcher tg, UrlFinder ug,
            SpotifyTrackTitleFinder ttf, SpotifyUriFinder uf,
            ChannelResponder channelResponder) {
        this.titleFinder = tg;
        this.urlFinder = ug;
        this.trackTitleFinder = ttf;
        this.trackUriFinder = uf;
        this.channelResponder = channelResponder;
    }

    public HandleChannelMessages(ResponseFinder messageRelais, ChannelResponder channelResponder) {
      this.messageRelais = messageRelais;
      this.channelResponder = channelResponder;
    }

    @Override
    public void onMessage(MessageEvent<PircBotX> event)
            throws Exception {
        checkForUrls(event);
        checkForTrackUris(event);
    }

    public void onMessageReplacement(MessageEvent<PircBotX> messageEvent) {
      String responseMessage = messageRelais.decideResponseTo(messageEvent.getMessage());

      if (!responseMessage.isEmpty()) {
        channelResponder.respondWith(messageEvent.getChannel(), responseMessage);
      }
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
            String title = trackTitleFinder.fetchTitleFrom(trackUri);
            checkIfToRespondWithTitle(event, title);
        }
    }

    private void checkForUrls(MessageEvent<PircBotX> event) {
        String url = urlFinder.extractUrlIn(getMessage(event));

        if (isNotNull(url)) {
            String title = titleFinder.fetchTitleFrom(url);
            checkIfToRespondWithTitle(event, title);
        }
    }

    private void checkIfToRespondWithTitle(MessageEvent<PircBotX> event,
            String title) {
        if (isNotNull(title))
            channelResponder.respondWith(event.getChannel(), title);
    }

    private boolean isNotNull(String title) {
        return title != null;
    }
}
