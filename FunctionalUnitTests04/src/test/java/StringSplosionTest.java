import org.junit.Test;

import static org.junit.Assert.*;

public class StringSplosionTest {

    // stringSplosion("Code") -> "CCoCodCode"
    // stringSplosion("abc") -> "aababc"
    // stringSplosion("ab") -> "aab"

    StringSplosion stringSplosion = new StringSplosion();

    @Test
    public void firstTest() {
        assertTrue(stringSplosion.stringSplosion("Code").equals("CCoCodCode"));
    }

    @Test
    public void secondTest() {
        assertTrue(stringSplosion.stringSplosion("abc").equals("aababc"));
    }

    @Test
    public void thirdTest() {
        assertTrue(stringSplosion.stringSplosion("ab").equals("aab"));
    }

}