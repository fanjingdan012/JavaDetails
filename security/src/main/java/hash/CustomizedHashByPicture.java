package hash;

import encode.EncodeUtil;

public class CustomizedHashByPicture {
    public byte boldAdd(byte b1, byte b2) {
        return (byte) ((b1 + b2) & 255);
    }

    public byte boldAdd(byte b1, byte b2, byte b3) {
        return (byte) ((b1 + b2 + b3) & 255);
    }

    public byte circleAdd(byte b1, byte b2) {
        return (byte) (b1 ^ b2);
    }

    public byte[] customizedHash(String input) {
        byte[] start = EncodeUtil.hex2Bytes("DECAFBADABAD1DEA");
        byte[] inputBytes = input.getBytes();
        byte[] result = new byte[8];
        int i = 0;

        while (i < inputBytes.length) {
            for (byte r = 0; r < 12; r++) {
//                System.out.println("round:"+r+",inbyte:"+inputBytes[i]);
                result[0] = boldAdd(r, inputBytes[i], boldAdd(start[1], (byte) (circleAdd(start[2], start[4]) & start[5])));
                result[1] = start[0];
                result[2] = boldAdd(start[6], start[7]);
                result[3] = boldAdd(start[0], start[1]);
                result[4] = boldAdd(start[0], start[5]);
                result[5] = boldAdd(start[3], start[6]);
                result[6] = boldAdd(start[5], start[7]);
                result[7] = boldAdd(start[3], start[4]);
                start[0] = result[0];
                start[1] = result[1];
                start[2] = result[2];
                start[3] = result[3];
                start[4] = result[4];
                start[5] = result[5];
                start[6] = result[6];
                start[7] = result[7];
            }
            i++;
        }
        return result;
    }
}
