package com.zs.security.symmetric.des;


import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class ImoocDES {
	/**
	 * DES加密算法
	 */
	//jdk方式实现des加密
	public static void jdkDES(String str){
		try {
			//生成KEY
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
			keyGenerator.init(56);
			SecretKey secreKey = keyGenerator.generateKey();
			byte[] bytesKey = secreKey.getEncoded();
			
			//KEY转换
			DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
			Key converSecreKey = factory.generateSecret(desKeySpec);
			
			//加密
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, converSecreKey);
			byte[] result = cipher.doFinal(str.getBytes());
			System.out.println("jdk des encrypt------------"+Hex.encodeHexString(result));
			
			//解密
			cipher.init(Cipher.DECRYPT_MODE, converSecreKey);
			result = cipher.doFinal(result);
			System.out.println("jsk des decrypt------------"+new String(result));
			
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
	}
	
	//用bouncy castle实现des加密
	public static void bcDES(String str){
			
		try {
			Security.addProvider(new BouncyCastleProvider());
			
			//生成KEY
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DES","BC");
			keyGenerator.init(56);
			SecretKey secreKey = keyGenerator.generateKey();
			byte[] bytesKey = secreKey.getEncoded();
			
			//KEY转换
			DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
			Key converSecreKey = factory.generateSecret(desKeySpec);
			
			//加密
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, converSecreKey);
			byte[] result = cipher.doFinal(str.getBytes());
			System.out.println("bc des encrypt------------"+Hex.encodeHexString(result));
			
			//解密
			cipher.init(Cipher.DECRYPT_MODE, converSecreKey);
			result = cipher.doFinal(result);
			System.out.println("bc des decrypt------------"+new String(result));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
			
	}
	
	
	/**
	 * 3DES加密算法
	 * @param args
	 */
	//jdk实现3DES加密算法
	public static void jdk3DES(String str){
		try {
			//生成KEY
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
			keyGenerator.init(168);
			//keyGenerator.init(new SecureRandom());
			SecretKey secreKey = keyGenerator.generateKey();
			byte[] bytesKey = secreKey.getEncoded();
			
			//KEY转换
			DESedeKeySpec desEdeKeySpec = new DESedeKeySpec(bytesKey);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
			Key converSecreKey = factory.generateSecret(desEdeKeySpec);
			
			//加密
			Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, converSecreKey);
			byte[] result = cipher.doFinal(str.getBytes());
			System.out.println("jdk 3des encrypt------------"+Hex.encodeHexString(result));
			
			//解密
			cipher.init(Cipher.DECRYPT_MODE, converSecreKey);
			result = cipher.doFinal(result);
			System.out.println("jsk 3des decrypt------------"+new String(result));
			
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
	}
	
	//用bouncy castle实现3DES加密算法
	public static void bc3DES(String str){
			try {
				//生成KEY
				KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede","BC");
				//keyGenerator.init(168);
				keyGenerator.init(new SecureRandom());
				SecretKey secreKey = keyGenerator.generateKey();
				byte[] bytesKey = secreKey.getEncoded();
				
				//KEY转换
				DESedeKeySpec desEdeKeySpec = new DESedeKeySpec(bytesKey);
				SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
				Key converSecreKey = factory.generateSecret(desEdeKeySpec);
				
				//加密
				Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
				cipher.init(Cipher.ENCRYPT_MODE, converSecreKey);
				byte[] result = cipher.doFinal(str.getBytes());
				System.out.println("bc 3des encrypt------------"+Hex.encodeHexString(result));
				
				//解密
				cipher.init(Cipher.DECRYPT_MODE, converSecreKey);
				result = cipher.doFinal(result);
				System.out.println("bc 3des decrypt------------"+new String(result));
			} catch (InvalidKeyException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (NoSuchProviderException e) {
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				e.printStackTrace();
			} catch (NoSuchPaddingException e) {
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				e.printStackTrace();
			} catch (BadPaddingException e) {
				e.printStackTrace();
			}
			
	}
	
	
	public static void main(String[] args) {
		jdkDES("imooc security des");
		bcDES("imooc security des");
		jdk3DES("imooc security des");
		bc3DES("imooc security des");
	}
}
