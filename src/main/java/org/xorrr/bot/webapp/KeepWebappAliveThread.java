package org.xorrr.bot.webapp;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.xorrr.bot.config.EnvironmentVars;

public class KeepWebappAliveThread extends Thread {

    public KeepWebappAliveThread() {
        super();
        start();
    }

    @Override
    public void run() {
        try {
            keepWebappAlive();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void keepWebappAlive() throws InterruptedException, IOException {
        while (true) {
            sleepForThirtyMinutes();
            accessTheWebappUrl(new URL(System.getenv(EnvironmentVars.WEBAPPURL)));
        }
    }

    private void sleepForThirtyMinutes() throws InterruptedException {
        Thread.sleep(600000 * 2);
        System.out.println("connect to the webapp then sleep 10min");
    }

    private void accessTheWebappUrl(URL webappUrl) throws IOException {
        URLConnection myURLConnection = webappUrl.openConnection();
        myURLConnection.connect();
        myURLConnection.getContentType();
    }
}
