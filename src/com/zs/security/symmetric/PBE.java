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
 * �ԳƼ����㷨: PBE(Password Based Encryption):�������ϢժҪ�㷨�ͶԳƼ����㷨���ŵ㣬���ڿ������
 * 
 * @author madison
 *
 */
public class PBE {

	public static void main(String[] args) {
		jdkPBE("security des");
	}

	// jdkʵ��pbe�㷨����
	public static void jdkPBE(String str) {

		try {
			// ��ʼ����
			SecureRandom random = new SecureRandom();
			byte[] salt = random.generateSeed(8);

			// ��������Կ
			String password = "madison";
			PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");
			Key key = factory.generateSecret(pbeKeySpec);

			// ����
			PBEParameterSpec pbeParameterSac = new PBEParameterSpec(salt, 100);
			Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
			cipher.init(Cipher.ENCRYPT_MODE, key, pbeParameterSac);
			byte[] result = cipher.doFinal(str.getBytes());
			System.out.println("jdk pbe encrypt---------" + Hex.encodeHexString(result));

			// ����
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
