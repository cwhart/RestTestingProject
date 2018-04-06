import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FirstLast6Test {

    private FirstLast6 firstLast = new FirstLast6();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void firstLast6() {
    }

    // firstLast6({1, 2, 6}) -> true
    // firstLast6({6, 1, 2, 3}) -> true
    // firstLast6({13, 6, 1, 2, 3}) -> false

    @Test
    public void testArray1() {
        int[] myArray = {1, 2, 6};
        assertTrue(firstLast.firstLast6(myArray));
    }
    @Test
    public void testArray2() {
        int[] myArray = {6, 1, 2, 3};
        assertTrue(firstLast.firstLast6(myArray));
    }
    @Test
    public void testArray3() {
        int[] myArray = {13, 6, 1, 2, 3};
        assertFalse(firstLast.firstLast6(myArray));
    }
    @Test
    public void testArray4() {
        int[] myArray = {6, 6};
        assertTrue(firstLast.firstLast6(myArray));
    }
    @Test
    public void testArray5() {
        int[] myArray = {0, 6};
        assertTrue(firstLast.firstLast6(myArray));
    }
    @Test
    public void testArray6() {
        int[] myArray = {6};
        assertTrue(firstLast.firstLast6(myArray));
    }
    @Test
    public void testArray7() {
        int[] myArray = {3};
        assertFalse(firstLast.firstLast6(myArray));
    }

}