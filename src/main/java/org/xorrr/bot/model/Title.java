package org.xorrr.bot.model;

public class Title {

  private final String value;

  public Title(String title) {
    if (title == null) {
      throw new IllegalArgumentException("value cant be null");
    }
    this.value = title;
  }

  public String stringValue() {
    return this.value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Title title1 = (Title) o;

    return value != null ? value.equals(title1.value) : title1.value == null;
  }

  @Override
  public int hashCode() {
    return value != null ? value.hashCode() : 0;
  }
}
