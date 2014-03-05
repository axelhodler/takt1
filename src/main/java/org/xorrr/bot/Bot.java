package org.xorrr.bot;

import java.io.IOException;

import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

import com.google.inject.Inject;

public class Bot {
    private PircBotX pircBotX;

    @Inject
    public Bot(PircBotX pircbotx) {
        this.pircBotX = pircbotx;
    };

    public void start() {
        try {
            pircBotX.startBot();
        } catch (IOException | IrcException e) {
            e.printStackTrace();
        }
    }
}
