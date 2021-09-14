package crypt;

import encode.EncodeUtil;
import file.FileUtil;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class RSAImplTest {

    @Test
    public void explain(){
        RSAImpl.explainRSA();
    }
    @Test
    public void testEncrypt() throws Exception {

    }

    @Test
    public void testEuclideanGcd(){
        RSAImpl rsa = new RSAImpl();
        assertEquals(new BigInteger("5"),rsa.eulideanGcd(new BigInteger("15"),new BigInteger("20")));
    }

    @Test
    public void testDecrypt() throws Exception {
        RSAImpl rsa = new RSAImpl();
        rsa.n = new BigInteger("a245934bdc547847f8c7eb0d7626dedb6ee2ee8e6e90bcc743e77d13972ea6155517aee49cade3e2200d10ba534151e954f6434ddfe8b1fde3d25c8a8fbccfd7c6f7ac3529d6f0a46e2670c2736121bbe096bdc8ceaf33b594c88f8948bae692ad07b1c46e9ad3a0dee68b265ce4cad123315b61ffe68dc9c20fc08903b0a2c5", 16);
        rsa.d = new BigInteger("1bbf15ce94e004a919cae55029f1bf8d79ca67cc06efccb7adceb93e4e4d75ac941e3693748eef364d10aed98d3ed008bbb45811cac57919d688b3b62599bd57fbde2f97538f1ca1c6604de31b49d9f41acc815e3028430053e31d879c62f731c62b8a49d1c3e1ca635eaaff37a1815ca50f461c23d9e4dd1babaa1f13d6e289", 16);
        rsa.e = new BigInteger("10001", 16);
        BigInteger C = new BigInteger("139f9bc08a2b03cb2ca7903301af41b0a5b837a215ae5961e13a656e529d19993e3802362b1c426a74bcde19ee1ad2a5935d9dd92eb94296e43b55d6d4209a84cf09769cdc6ff8cdf24ff77ff2a0d8be4fda98efa1e3d2c7209f433c9bb17c18aeafd2165431576e54db1c4203c2563a52d0c471a00eb11195227eb6f15bab9c", 16);
        BigInteger plainMsg = rsa.decrypt(C);
        System.out.println(plainMsg.toString(16));
    }

    @Test
    public void testPrimeFactorize() {
        BigInteger n = new BigInteger("cd626fe3069991d", 16);
        BigInteger p = RSAImpl.primeFactorize(n);
        BigInteger q = n.divide(p);
        System.out.println(p.toString(16));
        System.out.println(q.toString(16));
        //39532861
        //39533a3d
    }

    @Test
    public void getPlainTextBy3PublicKeys() {
        //C=plainMsg^e mod n
        BigInteger e = new BigInteger("3", 16);
        BigInteger n0 = new BigInteger("b69da93b6bd0743e7ce391acf12243c3132f9c92bfa8121018362db577c0af8e2bd29a1378aafbe90a959d1d8d1411811ce43d65665f42b6309bc96e4ec0fb60689581c531a35f9106e3bcb93435eb2d880d7903af192e29a8c8fa9acc10a811126d92a052a67b929192dcdcf6dfaf438f1103ac4c3d760f60b0f08dfad4764b", 16);
        BigInteger n1 = new BigInteger("95915e91e757c72659898f2f5d4d93d6e69b3ea92dd21e823a2db26ffac7fb14f4bfef415b78d47b702329d324f6b73b390a17e2012ab760801714a2206ad9d0b03e3268f424d5a66c191b4de9c6c8b7eef91724971ae1411cc9972c6ab9d53d18d6b9541b02bd5e0353acb5af19247b42d25c53faa8e8b12ee63aebf953524d", 16);
        BigInteger n2 = new BigInteger("bb805ec9120880a01db5e0b8b8dcab5ceff8acdcce9151f97005658c0ef462229163d37eaf9923089f028551ef510d4dda3a370f09f883770895ae9f528dd38ac3c66060127ff674fe70e28e8a5a52616dcd06f6c75f11f8ae760f4a4f3665d1fa195445b9b4847bd26b05b66f4480d8484e5719e1e38df41414dddd8c722217", 16);
        BigInteger C0 = new BigInteger("90418c1d221c58efff8d89dd639df6ab8144110553b0c4c779f00693a9e87060f952210189ce6518e05757491cea63dc4da57bbf44c235699d5129a5a8876850f526188566c7a80c2ecd9136e7ebb6d8cc4ab84fb4348536c821439107fd299ddab2d7c994b0da33ada72cb80b17ea0cc4914bd01fff5a17fd2ef72184396bb1", 16);
        BigInteger C1 = new BigInteger("52c22645f85f760ca3a5510cd766b783d095216a3c21db4ed24a2c45df5fa0cd13d7d7bf854c4f118fe21d8d8e8382027c31d5b906b074d99f9135a466e0882b8e7a7ba1947b7741f45b698085df244842117474747bfeaf0363cc5a3768e5f513874655bd0301b8e978f3a2611a17c36f384961ed7ee1e71bd9f684e07bdfab", 16);
        BigInteger C2 = new BigInteger("9763809263d04387219a1656b1e1bb387047e12b4984f3e5f26784d6faee5c918b457f77cecd20529d71c1d927ce7b28ecee31cc36c359aacb53a559a1953fe2313391fb7199e45532ea0b450ddd1172a94a48c2078f0737b64ce787a86d363b7c7b5a3d534128b8be0c1e1ea4d9c4577d94442c508bfdeec71fcbf38e2e5fc5", 16);
        //中国剩余定理, 共模攻击
        BigInteger N = n0.multiply(n1).multiply(n2);
        BigInteger N0 = N.divide(n0);
        BigInteger N1 = N.divide(n1);
        BigInteger N2 = N.divide(n2);
        BigInteger a0 = C0.multiply(N0).multiply(N0.modInverse(n0));
        BigInteger a1 = C1.multiply(N1).multiply(N1.modInverse(n1));
        BigInteger a2 = C2.multiply(N2).multiply(N2.modInverse(n2));
        BigInteger x = (a0.add(a1).add(a2)).mod(N);//=plainMsg^e
        System.out.println(rootN_Decimal(x.toString(), 3, 2));
        BigInteger plainMsg = new BigInteger("404081516936149541254143809721856662775757651915039301284353173758095544292444844295608669136536017264707716307916812894657745117387828577500481874420773218657394825640581325378908974");
        RSAImpl rsa = new RSAImpl();
        rsa.n = n2;
        rsa.e = e;
        assertEquals(C2, rsa.encrypt(plainMsg));
        System.out.println(EncodeUtil.bytes2Hex(plainMsg.toByteArray()));
        System.out.println(EncodeUtil.hex2String("616162626f747433302c20686f77277320796f7572206461793f20536f756e6473206c696b6520736f6d65626f6479277320676f7420612063617365206f6620746865204d6f6e646179732e"));
    }


    public static String rootN_Decimal(String num, int n, int precision) {

        BigDecimal x = new BigDecimal(new BigInteger(num).divide(new BigInteger(n + "")));
        BigDecimal x0 = BigDecimal.ZERO;

        BigDecimal e = new BigDecimal("0.1");
        for (int i = 1; i < precision; ++i)
            e = e.divide(BigDecimal.TEN, i + 1, BigDecimal.ROUND_HALF_EVEN);

        BigDecimal K = new BigDecimal(num);
        BigDecimal m = new BigDecimal(n);

        long i = 0;
        while (x.subtract(x0).abs().compareTo(e) > 0) {
            x0 = x;
            x = x.add(K.subtract(x.pow(n)).divide(m.multiply(x.pow(n - 1)), precision, BigDecimal.ROUND_HALF_EVEN));
            ++i;
        }
        return x + " " + i;
    }


    public static BigInteger gcd(BigInteger a, BigInteger b) {

        if(a.compareTo(b)<0){
            BigInteger tmp = b;
            b=a;
            a=tmp;
        }
        BigInteger r=a.mod(b);
        while(r.compareTo(BigInteger.ZERO)!=0) {		//辗转相除
            a=b;
            b=r;
            r=a.mod(b);
        }
        return b;
    }

    @Test
    public void fakeRandom(){
        List<String> ns = FileUtil.readTxtFileIntoStringArrList("fakeRandom.txt");
        BigInteger n1 = new BigInteger("fb198fa680d31b81fc6a40463b8c199c792df6a463b11bc8f502ea00e61f6b0128ffd4e8f00b74d1df784ac378c85748065772cf08d632b317a2d61e6de3d47bc63bd512eb8cba4deefb34f34b80842e8a8676bed154d237d87c3f1446af6cc936bbaaf71be1a1414a9313d5f3be04b47cecb15222974c1b2163452ac2c154ef",16);
        BigInteger p = BigInteger.ONE;
        for(String nStr:ns){
            BigInteger n = new BigInteger(nStr,16);
            p = gcd(n1,n);
            if(p.compareTo(BigInteger.ONE)>0){
                System.out.println(p);
                break;
            }
        }
        //13161213015599996204315356217317072130635448992721093845182830128173647180021414564110650778864716477562161900951899139691545827135223926159383826813733283
        BigInteger q = n1.divide(p);
        BigInteger phi = RSAImpl.calculatePhi(p,q);
        BigInteger d = RSAImpl.calculateD(new BigInteger("10001",16),phi);
        System.out.println(d.toString(16));

    }

    @Test
    public void testPrimeFactorizeFromMiddle() {
        BigInteger n = new BigInteger("1209143407476550975641959824312993703149920344437422193042293131572745298662696284279928622412441255652391493241414170537319784298367821654726781089600780498369402167443363862621886943970468819656731959468058528787895569936536904387979815183897568006750131879851263753496120098205966442010445601534305483783759226510120860633770814540166419495817666312474484061885435295870436055727722073738662516644186716532891328742452198364825809508602208516407566578212780807");
        BigInteger p = RSAImpl.primeFactorizeFromMiddle(n);
        BigInteger q = n.divide(p);
        System.out.println(q);
        System.out.println(p);
    }

    @Test
    public void testGivenPQDecrypt(){
        BigInteger p = new BigInteger("1500450271");
        BigInteger q = new BigInteger("9576890767");
        RSAImpl rsa = new RSAImpl(p,q,getE(RSAImpl.calculatePhi(p,q)));
        String[] enStr = {"14369648335605206820", "20971520000000", "80798284478113", "379749833583241", "182803912081669", "107213535210701", "194871710000000", "282621973446656", "34359738368", "24928547056768", "107213535210701", "93206534790699", "107213535210701", "140710042265625", "318547390056832", "107213535210701", "100000000000000", "435817657216", "34359738368", "37725479487783", "107213535210701", "34359738368", "182803912081669", "107213535210701", "107213535210701", "282621973446656", "34359738368", "80798284478113", "282621973446656", "34359738368", "10030613004288", "80798284478113", "182803912081669", "221068140740608", "140710042265625", "34359738368", "80798284478113", "282621973446656", "34359738368", "1338925209984", "2207984167552", "587068342272", "587068342272", "34359738368", "4902227890625", "16048523266853"};
        for (String str : enStr) {
            System.out.print(new String(rsa.decrypt(new BigInteger(str)).toByteArray()));
        }
    }
    private static BigInteger getE(BigInteger phi) {
        BigInteger e = new BigInteger("2");
        while (e.compareTo(phi) < 0) {
            if (RSAImpl.hcf(e, phi).equals(BigInteger.ONE)) {
                break;
            } else {
                e = e.add(BigInteger.ONE);
            }
        }
        return e;
    }
}