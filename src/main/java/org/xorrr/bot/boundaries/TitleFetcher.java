package org.xorrr.bot.boundaries;

import java.util.Optional;

public interface TitleFetcher {

  public Optional<String> fetchTitleFrom(String uri);
}
