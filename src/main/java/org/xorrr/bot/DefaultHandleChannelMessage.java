package org.xorrr.bot;

import com.google.inject.Inject;
import org.xorrr.bot.messageextraction.ChannelResponseFinder;
import org.xorrr.bot.model.Title;

import java.util.Optional;

public class DefaultHandleChannelMessage implements HandleChannelMessage {

  private ChannelResponseFinder channelResponseFinder;

  @Inject
  public DefaultHandleChannelMessage(ChannelResponseFinder channelResponseFinder) {
    this.channelResponseFinder = channelResponseFinder;
  }

  @Override
  public void handle(IrcChannel ircChannel, IrcMessage ircMessage) {
    Optional<Title> title = channelResponseFinder.decideResponseTo(ircMessage.value());

    if (title.isPresent()) {
      ircChannel.send(new IrcMessage(title.get().stringValue()));
    }
  }
}
