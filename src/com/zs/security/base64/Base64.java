package com.zs.security.base64;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64 {
	
	private static String src = "imooc security base64";
	
	public static void main(String[] args){
		jdkBase64();
		commonsCodeBase64();
		bouncyCastleBase64();
	}
	
	/**
	 * 使用jdk内部的base64加密（因此方法没公开顾不能直接使用，解决方法：
	 * Java Bulid Path-> Add Access Rule->Accessible->**）
	 */
	public static void jdkBase64(){
		BASE64Encoder encoder = new BASE64Encoder();
		String encode = encoder.encode(src.getBytes());
		System.out.println("jdkBase64 encode:"+encode);
		
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			System.out.println("jdkBase64 decode:"+new String(decoder.decodeBuffer(encode)));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void commonsCodeBase64(){
		byte[] encodeBytes = org.apache.commons.codec.binary.Base64.encodeBase64(src.getBytes());
		System.out.println("commonsCodeBase64 encode:"+new String(encodeBytes));
		
		byte[] decodeBytes = org.apache.commons.codec.binary.Base64.decodeBase64(encodeBytes);
		System.out.println("commonsCodeBase64 decode:"+new String(decodeBytes));
	}
	
	public static void bouncyCastleBase64(){
		byte[] encodeBytes = org.bouncycastle.util.encoders.Base64.encode(src.getBytes());
		System.out.println("bouncyCastleBase64 encode:"+new String(encodeBytes));
		
		byte[] decodeBytes = org.bouncycastle.util.encoders.Base64.decode(encodeBytes);
		System.out.println("bouncyCastleBase64 decode:"+new String(decodeBytes));
	}

}
