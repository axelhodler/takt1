package org.xorrr.bot.boundaries;

import org.xorrr.bot.config.IrcBotConfiguration;
import org.xorrr.bot.boundaries.impl.PircBotXMessageListener;

public interface IrcBot {
  void start(IrcBotConfiguration config, PircBotXMessageListener handleChannelMessages);
}
