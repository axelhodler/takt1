package org.xorrr.bot;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.pircbotx.Channel;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;
import org.xorrr.bot.messageextraction.ChannelResponseFinder;

@RunWith(MockitoJUnitRunner.class)
public class ShouldRelayMessagesToFilter {

  @Mock
  MessageEvent<PircBotX> messageEvent;
  @Mock
  Channel channelWhereMessageWasSentFrom;
  @Mock
  ChannelResponseFinder messageFilter;
  @Mock
  ChannelResponder channelResponder;

  private PircBotXMessageListener onMessageHandler;

  @Before
  public void setUp() {
    onMessageHandler = new PircBotXMessageListener(messageFilter,
        channelResponder);
  }

  @Test
  public void postResponseToChannel() throws Exception {
    String messageByUser = prepareMessageEventFromChannel();
    given(messageFilter.decideResponseTo(messageByUser)).willReturn("response");

    onMessageHandler.onMessage(messageEvent);

    verify(channelResponder).respondWith(channelWhereMessageWasSentFrom,
        "response");
  }

  @Test
  public void dontRespondToChannelIfNoResponseAvailable() throws Exception {
    String messageByUser = prepareMessageEventFromChannel();
    given(messageFilter.decideResponseTo(messageByUser)).willReturn("");

    onMessageHandler.onMessage(messageEvent);

    verify(channelResponder, never()).respondWith(isA(Channel.class),
        anyString());
  }

  private String prepareMessageEventFromChannel() {
    String messageByUser = "message sent to the channel by a user";
    given(messageEvent.getMessage()).willReturn(messageByUser);
    given(messageEvent.getChannel()).willReturn(channelWhereMessageWasSentFrom);
    return messageByUser;
  }
}
