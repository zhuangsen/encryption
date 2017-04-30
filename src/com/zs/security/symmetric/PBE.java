package com.zs.security.symmetric;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.codec.binary.Hex;

/**
 * 对称加密算法: PBE(Password Based Encryption):结合了消息摘要算法和对称加密算法的优点，基于口令加密
 * 
 * @author madison
 *
 */
public class PBE {

	public static void main(String[] args) {
		jdkPBE("security des");
	}

	// jdk实现pbe算法加密
	public static void jdkPBE(String str) {

		try {
			// 初始化盐
			SecureRandom random = new SecureRandom();
			byte[] salt = random.generateSeed(8);

			// 口令与密钥
			String password = "madison";
			PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");
			Key key = factory.generateSecret(pbeKeySpec);

			// 加密
			PBEParameterSpec pbeParameterSac = new PBEParameterSpec(salt, 100);
			Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
			cipher.init(Cipher.ENCRYPT_MODE, key, pbeParameterSac);
			byte[] result = cipher.doFinal(str.getBytes());
			System.out.println("jdk pbe encrypt---------" + Hex.encodeHexString(result));

			// 解密
			cipher.init(Cipher.DECRYPT_MODE, key, pbeParameterSac);
			result = cipher.doFinal(result);
			System.out.println("jdk pbe decrypt----------" + new String(result));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
	}
}
