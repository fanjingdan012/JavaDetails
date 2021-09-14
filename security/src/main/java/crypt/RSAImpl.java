package crypt;

import java.math.BigInteger;

public class RSAImpl {
    private static final BigInteger one = new BigInteger("1");


    public BigInteger p;
    public BigInteger q;
    public BigInteger n;//=p*q
    public BigInteger d;//d=e.modeInverse(phi) i.e.(d*e)mod phi =1
    public BigInteger e;
    public BigInteger phi;//=(p-1)*(q-1)
    public BigInteger publicKey;//=(n,e)
    public BigInteger privateKey;//=(n,d)

    public RSAImpl() {

    }

    public RSAImpl(BigInteger p, BigInteger q) {
        this.p = p;
        this.q = q;
        this.phi = calculatePhi(p, q);
        this.n = p.multiply(q);
        this.e = new BigInteger("65537");
        this.d = this.e.modInverse(phi);
    }

    public RSAImpl(BigInteger p, BigInteger q, BigInteger e) {
        this.p = p;
        this.q = q;
        this.phi = calculatePhi(p, q);
        this.n = p.multiply(q);
        this.e = e;
        this.d = this.e.modInverse(phi);
    }

    public BigInteger encrypt(BigInteger message) {
        return message.modPow(this.e, this.n);
    }


    public BigInteger decrypt(BigInteger encryptedMsg) {
        return encryptedMsg.modPow(this.d, this.n);
    }

    public static BigInteger primeFactorize(BigInteger bigInteger) {
        BigInteger n = new BigInteger(bigInteger.toByteArray());
        BigInteger k;


        for (k = new BigInteger("2"); k.compareTo(n) <= 0; k = k.add(one)) {
            if (n.mod(k).compareTo(new BigInteger("0")) == 0) {
                return k;

            }

        }
        return null;


    }

    public static BigInteger primeFactorizeFromMiddle(BigInteger bigInteger) {
        BigInteger n = new BigInteger(bigInteger.toByteArray());
        BigInteger k;

        BigInteger from = bigInteger.sqrt();
        for (k = from; k.compareTo(n) <= 0; k = k.add(one)) {
            if (n.mod(k).compareTo(new BigInteger("0")) == 0) {
                return k;

            }

        }
        return null;


    }

    public BigInteger eulideanGcd(BigInteger m, BigInteger n) {
        if (m.mod(n).compareTo(BigInteger.ZERO) == 0) {
            return n;
        } else {
            return eulideanGcd(n, m.mod(n));
        }
    }

    public static BigInteger calculatePhi(BigInteger p, BigInteger q) {
        BigInteger phi = p.subtract(one).multiply(q.subtract(one));
        return phi;
    }

    public static BigInteger calculateD(BigInteger e, BigInteger phi) {
        BigInteger d = e.modInverse(phi);
        return d;
    }

    public static void explainRSA() {
        BigInteger p = new BigInteger("23");
        System.out.println("p=23");
        BigInteger q = new BigInteger("19");
        System.out.println("q=19");
        BigInteger n = p.multiply(q);
        System.out.println("n=p*q=437");
        BigInteger phi = new BigInteger(22 * 18 + "");
        System.out.println("phi=(p-1)*(q-1)=396");
        BigInteger e = new BigInteger("17");
        System.out.println("e=17");
        BigInteger d = e.modInverse(phi);
        System.out.println("d=e.modeInverse(phi)=233, i.e. " + "d(" + d + ")*e(" + e + ") mod phi(" + phi + ") = 1");
        System.out.println("public key is (n,e)");
        System.out.println("private key is (n,d)");
        BigInteger plainMsg = new BigInteger("100");
        BigInteger encryptedMsg = plainMsg.modPow(e, n);
        System.out.println("encryptedMsg=plainMsg.modePow(e,n)=plainMsg^e mod n=(100^17)%437=289（need BigInteger)");
        System.out.println("encrypt 100:" + encryptedMsg);
        BigInteger plainMsg2 = encryptedMsg.modPow(d, n);
        System.out.println("plainMsg2=encryptedMsg.modePow(d,n)=encryptedMsg^d mod n=(289^233)%437=100");
        System.out.println("decrypt " + encryptedMsg + ":" + plainMsg2);
    }

    public static BigInteger hcf(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO)) {
            return a;
        } else {
            return hcf(b, a.mod(b));
        }
    }
}
