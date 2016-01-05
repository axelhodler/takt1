package org.xorrr.bot;

public interface IrcBot {
  void start(IrcBotConfiguration config, HandleChannelMessages handleChannelMessages);
}
