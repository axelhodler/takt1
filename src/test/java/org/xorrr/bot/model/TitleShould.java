package org.xorrr.bot.model;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TitleShould {

  @Test
  public void handleEquals() {
    assertThat(new Title("title"), is(new Title("title")));
  }

}