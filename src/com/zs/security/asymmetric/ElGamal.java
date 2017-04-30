package com.zs.security.asymmetric;

import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.spec.DHParameterSpec;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * �ǶԳƼ����㷨:ElGamal(������ɢ����:��Bouncy Castle�ṩ����Կ���ܣ�˽Կ����)
 * 
 * @author madison
 *
 */
public class ElGamal {

	public static final String src = "Elgamal test";

	public static void main(String[] args) {
		jdkElgamal();
	}

	/**
	 * 
	 * ���ڣ���Illegal key size or default parameters���쳣������Ϊ�����ĳ������ƣ�Sunͨ��Ȩ���ļ�
	 * ��local_policy.jar��US_export_policy.jar�� ������Ӧ���ơ���˴���һЩ����:
	 * Java 6 �����������ļ���
	 * http://www.oracle.com/technetwork/java/javase/downloads/jce-6-download-429243.html
	 * Java 7 �����������ļ���
	 * http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html
	 *  �ҵ���java7���Լ���װ�ġ�
	 * /Library/Java/JavaVirtualMachines/jdk1.7.0_71.jdk/Contents/Home/jre/lib/securityĿ¼�£���Ӧ����local_policy.jar��US_export_policy.jar�����ļ���
	 * �л���%JDK_Home%\jre\lib\securityĿ¼�£���Ӧ����local_policy.jar��US_export_policy.jar�����ļ���
	 * ͬʱ��������б�Ҫ��%JRE_Home%\lib\securityĿ¼�£�Ҳ��Ҫ��Ӧ�����������ļ���
	 */

	// jdkʵ�֣���˽Կ���ܡ���Կ���ܡ� �� 
	//���ڣ���˽Կ���ܡ���Կ���ܡ������⣬��ΪElgamal��֧��
	public static void jdkElgamal() {
		try {
			// �����BouncyCastle֧��
			Security.addProvider(new BouncyCastleProvider());

			// 1.��ʼ�����ͷ���Կ
			AlgorithmParameterGenerator algorithmParameterGenerator = AlgorithmParameterGenerator
					.getInstance("Elgamal");
			// ��ʼ������������
			algorithmParameterGenerator.init(256);
			// �����㷨����
			AlgorithmParameters algorithmParameters = algorithmParameterGenerator.generateParameters();
			// ������������
			DHParameterSpec dhParameterSpec = (DHParameterSpec) algorithmParameters
					.getParameterSpec(DHParameterSpec.class);
			// ʵ������Կ������
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("Elgamal");
			// ��ʼ����Կ��������
			keyPairGenerator.initialize(dhParameterSpec, new SecureRandom());
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			// ��Կ
			PublicKey elGamalPublicKey = keyPair.getPublic();
			// ˽Կ
			PrivateKey elGamalPrivateKey = keyPair.getPrivate();
			System.out.println("Public Key:" + Base64.encodeBase64String(elGamalPublicKey.getEncoded()));
			System.out.println("Private Key:" + Base64.encodeBase64String(elGamalPrivateKey.getEncoded()));

			// 2.˽Կ���ܡ���Կ���� ---- ����
			// ��ʼ����Կ
			// ��Կ����ת��
			X509EncodedKeySpec x509EncodedKeySpec2 = new X509EncodedKeySpec(elGamalPublicKey.getEncoded());
			// ʵ������Կ����
			KeyFactory keyFactory2 = KeyFactory.getInstance("Elgamal");
			// ������Կ
			PublicKey publicKey2 = keyFactory2.generatePublic(x509EncodedKeySpec2);
			// ���ݼ���
			// Cipher cipher2 = Cipher.getInstance("Elgamal");
			Cipher cipher2 = Cipher.getInstance(keyFactory2.getAlgorithm());
			cipher2.init(Cipher.ENCRYPT_MODE, publicKey2);
			byte[] result2 = cipher2.doFinal(src.getBytes());
			System.out.println("˽Կ���ܡ���Կ���� ---- ����:" + Base64.encodeBase64String(result2));

			// 3.˽Կ���ܡ���Կ���� ---- ����
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec5 = new PKCS8EncodedKeySpec(elGamalPrivateKey.getEncoded());
			KeyFactory keyFactory5 = KeyFactory.getInstance("Elgamal");
			PrivateKey privateKey5 = keyFactory5.generatePrivate(pkcs8EncodedKeySpec5);
			// Cipher cipher5 = Cipher.getInstance("Elgamal");
			Cipher cipher5 = Cipher.getInstance(keyFactory5.getAlgorithm());
			cipher5.init(Cipher.DECRYPT_MODE, privateKey5);
			byte[] result5 = cipher5.doFinal(result2);
			System.out.println("Elgamal ˽Կ���ܡ���Կ���� ---- ����:" + new String(result5));

			// // ˽Կ���ܡ���Կ����: ������
			// // 4.˽Կ���ܡ���Կ���� ---- ����
			// PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new
			// PKCS8EncodedKeySpec(elGamalPrivateKey.getEncoded());
			// KeyFactory keyFactory = KeyFactory.getInstance("Elgamal");
			// PrivateKey privateKey =
			// keyFactory.generatePrivate(pkcs8EncodedKeySpec);
			// Cipher cipher = Cipher.getInstance("Elgamal");
			// cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			// byte[] result = cipher.doFinal(src.getBytes());
			// System.out.println("˽Կ���ܡ���Կ���� ---- ����:" +
			// Base64.encodeBase64String(result));
			//
			// // 5.˽Կ���ܡ���Կ���� ---- ����
			// X509EncodedKeySpec x509EncodedKeySpec = new
			// X509EncodedKeySpec(elGamalPublicKey.getEncoded());
			// keyFactory = KeyFactory.getInstance("Elgamal");
			// PublicKey publicKey =
			// keyFactory.generatePublic(x509EncodedKeySpec);
			// cipher = Cipher.getInstance("Elgamal");
			// cipher.init(Cipher.DECRYPT_MODE, publicKey);
			// result = cipher.doFinal(result);
			// System.out.println("Elgamal ˽Կ���ܡ���Կ���� ---- ����:" + new
			// String(result));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
