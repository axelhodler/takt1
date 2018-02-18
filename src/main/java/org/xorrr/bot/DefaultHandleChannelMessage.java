package org.xorrr.bot;

import org.springframework.stereotype.Service;
import org.xorrr.bot.messageextraction.ChannelResponseFinder;

@Service
public class DefaultHandleChannelMessage implements HandleChannelMessage {

  private ChannelResponseFinder channelResponseFinder;

  public DefaultHandleChannelMessage(ChannelResponseFinder channelResponseFinder) {
    this.channelResponseFinder = channelResponseFinder;
  }

  @Override
  public void handle(IrcChannel ircChannel, IrcMessage ircMessage) {
    channelResponseFinder.decideResponseTo(ircMessage.value())
            .ifPresent(t -> ircChannel.send(new IrcMessage(t.stringValue())));
  }
}
