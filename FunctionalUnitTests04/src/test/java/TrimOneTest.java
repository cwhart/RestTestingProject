import org.junit.Test;

import static org.junit.Assert.*;

public class TrimOneTest {

    // trimOne("Hello") -> "ell"
    // trimOne("java") -> "av"
    // trimOne("coding") -> "odin"

    TrimOne trimOne = new TrimOne();

    @Test
    public void firstTest() {
        assertTrue(trimOne.trimOne("Hello").equals("ell"));
    }

    @Test
    public void secondTest() {
        assertTrue(trimOne.trimOne("java").equals("av"));
    }

    @Test
    public void thirdTest() {
        assertTrue(trimOne.trimOne("coding").equals("odin"));
    }

    @Test
    public void fourthTest() {
        assertTrue(trimOne.trimOne("Black Death").equals("lack Deat"));
    }

}