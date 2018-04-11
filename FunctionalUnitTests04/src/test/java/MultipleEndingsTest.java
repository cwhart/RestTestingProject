import org.junit.Test;

import static org.junit.Assert.*;

public class MultipleEndingsTest {

    // multipleEndings("Hello") -> "lololo"
    // multipleEndings("ab") -> "ababab"
    // multipleEndings("Hi") -> "HiHiHi"

    MultipleEndings multipleEndings = new MultipleEndings();

    @Test
    public void firstTest() {
        assertTrue(multipleEndings.multipleEndings("Hello").equals("lololo"));
    }

    @Test
    public void secondTest() {
        assertTrue(multipleEndings.multipleEndings("ab").equals("ababab"));
    }

    @Test
    public void thirdTest() {
        assertTrue(multipleEndings.multipleEndings("Hi").equals("HiHiHi"));
    }


}