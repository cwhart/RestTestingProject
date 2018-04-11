import org.junit.Test;

import static org.junit.Assert.*;

public class PosNegTest {

    // posNeg(1, -1, false) -> true
    // posNeg(-1, 1, false) -> true
    // posNeg(-4, -5, true) -> true

    PosNeg posNeg = new PosNeg();

    @Test
    public void firstTest() {
        assertTrue(posNeg.posNeg(1, -1, false));
    }

    @Test
    public void secondTest() {
        assertTrue(posNeg.posNeg(-1, 1, false));
    }

    @Test
    public void thirdTest() {
        assertFalse(posNeg.posNeg(-4, -5, false));
    }

    @Test
    public void fourthTest() {
        assertFalse(posNeg.posNeg(4, 5, false));
    }

    @Test
    public void fifthTest() {
        assertTrue(posNeg.posNeg(-4, -5, true));
    }

    @Test
    public void sixthTest() {
        assertFalse(posNeg.posNeg(4, 5, true));
    }

    @Test
    public void seventhTest() {
        assertFalse(posNeg.posNeg(-4, 5, true));
    }

    @Test
    public void eighthTest() {
        assertFalse(posNeg.posNeg(4, -5, true));
    }

}