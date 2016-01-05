package org.xorrr.bot;

import org.pircbotx.Channel;

public class PircBotXIrcChannel implements IrcChannel {
  private final Channel channel;

  public PircBotXIrcChannel(Channel channel) {
    this.channel = channel;
  }

  @Override
  public void send(IrcMessage ircMessage) {
    channel.send().message(ircMessage.value());
  }
}
