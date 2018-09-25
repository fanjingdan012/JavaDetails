package crypt;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RotorTest {
    //Field chars of type char[] - was not mocked since Mockito doesn't mock arrays
    //Field mapFuntions of type int[] - was not mocked since Mockito doesn't mock arrays
    //Field turnoverChars of type char[] - was not mocked since Mockito doesn't mock arrays
    //  @Mock
    //  Rotor nextRotor;
    //  @InjectMocks
    Rotor rotor;

    @Before
    public void setUp() {
        //MockitoAnnotations.initMocks(this);
        rotor = Rotor.ROTOR_I;
    }

    @Test
    public void testTurn() throws Exception {
        rotor.turn();
    }

    @Test
    public void testCheckTurnover() throws Exception {
        rotor.checkTurnover();
    }
    @Test
    public void testGetFunctionIndex(){
        rotor.setPos(1);
        int result = rotor.getFunctionIndex('B');
        Assert.assertEquals(2, result);
    }
    @Test
    public void testFw() throws Exception {
        rotor.setPos(1);
        int result = rotor.fw(0);
        Assert.assertEquals(9, result);
    }

    @Test
    public void testFwWithRingSetting() throws Exception{
        rotor.setPos(0);
        rotor.setRingSetting(1);
        int result = rotor.fw(0);
        Assert.assertEquals(10,result);
    }


    @Test
    public void testBw() throws Exception {
//        Character result = rotor.bw(Character.valueOf('a'));
//        Assert.assertEquals(Character.valueOf('a'), result);
        rotor.setDisplayPos(1);
        int result = rotor.bw(9);
        Assert.assertEquals(0, result);
    }
    @Test
    public void testMapFuntions() throws Exception {
        int[] result = Rotor.mapFuntions(Enigma.ALPHABET,Rotor.WHEEL_I);
        Assert.assertEquals(4, result[0]);
        Assert.assertEquals(9, result[1]);
    }




}

