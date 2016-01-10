package org.xorrr.bot;

import com.google.inject.Inject;
import org.xorrr.bot.messageextraction.ChannelResponseFinder;

import java.util.Optional;

public class DefaultHandleChannelMessage implements HandleChannelMessage {

  private ChannelResponseFinder channelResponseFinder;

  @Inject
  public DefaultHandleChannelMessage(ChannelResponseFinder channelResponseFinder) {
    this.channelResponseFinder = channelResponseFinder;
  }

  @Override
  public void handle(IrcChannel ircChannel, IrcMessage ircMessage) {
    Optional<String> responseMessage = channelResponseFinder.decideResponseTo(ircMessage.value());

    if (responseMessage.isPresent()) {
      ircChannel.send(new IrcMessage(responseMessage.get()));
    }
  }
}
