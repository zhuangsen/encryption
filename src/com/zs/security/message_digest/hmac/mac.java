package com.zs.security.message_digest.hmac;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;

public class mac {
	
	private static String src = "zs hmac";
	
	public static void main(String[] args) {
		jdkHmacMD5();
		bcHmacMD5();
	}

	
	public static void jdkHmacMD5(){
		try {
			
			//���������Կ
			//��ʼ��KeyGenerator
//			KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
			//������Կ
//			SecretKey secretKey = keyGenerator.generateKey();
			//�����Կ
//			byte[] key = secretKey.getEncoded();
			
			//�Զ�����Կ
			byte[] key = Hex.decodeHex(new char[]{'a','a','a','a','a','a','a','a','a','a'});
			
			//��ԭ��Կ
			SecretKey restoreSecretKey = new SecretKeySpec(key, "HmacMD5");
			//ʵ����Mac
			Mac mac = Mac.getInstance(restoreSecretKey.getAlgorithm());
			//��ʼ��Mac
			mac.init(restoreSecretKey);
			//ִ��ժҪ
			byte[] hmacMD5Bytes = mac.doFinal(src.getBytes());
			System.out.println("JDK hmacMD5:"+Hex.encodeHexString(hmacMD5Bytes));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (DecoderException e) {
			e.printStackTrace();
		}
	}
	
	public static void bcHmacMD5(){
		HMac hmac = new HMac(new MD5Digest());
		hmac.init(new KeyParameter(org.bouncycastle.util.encoders.Hex.decode("aaaaaaaaaa")));
		hmac.update(src.getBytes(),0,src.getBytes().length);
		
		byte[] hmacMD5Bytes = new byte[hmac.getMacSize()];//ִ��ժҪ
		hmac.doFinal(hmacMD5Bytes, 0);
		
		System.out.println("BC hmacMD5:"+Hex.encodeHexString(hmacMD5Bytes));
	}
}
