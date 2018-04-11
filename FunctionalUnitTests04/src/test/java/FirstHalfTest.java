import org.junit.Test;

import static org.junit.Assert.*;

public class FirstHalfTest {
    // firstHalf("WooHoo") -> "Woo"
    // firstHalf("HelloThere") -> "Hello"
    // firstHalf("abcdef") -> "abc"

    FirstHalf firstHalf = new FirstHalf();

    @Test
    public void firstTest() {
        assertTrue(firstHalf.firstHalf("WooHoo").equals("Woo"));
    }

    @Test
    public void secondTest() {
        assertTrue(firstHalf.firstHalf("HelloThere").equals("Hello"));
    }

    @Test
    public void thirdTest() {
        assertTrue(firstHalf.firstHalf("abcdef").equals("abc"));
    }

    @Test
    public void fourth() {
        assertTrue(firstHalf.firstHalf("").equals(""));
    }

}