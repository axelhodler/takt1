package org.xorrr.bot.config;

public class IrcBotConfiguration {

  private String botName;
  private String serverAddress;
  private String channelName;
  private String ident;
  private String password;

  public void initFromEnvironmentVars() {
    this.botName = readEnvVar("NAME");
    this.serverAddress = readEnvVar("SERVER");
    this.channelName = readEnvVar("CHANNEL");
    this.ident = readEnvVar("IDENT");
    this.password = readEnvVar("PASSWORD");
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
