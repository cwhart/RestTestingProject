import org.junit.Test;

import static org.junit.Assert.*;

public class AlarmClockTest {

// alarmClock(1, false) â†’ "7:00"
// alarmClock(5, false) â†’ "7:00"
// alarmClock(0, false) â†’ "10:00"

    AlarmClock alarmClock = new AlarmClock();

    @Test
    public void firstTest() {
        assertTrue("7:00".equals(alarmClock.alarmClock(1, false)));
    }

    @Test
    public void secondTest() {
        assertTrue("7:00".equals(alarmClock.alarmClock(5, false)));
    }

    @Test
    public void thirdTest() {
        assertTrue("10:00".equals(alarmClock.alarmClock(0, false)));
    }

    @Test
    public void fourthTest() {
        assertTrue("10:00".equals(alarmClock.alarmClock(2, true)));
    }

    @Test
    public void fifthTest() {
        assertTrue("off".equals(alarmClock.alarmClock(6, true)));
    }

}