import org.junit.Test;

import static org.junit.Assert.*;

public class InsertWordTest {

    InsertWord insertWord = new InsertWord();

    // insertWord("<<>>", "Yay") -> "<<Yay>>"
    // insertWord("<<>>", "WooHoo") -> "<<WooHoo>>"
    // insertWord("[[]]", "word") -> "[[word]]"

    @Test
    public void firstTest() {
        assertTrue(insertWord.insertWord("<<>>", "Yay").equals("<<Yay>>"));

        }

    @Test
    public void secondTest() {
        assertTrue(insertWord.insertWord("<<>>", "WooHoo").equals("<<WooHoo>>"));

    }

    @Test
    public void thirdTest() {
        assertTrue(insertWord.insertWord("[[]]", "word").equals("[[word]]"));

    }
}