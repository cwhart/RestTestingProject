import org.junit.Test;

import static org.junit.Assert.*;

public class EveryOtherTest {
    // everyOther("Hello") -> "Hlo"
    // everyOther("Hi") -> "H"
    // everyOther("Heeololeo") -> "Hello"

    EveryOther everyOther = new EveryOther();

    @Test
    public void firstTest() {
        assertTrue(everyOther.everyOther("Hello").equals("Hlo"));
    }

    @Test
    public void secondTest() {
        assertTrue(everyOther.everyOther("Hi").equals("H"));
    }

    @Test
    public void thirdTest() {
        assertTrue(everyOther.everyOther("Heeololeo").equals("Hello"));
    }

}