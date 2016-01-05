package org.xorrr.bot;

public interface IrcBot {
  void start(IrcBotConfiguration config, PircBotXMessageListener handleChannelMessages);
}
