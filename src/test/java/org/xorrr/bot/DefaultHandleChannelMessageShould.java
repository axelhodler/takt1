package org.xorrr.bot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xorrr.bot.messageextraction.ChannelResponseFinder;
import org.xorrr.bot.model.Title;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DefaultHandleChannelMessageShould {

  @Mock
  ChannelResponseFinder responseFinder;
  @Mock
  IrcChannel ircChannel;

  DefaultHandleChannelMessage handleChannelMessage;

  @Before
  public void initialize() {
    handleChannelMessage = new DefaultHandleChannelMessage(responseFinder);
  }

  @Test
  public void postResponseToChannel() {
    given(responseFinder.decideResponseTo("hallo")).willReturn(Optional.of(new Title("hi")));

    handleChannelMessage.handle(ircChannel, new IrcMessage("hallo"));

    verify(ircChannel).send(new IrcMessage("hi"));
  }

  @Test
  public void dontRespondToChannelIfNoResponseAvailable() {
    given(responseFinder.decideResponseTo("hallo")).willReturn(Optional.empty());

    handleChannelMessage.handle(ircChannel, new IrcMessage("hallo"));

    verify(ircChannel, never()).send(new IrcMessage(""));
  }
}
