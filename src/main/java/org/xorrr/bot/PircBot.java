package org.xorrr.bot;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

import java.io.IOException;

public class PircBot implements IrcBot {

  @Override
  public void start(IrcBotConfiguration config, PircBotXMessageListener handleChannelMessages) {
    Configuration<PircBotX> pircBotConfig = new Configuration.Builder<>()
            .setName(config.getBotName())
            .setServerHostname(config.getServerAddress())
            .addAutoJoinChannel(config.getChannelName()).addListener(handleChannelMessages)
            .setLogin(config.getIdent())
            .setNickservPassword(config.getPassword()).buildConfiguration();
    PircBotX pircbot = new PircBotX(pircBotConfig);
    try {
      pircbot.startBot();
    } catch (IOException | IrcException e) {
      e.printStackTrace();
    }
  }
}
