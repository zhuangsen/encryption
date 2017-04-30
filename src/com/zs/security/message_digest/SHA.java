package com.zs.security.message_digest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA224Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * 消息摘要算法：SHA(SHA-1、SHA-2、SHA-224、SHA-256、SHA-384、SHA-512)
 * 
 * @author madison
 *
 */
public class SHA {

	private static String src = "imooc security sha";

	public static void main(String[] args) {
		jdkSHA1();
		SHA224();
		bcSHA1();
		ccSHA1();
	}

	public static void jdkSHA1() {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			md.update(src.getBytes());
			System.out.println("JDK SHA1:" + Hex.encodeHexString(md.digest()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public static void bcSHA1() {
		Digest digest = new SHA1Digest();
		digest.update(src.getBytes(), 0, src.getBytes().length);
		byte[] sha1Bytes = new byte[digest.getDigestSize()];
		digest.doFinal(sha1Bytes, 0);
		System.out.println("BC SHA1:" + org.bouncycastle.util.encoders.Hex.toHexString(sha1Bytes));
	}

	public static void SHA224() {
		// JDK 实现
		try {
			Security.addProvider(new BouncyCastleProvider());
			MessageDigest md = MessageDigest.getInstance("SHA224");
			byte[] sha224Bytes = md.digest(src.getBytes());
			System.out.println("JDK SHA224:" + Hex.encodeHexString(sha224Bytes));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// Bouncy Castle实现
		Digest digest = new SHA224Digest();
		digest.update(src.getBytes(), 0, src.getBytes().length);
		byte[] sha224Bytes = new byte[digest.getDigestSize()];
		digest.doFinal(sha224Bytes, 0);
		System.out.println("BC SHA224:" + org.bouncycastle.util.encoders.Hex.toHexString(sha224Bytes));
	}

	public static void ccSHA1() {
		System.out.println("CC SHA1-1:" + DigestUtils.sha1Hex(src.getBytes()));
		System.out.println("CC SHA2-2:" + DigestUtils.sha1Hex(src));
	}

}
