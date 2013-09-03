package earth.xor;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexLearning {

    public String basicText = "foo www.test.org bar";
    
    @Test
    public void testGettingTheUrl() {
	Pattern p = Pattern.compile("www.test.org");
	Matcher m = p.matcher(basicText);
	m.find();
	
	assertEquals("www.test.org", m.group());
    }
}
