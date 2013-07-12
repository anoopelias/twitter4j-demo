package twitter4j.demo.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class UtilTest {
    @Test
    public void test_textToJson() {
        assertEquals("To Be or Not To be", Util.textToJson("To Be or Not To be"));
    }
    
    @Test
    public void test_textToJson_quotes() {
        assertEquals("To Be \\\"or \\\"Not To be", Util.textToJson("To Be \"or \"Not To be"));
    }
    
    @Test
    public void test_textToJson_newLine() {
        assertEquals("To Be or \\n Not To be", Util.textToJson("To Be or \n Not To be"));
    }
}
