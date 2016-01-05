package org.xorrr.bot;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import org.xorrr.bot.messageextraction.ChannelResponseFinder;

import com.google.inject.Inject;

public class PircBotXMessageListener extends ListenerAdapter<PircBotX> {

  private ChannelResponseFinder messageRelais;
  private ChannelResponder channelResponder;

  @Inject
  public PircBotXMessageListener(ChannelResponseFinder messageRelais,
                                 ChannelResponder channelResponder) {
    this.messageRelais = messageRelais;
    this.channelResponder = channelResponder;
  }

  @Override
  public void onMessage(MessageEvent<PircBotX> messageEvent) throws Exception {
    String responseMessage = messageRelais.decideResponseTo(messageEvent
        .getMessage());

    if (!responseMessage.isEmpty()) {
      channelResponder.respondWith(messageEvent.getChannel(), responseMessage);
    }
  }
}
