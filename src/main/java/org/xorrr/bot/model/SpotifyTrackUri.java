package org.xorrr.bot.model;

public class SpotifyTrackUri {
  private final String value;

  public SpotifyTrackUri(String value) {
    this.value = value;
  }

  public String trackId() {
    return value.split(":")[2];
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    SpotifyTrackUri that = (SpotifyTrackUri) o;

    return value != null ? value.equals(that.value) : that.value == null;
  }

  @Override
  public int hashCode() {
    return value != null ? value.hashCode() : 0;
  }
}
