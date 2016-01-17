package org.xorrr.bot.model;

public class Title {

  private final String title;

  public Title(String title) {
    this.title = title;
  }

  public String stringValue() {
    return this.title;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Title title1 = (Title) o;

    return title != null ? title.equals(title1.title) : title1.title == null;
  }

  @Override
  public int hashCode() {
    return title != null ? title.hashCode() : 0;
  }
}
