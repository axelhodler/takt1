package org.xorrr.bot;

public class IrcBotConfiguration {

  private String botName;
  private String serverAddress;
  private String channelName;
  private String ident;
  private String password;

  public void initFromEnvironmentVars() {

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
}
