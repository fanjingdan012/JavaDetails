package crypt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import encode.EncodeUtil;
import hash.HashUtil;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
 

public class Symmetric {

	public static byte[] encryptBitAES(byte[] content, String password) {
		try {
			Cipher encryptCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			encryptCipher.init(Cipher.ENCRYPT_MODE, getAESKey(password));
			byte[] result = encryptCipher.doFinal(content);
			return result; // 加密
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		//return null;
	}
 

	public static byte[] decryptBitAES(byte[] content, String password) {
		try {
			Cipher decryptCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			decryptCipher.init(Cipher.DECRYPT_MODE, getAESKey(password));
			byte[] result = decryptCipher.doFinal(content);
			return result; // 加密结果
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		//return null;
	}
 

	public static String encryptAES(String content, String password) {
		return EncodeUtil.bytes2Hex(encryptBitAES(content.getBytes(), password));
	}
 

	public static String decryptAES(String content, String password) {
		return new String(decryptBitAES(EncodeUtil.hex2Bytes(content), password));
	}
 

	private static Key getAESKey(String password) throws NoSuchAlgorithmException {
		SecureRandom secureRandom = new SecureRandom(password.getBytes());
		// generate KEY
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128, secureRandom);
		SecretKey secretKey = kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		// convert KEY
		SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
		return key;
	}


	public static String buildJWT(String account,String key, int expireTime) {


		try {
			/**
			 * 1.create key
			 */
			MACSigner macSigner = new MACSigner(key);
			/**
			 * 2. build payload
			 */
			JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
							.subject("doi")
							.issuer("http://www.doiduoyi.com")
							.expirationTime(new Date(System.currentTimeMillis() + expireTime))
							.claim("ACCOUNT",account)
							.build();

			/**
			 * 3. build signature
			 */
			SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
			signedJWT.sign(macSigner);

			/**
			 * 4. generate token
			 */
			String token = signedJWT.serialize();
			return token;
		} catch (KeyLengthException e) {
			e.printStackTrace();
		} catch (JOSEException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String vaildToken(String token, String key ) {

		try {
			SignedJWT jwt = SignedJWT.parse(token);
			JWSVerifier verifier = new MACVerifier(key);
			if (!jwt.verify(verifier)) {
				throw new RuntimeException( "Token invalid");
			}

			Date expirationTime = jwt.getJWTClaimsSet().getExpirationTime();
			if (new Date().after(expirationTime)) {
				throw new RuntimeException( "Token expire");
			}

			Object account = jwt.getJWTClaimsSet().getClaim("ACCOUNT");
			//has openUid?
			if (Objects.isNull(account)){
				throw new RuntimeException("account empty");
			}
			return account.toString();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (JOSEException e) {
			e.printStackTrace();
		}
		return null;
	}

 
}

