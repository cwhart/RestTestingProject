//package functionaltests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GreatPartyTest {

    GreatParty party = new GreatParty();

    public GreatPartyTest() {

    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void greatParty() {
    }

    @Test
    public void test30False() {
        assertFalse(party.greatParty(30, false));
    }

    @Test
    public void test50False() {
        assertTrue(party.greatParty(50, false));
    }

    @Test
    public void test39True() {
        assertFalse(party.greatParty(39, true));
    }

    @Test
    public void test39False() {
        assertFalse(party.greatParty(39, false));
    }

    @Test
    public void test40True() {
        assertTrue(party.greatParty(40, true));
    }

    @Test
    public void test40False() {
        assertTrue(party.greatParty(40, false));
    }

    @Test
    public void test60True() {
        assertTrue(party.greatParty(60, true));
    }

    @Test
    public void test60False() {
        assertTrue(party.greatParty(60, false));
    }

    @Test
    public void test61True() {
        assertTrue(party.greatParty(61, true));
    }

    @Test
    public void test61False() {
        assertFalse(party.greatParty(61, false));
    }
}