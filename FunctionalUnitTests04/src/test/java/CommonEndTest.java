import org.junit.Test;

import static org.junit.Assert.*;

public class CommonEndTest {

    CommonEnd commonEnd = new CommonEnd();

    // commonEnd({1, 2, 3}, {7, 3}) -> true
    // commonEnd({1, 2, 3}, {7, 3, 2}) -> false
    // commonEnd({1, 2, 3}, {1, 3}) -> true

    @Test
    public void firstTest() {
        int[] a = {1, 2, 3};
        int[] b = {7, 3};

        assertTrue(commonEnd.commonEnd(a, b));
    }

    @Test
    public void secondTest() {
        int[] a = {1, 2, 3};
        int[] b = {7, 3, 2};

        assertFalse(commonEnd.commonEnd(a, b));
    }

    @Test
    public void thirdTest() {
        int[] a = {1, 2, 3};
        int[] b = {1, 3};

        assertTrue(commonEnd.commonEnd(a, b));
    }

}