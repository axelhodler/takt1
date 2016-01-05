package org.xorrr.bot.config;

import com.google.inject.AbstractModule;
import org.xorrr.bot.DefaultHandleChannelMessage;
import org.xorrr.bot.HandleChannelMessage;

public class DependencyInjectionModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(HandleChannelMessage.class).to(DefaultHandleChannelMessage.class);
  }

}
