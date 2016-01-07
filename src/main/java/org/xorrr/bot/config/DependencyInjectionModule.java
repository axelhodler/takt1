package org.xorrr.bot.config;

import com.google.inject.AbstractModule;
import org.xorrr.bot.DefaultHandleChannelMessage;
import org.xorrr.bot.HandleChannelMessage;
import org.xorrr.bot.boundaries.TitleFetcher;
import org.xorrr.bot.boundaries.impl.JsoupTitleFetcher;

public class DependencyInjectionModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(HandleChannelMessage.class).to(DefaultHandleChannelMessage.class);

    bind(TitleFetcher.class).to(JsoupTitleFetcher.class);
  }

}
