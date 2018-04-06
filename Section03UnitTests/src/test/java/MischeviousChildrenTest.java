import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MischeviousChildrenTest {

    MischeviousChildren mischeviousChildren = new MischeviousChildren();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void areWeInTrouble() {

    }

    @Test
    public void testTrueTrue() {
        assertTrue(mischeviousChildren.areWeInTrouble(true, true));
    }

    @Test
    public void testFalseFalse() {
        assertTrue(mischeviousChildren.areWeInTrouble(false, false));
    }

    @Test
    public void testTrueFalse() {
        assertFalse(mischeviousChildren.areWeInTrouble(true, false));
    }
}