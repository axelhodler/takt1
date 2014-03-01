package org.xorrr.bot.webapp;

public class WebAppThread implements Runnable {

    private WebApp app;

    public WebAppThread(WebApp app) {
        this.app = app;
    }

    public void run() {
        app.launchServer();
    }

}
