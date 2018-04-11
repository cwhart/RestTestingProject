import org.junit.Test;

import static org.junit.Assert.*;

public class DoubleXTest {

    DoubleX dblx = new DoubleX();

    @Test
    public void testFirstString() {
        assertTrue(dblx.doubleX("axxbb"));

        }

    @Test
    public void testSecondString() {
        assertFalse(dblx.doubleX("axaxxax"));

    }

    @Test
    public void testThirdString() {
        assertTrue(dblx.doubleX("xxxxx"));

    }

    @Test
    public void testFourthString() {
        assertFalse(dblx.doubleX("brehxhtrwxx"));

    }

    @Test
    public void testFifthString() {
        assertTrue(dblx.doubleX("xxhtrydmhgdxy"));

    }
    }
