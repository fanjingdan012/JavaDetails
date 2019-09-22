package crypt;

import hash.HashUtil;

import java.math.BigInteger;

public class CTFChallenge {
    private static final BigInteger one = new BigInteger("1");
    private BigInteger modulus;
    private BigInteger publicKey;
    private BigInteger privateKey;

    public CTFChallenge() {
        BigInteger p = new BigInteger("13767456700928940679");
        BigInteger q = new BigInteger("12386380973634958649");
        BigInteger phi = p.subtract(one).multiply(q.subtract(one));
        this.modulus = p.multiply(q);
        this.publicKey = new BigInteger("65537");
        this.privateKey = this.publicKey.modInverse(phi);
    }

    public BigInteger encrypt(BigInteger message) {
        return message.modPow(this.publicKey, this.modulus);
    }

    public String encryptWithRSA(String message) {
        String messageAfterPadding = this.paddingFirstPartOfMessage(message);
        return this.encrypt(new BigInteger(messageAfterPadding)).toString();
    }

    private String paddingFirstPartOfMessage(String firstPartOfMessage) {
        char[] messgaeAsCharArray = firstPartOfMessage.toCharArray();
        StringBuffer buf = new StringBuffer();
        char[] var4 = messgaeAsCharArray;
        int var5 = messgaeAsCharArray.length;

        for (int var6 = 0; var6 < var5; ++var6) {
            char messageChar = var4[var6];

            int lengthOfNumericValue = ("" + (int) messageChar).length();
            if (lengthOfNumericValue == 1) {
                buf.append("88" + (int) messageChar);
            }

            if (lengthOfNumericValue == 2) {
                buf.append("8" + (int) messageChar);
            }

            buf.append((int) messageChar);
        }

        return buf.toString();
    }

    public BigInteger decrypt(BigInteger encryptedMsg) {
        return encryptedMsg.modPow(this.privateKey, this.modulus);
    }



    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Answer is wrong");
        } else if (args[0].length() != 15) {
            System.out.println("Answer is wrong");
        } else {
            String firstPartOfMessage = args[0].substring(0, 10);
            String lastPartOfMessage = args[0].substring(10);
            CTFChallenge challenge = new CTFChallenge();
            String encryptedMessage = challenge.encryptWithRSA(firstPartOfMessage);
            boolean passFirstPart = false;
            if ("148728508633806583981745142113768023817".equals(encryptedMessage)) {
                passFirstPart = true;
            }

            boolean passSecondPart = HashUtil.md5(lastPartOfMessage).equals("E0F2D33D0AD8B260532AAD36484E8192");
            if (passFirstPart && passSecondPart) {
                System.out.println("Great! Congratulations to catch the flag! Go to CTF platform to validate your findings!");
            } else {
                System.out.println("Answer is wrong");
            }

        }
    }













}
