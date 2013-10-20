package webapp;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import bot.ConfigHelper;

public class KeepWebappAliveThread extends Thread {

    public KeepWebappAliveThread(String name) {
        super(name);
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
            accessTheWebappUrl(tryToGetWebappUrl());
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

    private URL tryToGetWebappUrl() {
        ConfigHelper configHelper = new ConfigHelper();

        URL webappUrl = null;
        try {
            webappUrl = new URL(configHelper.getWebappUrl());
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }
        return webappUrl;
    }
}
