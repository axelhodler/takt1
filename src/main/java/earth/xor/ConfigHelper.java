package earth.xor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigHelper {

    public String getBotName() {
	Properties prop = new Properties();
	
	try {
	    prop.load(new FileInputStream("config.properties"));
	    return prop.getProperty("botname");
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

}
