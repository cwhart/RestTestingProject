import org.junit.Test;

import static org.junit.Assert.*;

public class RotateLeftTest {

    // rotateLeft({1, 2, 3}) -> {2, 3, 1}
    // rotateLeft({5, 11, 9}) -> {11, 9, 5}
    // rotateLeft({7, 0, 0}) -> {0, 0, 7}

    RotateLeft rotateLeft = new RotateLeft();

    @Test
    public void firstTest() {
        int[] input = {1,2,3};
        int[] expected = {2,3,1};

        assertArrayEquals(expected, rotateLeft.rotateLeft(input));
    }

    @Test
    public void secondTest() {
        int[] input = {5, 11, 9};
        int[] expected = {11, 9, 5};

        assertArrayEquals(expected, rotateLeft.rotateLeft(input));
    }

    @Test
    public void thirdTest() {
        int[] input = {7, 0, 0};
        int[] expected = {0, 0, 7};

        assertArrayEquals(expected, rotateLeft.rotateLeft(input));
    }


}