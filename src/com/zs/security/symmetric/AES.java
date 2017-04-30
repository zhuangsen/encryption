package com.zs.security.symmetric;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * 对称加密算法:AES(是目前使用最多的对称加密算法，至今尚未被破解，通常用于移动通信系统加密以及基于SSH协议的软件,DES替代者)
 * 
 * @author madison
 *
 */
public class AES {
	
	public static void main(String[] args) {
		jdkAES("imooc security aes");
		bcAES("imooc security aes");
	}

	// 用jdk实现AES加密
	public static void jdkAES(String str) {
		try {
			// 生成KEY
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] keyBytes = secretKey.getEncoded();

			// key转换
			Key key = new SecretKeySpec(keyBytes, "AES");

			// 加密
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] result = cipher.doFinal(str.getBytes());
			System.out.println("jdk aes encrypt------" + Base64.encodeBase64String(result));

			// 解密
			cipher.init(Cipher.DECRYPT_MODE, key);
			result = cipher.doFinal(result);
			System.out.println("jdk aes decrypt------" + new String(result));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
	}

	// 用bouncy castle实现AES加密
	public static void bcAES(String str) {
		try {

			Security.addProvider(new BouncyCastleProvider());

			// 生成KEY
			KeyGenerator keyGenerator;
			keyGenerator = KeyGenerator.getInstance("AES", "BC");
			keyGenerator.getProvider();
			keyGenerator.init(128);
			// 产生密钥
			SecretKey secretKey = keyGenerator.generateKey();
			// 获取密钥
			byte[] keyBytes = secretKey.getEncoded();

			// key转换
			Key key = new SecretKeySpec(keyBytes, "AES");

			// 加密
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] result = cipher.doFinal(str.getBytes());
			System.out.println("bc aes encrypt------" + Base64.encodeBase64String(result));

			// 解密
			cipher.init(Cipher.DECRYPT_MODE, key);
			result = cipher.doFinal(result);
			System.out.println("bc aes decrypt------" + new String(result));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
	}

}
