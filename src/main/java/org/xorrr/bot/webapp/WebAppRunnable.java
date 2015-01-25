package org.xorrr.bot.webapp;

public class WebAppRunnable implements Runnable {

    private WebApp app;

    public WebAppRunnable(WebApp app) {
        this.app = app;
    }

    @Override
    public void run() {
        app.launchServer();
    }

}
