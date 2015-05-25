package org.xorrr.bot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;

import org.mockito.runners.MockitoJUnitRunner;
import org.xorrr.bot.finder.TitleFinder;
import org.xorrr.bot.finder.UrlFinder;

@RunWith(MockitoJUnitRunner.class)
public class RelayMessageTest {

  @Mock
  UrlFinder urlFinder;
  @Mock
  TitleFinder titleFinder;

  private RelayMessage messageRelais;

  @Before
  public void initialize() {
    messageRelais = new RelayMessage(urlFinder, titleFinder);
  }

  @Test
  public void checksForHttpUrls() {
    messageRelais.relay("message");

    verify(urlFinder).extractUrlIn("message");
  }

  @Test
  public void fetchTitleOfExtractedUrl() {
    String messageContainingAUrl = "Hi, check this out www.foobar.org";
    given(urlFinder.extractUrlIn(messageContainingAUrl)).willReturn("www.foobar.org");

    messageRelais.relay(messageContainingAUrl);

    verify(titleFinder).findTitle("www.foobar.org");
  }
}
