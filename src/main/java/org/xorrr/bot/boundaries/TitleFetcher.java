package org.xorrr.bot.boundaries;

import org.xorrr.bot.model.Title;

import java.util.Optional;

public interface TitleFetcher {

  public Optional<Title> fetchTitleFrom(String uri);
}
