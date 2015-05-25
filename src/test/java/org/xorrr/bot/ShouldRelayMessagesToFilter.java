package org.xorrr.bot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.mockito.runners.MockitoJUnitRunner;
import org.pircbotx.PircBotX;
import org.pircbotx.hooks.events.MessageEvent;

@RunWith(MockitoJUnitRunner.class)
public class ShouldRelayMessagesToFilter {

  @Mock
  MessageEvent<PircBotX> messageEvent;
  @Mock
  BotResponse messageFilter;

  @Test
  public void filterChoosesWhatToDoWithTheMessage() throws Exception {
    String messageByUser = "message sent to the channel by a user";
    given(messageEvent.getMessage()).willReturn(messageByUser);
    HandleChannelMessages onMessageHandler = new HandleChannelMessages(messageFilter);

    onMessageHandler.onMessageReplacement(messageEvent);

    verify(messageFilter).decideResponseTo(messageByUser);
  }
}
