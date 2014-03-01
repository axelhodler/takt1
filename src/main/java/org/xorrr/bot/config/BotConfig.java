package org.xorrr.bot.config;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.xorrr.bot.BotHandler;

import com.google.inject.Inject;

public class BotConfig {
    private BotHandler listener;

    @Inject
    public BotConfig(BotHandler handler) {
        this.listener = handler;
    }

    public Configuration<PircBotX> createConfig() {
        Configuration<PircBotX> config = new Configuration.Builder<PircBotX>()
                .setName(get(EnvironmentVars.NAME))
                .setServerHostname(get(EnvironmentVars.SERVER))
                .addAutoJoinChannel(get(EnvironmentVars.CHANNEL))
                .addListener(listener)
                .setLogin(get(EnvironmentVars.IDENT))
                .setNickservPassword(get(EnvironmentVars.IDENT))
                .buildConfiguration();

        return config;
    }

    private String get(String var) {
        return System.getenv(var);
    }
}
