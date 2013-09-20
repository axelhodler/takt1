package bot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigHelper {

    private Properties prop;
    
    public ConfigHelper() {
	prop = new Properties();
	tryToLoadTheConfigFile();
    }
    
    private void tryToLoadTheConfigFile() {
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

}
