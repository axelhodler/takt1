package org.xorrr.bot.webapp;

public class BotWebInterfaceRunner implements Runnable {

    private BotWebInterface app;

    public BotWebInterfaceRunner(BotWebInterface app) {
        this.app = app;
    }

    @Override
    public void run() {
        app.launchServer();
    }

}
