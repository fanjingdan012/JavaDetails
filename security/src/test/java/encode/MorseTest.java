package encode;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MorseTest {
    @Test
    public void testCodeReviewMorse() {
        assertEquals(Morse.decode("-.-. --- -.. .  .-. . ...- .. . .--"), "CODE REVIEW");
    }

    @Test
    public void test2ndMonitor() {
        assertEquals(Morse.decode("..--- -. -..  -- --- -. .. - --- .-."), "2ND MONITOR");
    }

    @Test
    public void testFromCodeEval() {
        assertEquals(Morse.decode("..-. .-. --- --  -.-. --- -.. . . ...- .- .-.."), "FROM CODEEVAL");
    }
}

