package org.xorrr.bot;

public class IrcMessage {
  private String message;

  public IrcMessage(String message) {
    this.message = message;
  }

  public String value() {
    return this.message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    IrcMessage that = (IrcMessage) o;

    return message != null ? message.equals(that.message) : that.message == null;
  }

  @Override
  public int hashCode() {
    return message != null ? message.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "IrcMessage{" +
            "message='" + message + '\'' +
            '}';
  }
}
