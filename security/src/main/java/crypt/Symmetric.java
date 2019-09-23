package crypt;

import encode.EncodeUtil;
import hash.HashUtil;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
 
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
 

public class Symmetric {

	public static byte[] encryptBitAES(byte[] content, String password) {
		try {
			Cipher encryptCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// 创建密码器
			encryptCipher.init(Cipher.ENCRYPT_MODE, getAESKey(password));// 初始化
			byte[] result = encryptCipher.doFinal(content);
			return result; // 加密
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		//return null;
	}
 

	public static byte[] decryptBitAES(byte[] content, String password) {
		try {
			Cipher decryptCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// 创建密码器
			decryptCipher.init(Cipher.DECRYPT_MODE, getAESKey(password));// 初始化
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
		// 生成KEY
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128, secureRandom);
		SecretKey secretKey = kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		// 转换KEY
		SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
		return key;
	}

 
}

