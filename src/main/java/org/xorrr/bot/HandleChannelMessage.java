package org.xorrr.bot;

public interface HandleChannelMessage {
  void handle(IrcChannel ircChannel, IrcMessage ircMessage);
}
