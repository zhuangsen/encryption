package com.zs.security.asymmetric.elgamal;

import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.DHParameterSpec;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
/**
 * �ǶԳƼ����㷨ElGamal�㷨���
 * �ǶԳ��㷨һ�����������ͶԳƼ����㷨����Կ��ʹ�õġ������RSA�㷨������㷨ֻ֧��˽Կ���ܹ�Կ����
 * @author kongqz
 * */
public class ElGamalTest {
	//�ǶԳ���Կ�㷨
	public static final String KEY_ALGORITHM="ElGamal";
	
	
	/**
	 * ��Կ���ȣ�DH�㷨��Ĭ����Կ������1024
	 * ��Կ���ȱ�����8�ı�������160��16384λ֮��
	 * */
	private static final int KEY_SIZE=256;
	//��Կ
	private static final String PUBLIC_KEY="ElGamalPublicKey";
	
	//˽Կ
	private static final String PRIVATE_KEY="ElGamalPrivateKey";
	
	/**
	 * ��ʼ����Կ��
	 * @return Map �׷���Կ��Map
	 * */
	public static Map<String,Object> initKey() throws Exception{
		//�����BouncyCastle֧��
		Security.addProvider(new BouncyCastleProvider());
		AlgorithmParameterGenerator apg=AlgorithmParameterGenerator.getInstance(KEY_ALGORITHM);
		//��ʼ������������
		apg.init(KEY_SIZE);
		//�����㷨����
		AlgorithmParameters params=apg.generateParameters();
		//������������
		DHParameterSpec elParams=(DHParameterSpec)params.getParameterSpec(DHParameterSpec.class);
		
		//ʵ������Կ������
		KeyPairGenerator kpg=KeyPairGenerator.getInstance(KEY_ALGORITHM) ;
		
		//��ʼ����Կ��������
		kpg.initialize(elParams,new SecureRandom());
		
		KeyPair keyPair=kpg.generateKeyPair();
		//�׷���Կ
		PublicKey publicKey= keyPair.getPublic();
		//�׷�˽Կ
		PrivateKey privateKey= keyPair.getPrivate();
		//����Կ�洢��map��
		Map<String,Object> keyMap=new HashMap<String,Object>();
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
		
	}
	
	
	/**
	 * ��Կ����
	 * @param data����������
	 * @param key ��Կ
	 * @return byte[] ��������
	 * */
	public static byte[] encryptByPublicKey(byte[] data,byte[] key) throws Exception{
		
		//ʵ������Կ����
		KeyFactory keyFactory=KeyFactory.getInstance(KEY_ALGORITHM);
		//��ʼ����Կ
		//��Կ����ת��
		X509EncodedKeySpec x509KeySpec=new X509EncodedKeySpec(key);
		//������Կ
		PublicKey pubKey=keyFactory.generatePublic(x509KeySpec);
		
		//���ݼ���
		Cipher cipher=Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		return cipher.doFinal(data);
	}
	/**
	 * ˽Կ����
	 * @param data ����������
	 * @param key ��Կ
	 * @return byte[] ��������
	 * */
	public static byte[] decryptByPrivateKey(byte[] data,byte[] key) throws Exception{
		//ȡ��˽Կ
		PKCS8EncodedKeySpec pkcs8KeySpec=new PKCS8EncodedKeySpec(key);
		KeyFactory keyFactory=KeyFactory.getInstance(KEY_ALGORITHM);
		//����˽Կ
		PrivateKey privateKey=keyFactory.generatePrivate(pkcs8KeySpec);
		//���ݽ���
		Cipher cipher=Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}
	
	/**
	 * ȡ��˽Կ
	 * @param keyMap ��Կmap
	 * @return byte[] ˽Կ
	 * */
	public static byte[] getPrivateKey(Map<String,Object> keyMap){
		Key key=(Key)keyMap.get(PRIVATE_KEY);
		return key.getEncoded();
	}
	/**
	 * ȡ�ù�Կ
	 * @param keyMap ��Կmap
	 * @return byte[] ��Կ
	 * */
	public static byte[] getPublicKey(Map<String,Object> keyMap) throws Exception{
		Key key=(Key) keyMap.get(PUBLIC_KEY);
		return key.getEncoded();
	}
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		//��ʼ����Կ
		//������Կ��
		Map<String,Object> keyMap=ElGamalTest.initKey();
		//��Կ
		byte[] publicKey=ElGamalTest.getPublicKey(keyMap);
		
		//˽Կ
		byte[] privateKey=ElGamalTest.getPrivateKey(keyMap);
		System.out.println("��Կ��/n"+Base64.encodeBase64String(publicKey));
		System.out.println("˽Կ��/n"+Base64.encodeBase64String(privateKey));
		
		System.out.println("================��Կ�Թ������,�׷�����Կ�������ҷ�����ʼ���м������ݵĴ���=============");
		String str="ElGamal���뽻���㷨";
		System.out.println("/n===========�׷����ҷ����ͼ�������==============");
		System.out.println("ԭ��:"+str);
		
		//�ҷ�ʹ�ù�Կ�����ݽ��м���
		byte[] code2=ElGamalTest.encryptByPublicKey(str.getBytes(), publicKey);
		System.out.println("===========�ҷ�ʹ�ù�Կ�����ݽ��м���==============");
		System.out.println("���ܺ�����ݣ�"+Base64.encodeBase64String(code2));
		
		
		//�׷�ʹ��˽Կ�����ݽ��н���
		byte[] decode2=ElGamalTest.decryptByPrivateKey(code2, privateKey);
		
		System.out.println("�׷����ܺ�����ݣ�"+new String(decode2));
	}
}


