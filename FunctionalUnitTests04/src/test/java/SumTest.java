import org.junit.Test;

import static org.junit.Assert.*;

public class SumTest {
    // sum({1, 2, 3}) -> 6
    // sum({5, 11, 2}) -> 18
    // sum({7, 0, 0}) -> 7

    Sum sum = new Sum();

    @Test
    public void firstTest() {
        int[] input = {1, 2, 3};
        assertEquals(6, sum.sum(input));
    }

    @Test
    public void secondTest() {
        int[] input = {5, 11, 2};
        assertEquals(18, sum.sum(input));
    }

    @Test
    public void thirdTest() {
        int[] input = {7, 0, 0};
        assertEquals(7, sum.sum(input));
    }

}