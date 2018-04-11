import org.junit.Test;

import static org.junit.Assert.*;

public class NearHundredTest {

// nearHundred(103) -> true
// nearHundred(90) -> true
// nearHundred(89) -> false
//
    NearHundred nearHundred = new NearHundred();

    @Test
    public void firstTest() {
        assertTrue(nearHundred.nearHundred(103));
    }

    @Test
    public void secondTest() {
        assertTrue(nearHundred.nearHundred(90));
    }

    @Test
    public void thirdTest() {
        assertFalse(nearHundred.nearHundred(89));
    }

}