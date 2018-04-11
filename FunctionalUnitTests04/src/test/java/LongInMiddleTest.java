import org.junit.Test;

import static org.junit.Assert.*;

public class LongInMiddleTest {

    // longInMiddle("Hello", "hi") -> "hiHellohi"
    // longInMiddle("hi", "Hello") -> "hiHellohi"
    // longInMiddle("aaa", "b") -> "baaab"

    LongInMiddle longInMiddle = new LongInMiddle();

    @Test
    public void firstTest() {
        assertTrue(longInMiddle.longInMiddle("Hello", "hi").equals("hiHellohi"));
    }

    @Test
    public void secondTest() {
        assertTrue(longInMiddle.longInMiddle("hi", "Hello").equals("hiHellohi"));
    }

    @Test
    public void thirdTest() {
        assertTrue(longInMiddle.longInMiddle("aaa", "b").equals("baaab"));
    }

}