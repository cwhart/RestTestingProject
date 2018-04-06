import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CanHazTableTest {

    CanHazTable table = new CanHazTable();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void canHazTable() {
    }

    // canHazTable(5, 10) -> 2
    // canHazTable(5, 2) -> 0
    // canHazTable(5, 5) -> 1

    @Test
    public void fiveTen() {
        assertEquals(2, table.canHazTable(5,10));
    }

    @Test
    public void fiveTwo() {
        assertEquals(0, table.canHazTable(5,2));
    }

    @Test
    public void oneNine() {
        assertEquals(0, table.canHazTable(1,9));
    }

    @Test
    public void twoOne() {
        assertEquals(0, table.canHazTable(2,1));
    }

    @Test
    public void fiveFive() {
        assertEquals(1, table.canHazTable(5,5));
    }

    @Test
    public void eightTen() {
        assertEquals(2, table.canHazTable(8,10));
    }

    @Test
    public void tenOne() {
        assertEquals(0, table.canHazTable(10,1));
    }

    @Test
    public void nineFour() {
        assertEquals(2, table.canHazTable(9,4));
    }

}