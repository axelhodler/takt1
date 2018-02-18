package org.xorrr.bot.boundaries.impl;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.xorrr.bot.config.IrcBotConfiguration;

import java.io.IOException;

@Service
public class PircBot implements CommandLineRunner {

  private final IrcBotConfiguration config;
  private final PircBotXMessageListener handleChannelMessages;

  public PircBot(IrcBotConfiguration config, PircBotXMessageListener handleChannelMessages) {
    this.config = config;
    this.handleChannelMessages = handleChannelMessages;
  }

  public void start() {
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

  @Override
  public void run(String... strings) throws Exception {
    this.start();
  }
}
