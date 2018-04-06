import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringTimesTest {

    private StringTimes stringTimes = new StringTimes();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void stringTimes() {
    }

    @Test
    public void oneTime () {
        String expectedResult = "Hi";
        assertEquals(expectedResult, stringTimes.stringTimes("Hi", 1));
    }

    @Test
    public void threeTimes () {
        String expectedResult = "HiHiHi";
        assertEquals(expectedResult, stringTimes.stringTimes("Hi", 3));
    }

    @Test
    public void differentString () {
        String expectedResult = "Geronimo Geronimo ";
        assertEquals(expectedResult, stringTimes.stringTimes("Geronimo ", 2));
    }

    @Test
    public void nullString () {
        String expectedResult = "nullnullnullnullnull";
        assertEquals(expectedResult, stringTimes.stringTimes(null, 5));
    }

    @Test
    public void zeroTimes () {
        String expectedResult = "";
        assertEquals(expectedResult, stringTimes.stringTimes("Hi", 0));
    }
}