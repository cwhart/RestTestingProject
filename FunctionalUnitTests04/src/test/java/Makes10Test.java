import org.junit.Test;

import static org.junit.Assert.*;

public class Makes10Test {

    // makes10(9, 10) -> true
    // makes10(9, 9) -> false
    // makes10(1, 9) -> true

    Makes10 makes10 = new Makes10();

    @Test
    public void firstTest() {
        assertTrue(makes10.makes10(9,10));
    }

    @Test
    public void secondTest() {
        assertFalse(makes10.makes10(9,9));
    }

    @Test
    public void thirdTest() {
        assertFalse(makes10.makes10(0,0));
    }

}