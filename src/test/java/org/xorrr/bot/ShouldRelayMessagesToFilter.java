package org.xorrr.bot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;

import org.mockito.runners.MockitoJUnitRunner;
import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;

@RunWith(MockitoJUnitRunner.class)
public class ShouldRelayMessagesToFilter {

  @Mock
  MessageEvent<PircBotX> messageEvent;
  @Mock
  Channel channelWhereMessageWasSentFrom;
  @Mock
  BotResponse messageFilter;
  @Mock
  ChannelResponder channelResponder;

  private HandleChannelMessages onMessageHandler;

  @Before
  public void setUp() {
    onMessageHandler = new HandleChannelMessages(messageFilter, channelResponder);
  }

  @Test
  public void postResponseToChannel() {
    String messageByUser = prepareMessageEventFromChannel();
    given(messageFilter.decideResponseTo(messageByUser)).willReturn("response");

    onMessageHandler.onMessageReplacement(messageEvent);

    verify(channelResponder).respondWith(channelWhereMessageWasSentFrom, "response");
  }

  @Test
  public void dontRespondToChannelIfNoResponseAvailable() {
    String messageByUser = prepareMessageEventFromChannel();
    given(messageFilter.decideResponseTo(messageByUser)).willReturn("");

    onMessageHandler.onMessageReplacement(messageEvent);

    verify(channelResponder, never()).respondWith(isA(Channel.class), anyString());
  }

  private String prepareMessageEventFromChannel() {
    String messageByUser = "message sent to the channel by a user";
    given(messageEvent.getMessage()).willReturn(messageByUser);
    given(messageEvent.getChannel()).willReturn(channelWhereMessageWasSentFrom);
    return messageByUser;
  }
}
