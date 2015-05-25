package org.xorrr.bot;

import org.pircbotx.Channel;

/**
 * Abstraction for the details how we can let PircBot say something
 */
public class ChannelResponder {

  public void respondWith(Channel channel, String message) {
    channel.send().message(message);
  }
}
