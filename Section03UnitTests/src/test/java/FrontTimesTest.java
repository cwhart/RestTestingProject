import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FrontTimesTest {

    FrontTimes frontTimes = new FrontTimes();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void frontTimes() {
    }


    // frontTimes("Chocolate", 2) -> "ChoCho"
    // frontTimes("Chocolate", 3) -> "ChoChoCho"
    // frontTimes("Abc", 3) -> "AbcAbcAbc"

    @Test
    public void chocolateTwo() {
        String expectedResult = "ChoCho";
        assertEquals(expectedResult, frontTimes.frontTimes("Chocolate", 2));
    }

    @Test
    public void chocolateThree() {
        String expectedResult = "ChoChoCho";
        assertEquals(expectedResult, frontTimes.frontTimes("Chocolate", 3));
    }

    @Test
    public void abcThree() {
        String expectedResult = "AbcAbcAbc";
        assertEquals(expectedResult, frontTimes.frontTimes("Abc", 3));
    }

    @Test
    public void shortStrTwice() {
        String expectedResult = "ChCh";
        assertEquals(expectedResult, frontTimes.frontTimes("Ch", 2));
    }

    @Test
    public void stringZeroTimes() {
        String expectedResult = "";
        assertEquals(expectedResult, frontTimes.frontTimes("Chocolate", 0));
    }

    @Test
    public void blankStringTwice() {
        String expectedResult = "";
        assertEquals(expectedResult, frontTimes.frontTimes("", 2));
    }
}