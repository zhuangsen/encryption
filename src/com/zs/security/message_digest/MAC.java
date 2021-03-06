//package com.zs.security.message_digest;
//
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//
//import javax.crypto.Mac;
//import javax.crypto.SecretKey;
//import javax.crypto.spec.SecretKeySpec;
//
//import org.apache.commons.codec.DecoderException;
//import org.apache.commons.codec.binary.Hex;
//import org.bouncycastle.crypto.digests.MD5Digest;
//import org.bouncycastle.crypto.macs.HMac;
//import org.bouncycastle.crypto.params.KeyParameter;
//
///**
// * 消息摘要算法：MAC(Message Authentication Code)
// * 
// * @author madison
// *
// */
//public class MAC {
//
//	private static String src = "zs hmac";
//
//	public static void main(String[] args) {
//		jdkHmacMD5();
//		bcHmacMD5();
//	}
//
//	public static void jdkHmacMD5() {
//		try {
//
//			// 随机产生密钥
//			// 初始化KeyGenerator
//			// KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
//			// 产生密钥
//			// SecretKey secretKey = keyGenerator.generateKey();
//			// 获得密钥
//			// byte[] key = secretKey.getEncoded();
//
//			// 自定义密钥
//			byte[] key = Hex.decodeHex(new char[] { 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a', 'a' });
//
//			// 还原密钥
//			SecretKey restoreSecretKey = new SecretKeySpec(key, "HmacMD5");
//			// 实例化Mac
//			Mac mac = Mac.getInstance(restoreSecretKey.getAlgorithm());
//			// 初始化Mac
//			mac.init(restoreSecretKey);
//			// 执行摘要
//			byte[] hmacMD5Bytes = mac.doFinal(src.getBytes());
//			System.out.println("JDK hmacMD5:" + Hex.encodeHexString(hmacMD5Bytes));
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (InvalidKeyException e) {
//			e.printStackTrace();
//		} catch (DecoderException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void bcHmacMD5() {
//		HMac hmac = new HMac(new MD5Digest());
//		hmac.init(new KeyParameter(org.bouncycastle.util.encoders.Hex.decode("aaaaaaaaaa")));
//		hmac.update(src.getBytes(), 0, src.getBytes().length);
//
//		byte[] hmacMD5Bytes = new byte[hmac.getMacSize()];// 执行摘要
//		hmac.doFinal(hmacMD5Bytes, 0);
//
//		System.out.println("BC hmacMD5:" + Hex.encodeHexString(hmacMD5Bytes));
//	}
//}
