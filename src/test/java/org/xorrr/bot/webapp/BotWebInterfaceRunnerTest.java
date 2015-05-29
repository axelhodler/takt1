package org.xorrr.bot.webapp;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BotWebInterfaceRunnerTest {

  @Mock
  BotWebInterface app;

  @Test
  public void test() {
    BotWebInterfaceRunner thread = new BotWebInterfaceRunner(app);

    thread.run();

    verify(app).launchServer();
  }

}
