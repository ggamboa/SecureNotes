package com.ggg.sn2.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;



public class KeyUtility {
	
	KeyGenerator keyGenerator;
	
	public KeyUtility() {
		try {
			keyGenerator = KeyGenerator.getInstance("DES");

		}
		catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
	public SecretKey generateKey() {
		
		keyGenerator.init(new SecureRandom());
		return keyGenerator.generateKey();
	}
	
	public static SecretKey getKey(byte[] input) throws Exception {
		
		SecretKeyFactory desFactory = SecretKeyFactory.getInstance("DES");
		KeySpec spec = new DESKeySpec(input);
		return desFactory.generateSecret(spec);
		
	}
	
	public static byte[] getBytesFromKey(SecretKey key) throws Exception {
		SecretKeyFactory desFactory = SecretKeyFactory.getInstance("DES");
		DESKeySpec spec = (DESKeySpec) desFactory.getKeySpec(key, DESKeySpec.class);
		return spec.getKey();
	}
	
	public static void main(String[] args) {
		//KeyUtility ku = new KeyUtility();
		//SecretKey key = ku.generateKey();
		//System.out.println("Key: " + key.getEncoded());
		try {
			
			String password = "gilbert6";
			byte[] buf = password.getBytes();
			System.out.println("Bytes: " + buf.length);
			
			SecretKey key = getKey(buf);
			System.out.println(key.getEncoded());

			//String sKey = new String(getBytesFromKey(key));
			//System.out.println(sKey);
			byte[] buf2 = getBytesFromKey(key);
			SecretKey key2 = getKey(buf2);
			System.out.println(key2.getEncoded());
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
