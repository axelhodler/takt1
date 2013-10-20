package webapp;

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

        ConfigHelper configHelper = new ConfigHelper();

        URL myurl = null;
        try {
            myurl = new URL(configHelper.getWebappUrl());
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }
        URLConnection myURLConnection;

        try {
            while (true) {
                Thread.sleep(600000);
                System.out.println("connect to the webapp then sleep 10min");
                myURLConnection = myurl.openConnection();
                myURLConnection.connect();
                myURLConnection.getContentType();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
