package com.ggg.sn2.security;

import java.security.MessageDigest;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class PassPhraseEncryptionUtility {
	
	private static final String ALGORITHM = "PBEWithMD5AndDES";
	private static final String DIGEST_ALGORITHM = "MD5";
	
	private final int ITERATIONS = 20;
	
	private String passPhrase;
	SecretKey key;
	
	public PassPhraseEncryptionUtility(String passPhrase) {
		this.passPhrase = passPhrase;
		
		try {
			KeySpec ks = new PBEKeySpec(passPhrase.toCharArray());
			SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGORITHM);
			key = skf.generateSecret(ks);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public byte[] encrypt(byte[] input) throws Exception {
		
		byte[] salt = new byte[8];
		
		MessageDigest md = MessageDigest.getInstance(DIGEST_ALGORITHM);
		md.update(passPhrase.getBytes());
		md.update(input);
		
		byte[] digest = md.digest();
		
		System.arraycopy(digest, 0, salt, 0, 8);
		
		AlgorithmParameterSpec aps = new PBEParameterSpec(salt, ITERATIONS);
		
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key, aps);
		
		byte[] encrypted = cipher.doFinal(input);
		byte[] output = new byte[encrypted.length + 8];
		System.arraycopy(salt, 0, output, 0, 8);
		System.arraycopy(encrypted, 0, output, 8, encrypted.length);
		
		return output;
		
	}
	
	public byte[] decrypt(byte[] input) throws Exception {
		
		byte[] salt = new byte[8];
		
		System.arraycopy(input, 0, salt, 0, 8);
		
		AlgorithmParameterSpec aps = new PBEParameterSpec(salt, ITERATIONS);
		
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key, aps);
		
		byte[] newInput = new byte[input.length - 8];
		System.arraycopy(input, 8, newInput, 0, input.length - 8);
		
		byte[] output = cipher.doFinal(newInput);
		
		return output;
		
	}

}
