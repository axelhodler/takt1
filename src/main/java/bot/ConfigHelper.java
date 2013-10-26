package bot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigHelper {

    private static ConfigHelper uniqueInstance = null;
    private static Properties prop;

    private ConfigHelper() {}

    public static ConfigHelper getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ConfigHelper();
            prop = new Properties();
            tryToLoadTheConfigFile();
        }
        return uniqueInstance;
    }

    private static void tryToLoadTheConfigFile() {
        try {
            prop.load(new FileInputStream("config.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getBotName() {
        return prop.getProperty("botname");
    }

    public String getServer() {
        return prop.getProperty("server");
    }

    public String getChannel() {
        return prop.getProperty("channel");
    }

    public String getIdentName() {
        return prop.getProperty("ident");
    }

    public String getIdentifyPassword() {
        return prop.getProperty("password");
    }

    public String getWebappUrl() {
        return prop.getProperty("webappurl");
    }

    public String getRestApiUrl() {
        return prop.getProperty("restapiurl");
    }
}
