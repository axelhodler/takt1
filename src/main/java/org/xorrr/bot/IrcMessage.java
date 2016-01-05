package org.xorrr.bot;

public class IrcMessage {
  private String message;

  public IrcMessage(String message) {
    this.message = message;
  }

  public String value() {
    return this.message;
  }
}
