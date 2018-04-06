import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbbaTest {

    Abba abba = new Abba();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void abba() {
    }

    // abba("Hi", "Bye") -> "HiByeByeHi"
    // abba("Yo", "Alice") -> "YoAliceAliceYo"
    // abba("What", "Up") -> "WhatUpUpWhat"

    @Test
    public void hiBye () {
        String expectedResult = "HiByeByeHi";
        assertEquals(expectedResult, abba.abba("Hi", "Bye"));
    }

    @Test
    public void yoAlice () {
        String expectedResult = "YoAliceAliceYo";
        assertEquals(expectedResult, abba.abba("Yo", "Alice"));
    }

    @Test
    public void whatUp () {
        String expectedResult = "WhatUpUpWhat";
        assertEquals(expectedResult, abba.abba("What", "Up"));
    }

    @Test
    public void includeSpaces () {
        String expectedResult = "word wordtwo twotwo twoword word";
        assertEquals(expectedResult, abba.abba("word word", "two two"));
    }

    @Test
    public void oneNull () {
        String expectedResult = null + "ShrubberyShrubbery" + null;
        assertEquals(expectedResult, abba.abba(null, "Shrubbery"));
    }
}