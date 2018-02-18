package org.xorrr.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.xorrr.bot.config.IrcBotConfiguration;

@SpringBootApplication
@EnableConfigurationProperties(IrcBotConfiguration.class)
public class Main {

  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }

}
