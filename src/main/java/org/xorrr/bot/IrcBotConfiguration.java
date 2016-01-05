package org.xorrr.bot;

import org.xorrr.bot.config.UsedEnvironmentVars;

public class IrcBotConfiguration {

  private String botName;
  private String serverAddress;
  private String channelName;
  private String ident;
  private String password;

  public void initFromEnvironmentVars() {
    this.botName = readEnvVar(UsedEnvironmentVars.NAME);
    this.serverAddress = readEnvVar(UsedEnvironmentVars.SERVER);
    this.channelName = readEnvVar(UsedEnvironmentVars.CHANNEL);
    this.ident = readEnvVar(UsedEnvironmentVars.IDENT);
    this.password = readEnvVar(UsedEnvironmentVars.PASSWORD);
  }

  public String getBotName() {
    return botName;
  }

  public String getServerAddress() {
    return serverAddress;
  }

  public String getChannelName() {
    return channelName;
  }

  public String getIdent() {
    return ident;
  }

  public String getPassword() {
    return password;
  }

  private String readEnvVar(String name) {
    return System.getenv(name);
  }
}
