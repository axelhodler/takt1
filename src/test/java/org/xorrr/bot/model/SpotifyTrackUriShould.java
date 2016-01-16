package org.xorrr.bot.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SpotifyTrackUriShould {

  SpotifyTrackUri trackUri;

  @Test
  public void provideTrackId() {
    trackUri = new SpotifyTrackUri("spotify:track:4MYBNI0m7Ot8Llm2OsjzXQ");

    assertThat(trackUri.trackId(), is("4MYBNI0m7Ot8Llm2OsjzXQ"));
  }

  @Test
  public void provideTrackIdInTrackUri() {
    trackUri = new SpotifyTrackUri("spotify:track:foobarbaz1234");

    assertThat(trackUri.trackId(), is("foobarbaz1234"));
  }
}