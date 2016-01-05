package org.xorrr.bot;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import org.xorrr.bot.messageextraction.ChannelResponseFinder;

import com.google.inject.Inject;

public class PircBotXMessageListener extends ListenerAdapter<PircBotX> {

  private HandleChannelMessage handleChannelMessage;

  public PircBotXMessageListener(HandleChannelMessage handleChannelMessage) {
    this.handleChannelMessage = handleChannelMessage;
  }

  @Override
  public void onMessage(MessageEvent<PircBotX> messageEvent) throws Exception {
    handleChannelMessage.handle(new PircBotXIrcChannel(messageEvent.getChannel()),
            new IrcMessage(messageEvent.getMessage()));
  }
}
