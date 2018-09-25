package crypt;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static crypt.Rotor.*;

//import static org.mockito.Mockito.*;


public class EnigmaTest {
    //  @Mock
    //  Plugboard plugboard;
    //  @Mock
    //  Rotor rotor1;
    //  @Mock
    //  Rotor rotor2;
    //  @Mock
    //  Rotor rotor3;
    //  @Mock
    //  Reflector reflector;
    //  @InjectMocks
    //  Enigma enigma;
    //
    //  @Before
    //  public void setUp() {
    //    MockitoAnnotations.initMocks(this);
    //  }

    @Test
    public void testMapChars() throws Exception {
        Map<Character, Character> result = Enigma.mapChars("key", "value");
        Assert.assertEquals(new HashMap<Character, Character>() {{
            put(Character.valueOf('a'), Character.valueOf('a'));
        }}, result);
    }

    @Test
    public void testMapFuntions() throws Exception {
        int[] result = mapFuntions("key", "value");
        Assert.assertArrayEquals(new int[] {0}, result);
    }



    //  @Test
    //  public void testPressChar() throws Exception {
    //    Character result = enigma.pressChar(Character.valueOf('a'));
    //    Assert.assertEquals(Character.valueOf('a'), result);
    //  }

    @Test
    public void testMain() throws Exception {
        Enigma.main(new String[] {"arg"});
    }

    @Test
    public void testPressString() {


        Rotor rotor3 = new Rotor(WHEEL_I, "Y", "Q", "I", 'A', 0, null);
        Rotor rotor2 = new Rotor(WHEEL_II, "M", "E", "II", 'A', 0, rotor3);
        Rotor rotor1 = new Rotor(WHEEL_III, "D", "V", "III", 'A', 0, rotor2);

        Plugboard plugboard = new Plugboard("", "");
        Reflector reflector = new Reflector(Reflector.REFLECTOR_B);
        Enigma enigma = new Enigma(plugboard, rotor1, rotor2, rotor3, reflector);
        Assert.assertEquals("BDZGO",enigma.pressString("AAAAA"));
    }

    @Test
    public void testPressStringWithRingSetting() {


        Rotor rotor3 = new Rotor(WHEEL_I, "Y", "Q", "I", 'A', 1, null);
        Rotor rotor2 = new Rotor(WHEEL_II, "M", "E", "II", 'A', 1, rotor3);
        Rotor rotor1 = new Rotor(WHEEL_III, "D", "V", "III", 'A', 1, rotor2);

        Plugboard plugboard = new Plugboard("", "");
        Reflector reflector = new Reflector(Reflector.REFLECTOR_B);
        Enigma enigma = new Enigma(plugboard, rotor1, rotor2, rotor3, reflector);
        Assert.assertEquals("EWTYX",enigma.pressString("AAAAA"));
    }

    @Test
    public void testPressString1() {
        Rotor rotor3 = new Rotor(WHEEL_VIII, "HU", "ZM", "I", 'H', 19, null);
        Rotor rotor2 = new Rotor(WHEEL_VI, "HU", "ZM", "II", 'A', 7, rotor3);
        Rotor rotor1 = new Rotor(WHEEL_VIII, "HU", "ZM", "III", 'X', 16, rotor2);

        Plugboard plugboard = new Plugboard("", "");
        Reflector reflector = new Reflector(Reflector.REFLECTOR_C);
        Enigma enigma = new Enigma(plugboard, rotor1, rotor2, rotor3, reflector);
        Assert.assertEquals("BUG",enigma.pressString("HAX"));
    }

    @Test
    public void testPressStringTurnover() {
        Rotor rotor3 = new Rotor(WHEEL_VIII, "HU", "ZM", "I", 'L', 0, null);
        Rotor rotor2 = new Rotor(WHEEL_VI, "HU", "ZM", "II", 'Z', 0, rotor3);
        Rotor rotor1 = new Rotor(WHEEL_VIII, "HU", "ZM", "III", 'A', 0, rotor2);

        Plugboard plugboard = new Plugboard("", "");
        Reflector reflector = new Reflector(Reflector.REFLECTOR_C);
        Enigma enigma = new Enigma(plugboard, rotor1, rotor2, rotor3, reflector);
        Assert.assertEquals("FKSFM",enigma.pressString("AAAAA"));
    }
}
