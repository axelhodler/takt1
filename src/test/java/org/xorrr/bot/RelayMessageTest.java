package org.xorrr.bot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.xorrr.bot.finder.UrlFinder;

@RunWith(MockitoJUnitRunner.class)
public class RelayMessageTest {

  @Mock
  UrlFinder urlFinder;

  @Test
  public void checksForHttpUrls() {
    RelayMessage messageRelais = new RelayMessage(urlFinder);

    messageRelais.relay("message");

    urlFinder.extractUrlIn("message");
  }
}
