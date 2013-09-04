package earth.xor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlGrabber {

    private String urlPattern = "((https?):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
    
    public String grabUrl(String sentence) {
	Pattern p = Pattern.compile(urlPattern);
	Matcher m = p.matcher(sentence);
	
	if (m.find()) {
	    return m.group(1);
	}
	
	return null;
    }

}
