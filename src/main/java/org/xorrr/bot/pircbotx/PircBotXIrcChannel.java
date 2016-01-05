package org.xorrr.bot.pircbotx;

import org.pircbotx.Channel;
import org.xorrr.bot.IrcChannel;
import org.xorrr.bot.IrcMessage;

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
