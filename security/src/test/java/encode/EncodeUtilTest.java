package encode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EncodeUtilTest {

    @Test
    public void testBufferToHex() throws Exception {
        String result = EncodeUtil.bufferToHex(new byte[]{(byte) 0, (byte) 128});
        assertEquals("0080", result);
    }

    @Test
    public void testStringToHex() {
        assertEquals("6765", EncodeUtil.stringToHex("ge"));
    }

    @Test
    public void testHexToString() {
        assertEquals("ge", EncodeUtil.hexToString("6765"));
    }
}