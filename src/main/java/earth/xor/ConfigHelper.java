package earth.xor;

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
	// TODO Auto-generated method stub
	return null;
    }

    public String getChannel() {
	// TODO Auto-generated method stub
	return null;
    }

}
