package com.zs.security.asymmetric;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;

import org.apache.commons.codec.binary.Base64;

/**
 * �ǶԳƼ����㷨��DH����Կ�����㷨��
 * 
 * @author madison
 *
 */
public class DH {

	private static String src = "security";

	public static void main(String[] args) {
		jdkDH();
	}

	public static void jdkDH() {
		try {
			// 1.��ʼ��������Կ
			KeyPairGenerator senderKeyPairGenerator = KeyPairGenerator.getInstance("DH");
			senderKeyPairGenerator.initialize(512);
			KeyPair senderKeyPair = senderKeyPairGenerator.generateKeyPair();
			// ���ͷ���Կ�����͸����շ������硢�ļ���������
			byte[] senderPublicKeyEnc = senderKeyPair.getPublic().getEncoded();

			// 2.��ʼ�����շ���Կ
			KeyFactory receiverKeyFactory = KeyFactory.getInstance("DH");
			X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(senderPublicKeyEnc);
			PublicKey receiverPublicKey = receiverKeyFactory.generatePublic(x509EncodedKeySpec);
			DHParameterSpec dhParameterSpec = ((DHPublicKey) receiverPublicKey).getParams();
			KeyPairGenerator receiverKeyPairGenerator = KeyPairGenerator.getInstance("DH");
			receiverKeyPairGenerator.initialize(dhParameterSpec);
			KeyPair receiverKeyPair = receiverKeyPairGenerator.generateKeyPair();
			PrivateKey receiverPrivateKey = receiverKeyPair.getPrivate();
			byte[] receiverPublicKeyEnc = receiverKeyPair.getPublic().getEncoded();

			// 3.��Կ����
			KeyAgreement receiverKeyAgreement = KeyAgreement.getInstance("DH");
			receiverKeyAgreement.init(receiverPrivateKey);
			receiverKeyAgreement.doPhase(receiverPublicKey, true);
			SecretKey receiverDesKey = receiverKeyAgreement.generateSecret("DES");

			KeyFactory senderKeyFactory = KeyFactory.getInstance("DH");
			x509EncodedKeySpec = new X509EncodedKeySpec(receiverPublicKeyEnc);
			PublicKey senderPublicKey = senderKeyFactory.generatePublic(x509EncodedKeySpec);
			KeyAgreement senderKeyAgreement = KeyAgreement.getInstance("DH");
			senderKeyAgreement.init(senderKeyPair.getPrivate());
			senderKeyAgreement.doPhase(senderPublicKey, true);
			SecretKey senderDesKey = senderKeyAgreement.generateSecret("DES");

			if (Objects.equals(receiverDesKey, senderDesKey)) {
				System.out.println("˫����Կ��ͬ");
			}

			// ����
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, senderDesKey);
			byte[] result = cipher.doFinal(src.getBytes());
			System.out.println("JDK DH encrypt:" + Base64.encodeBase64String(result));

			// ����
			cipher.init(Cipher.DECRYPT_MODE, receiverDesKey);
			result = cipher.doFinal(result);
			System.out.println("JDK DH decrypt:" + new String(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
