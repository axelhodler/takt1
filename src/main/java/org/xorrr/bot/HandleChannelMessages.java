package org.xorrr.bot;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import com.google.inject.Inject;

public class HandleChannelMessages extends ListenerAdapter<PircBotX> {

  private ResponseFinder messageRelais;
  private ChannelResponder channelResponder;

  @Inject
  public HandleChannelMessages(ResponseFinder messageRelais,
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
