import org.junit.Test;

import static org.junit.Assert.*;

public class CaughtSpeedingTest {

    // caughtSpeeding(60, false) â†’ 0
    // caughtSpeeding(65, false) â†’ 1
    //caughtSpeeding(65, true) â†’ 0

    CaughtSpeeding caughtSpeeding = new CaughtSpeeding();

    @Test
    public void firstTest() {
        assertEquals(0, caughtSpeeding.caughtSpeeding(60, false));
    }

    @Test
    public void secondTest() {
        assertEquals(1, caughtSpeeding.caughtSpeeding(65, false));
    }

    @Test
    public void thirdTest() {
        assertEquals(0, caughtSpeeding.caughtSpeeding(65, true));
    }
}