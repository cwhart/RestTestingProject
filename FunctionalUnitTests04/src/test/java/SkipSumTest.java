import org.junit.Test;

import static org.junit.Assert.*;

public class SkipSumTest {
    // skipSum(3, 4) â†’ 7
    // skipSum(9, 4) â†’ 20
    // skipSum(10, 11) â†’ 21

    SkipSum skipSum = new SkipSum();

    @Test
    public void firstTest() {
        assertEquals(7, skipSum.skipSum(3, 4));
    }

    @Test
    public void secondTest() {
        assertEquals(20, skipSum.skipSum(9, 4));
    }

    @Test
    public void thirdTest() {
        assertEquals(21, skipSum.skipSum(10, 11));
    }

}