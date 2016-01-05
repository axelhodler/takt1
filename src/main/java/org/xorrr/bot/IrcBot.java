package org.xorrr.bot;

import org.xorrr.bot.pircbotx.PircBotXMessageListener;

public interface IrcBot {
  void start(IrcBotConfiguration config, PircBotXMessageListener handleChannelMessages);
}
