package com.ggg.sn.core;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class HashCodeUtil {
	
	public static final String C_SHA_1 = "SHA-1"; // Secure Hash Algorithm 1
	public static char[] C_DIGITS = {'0', '1', '2', '3', '4','5','6','7','8','9','a','b','c','d','e','f'};
	
	public HashCodeUtil() {
		
	}
	
	
	private static byte[] hash(String s) {
		
		byte[] bytes = null;
		try {
			MessageDigest sha = MessageDigest.getInstance(C_SHA_1);
			bytes = sha.digest(s.getBytes());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}
	
	
	private static String hexEncode(byte[] bytes) {
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i < bytes.length; i++) {
			byte b = bytes[i];
			sb.append( C_DIGITS[ (b&0xf0) >> 4 ] );
		    sb.append( C_DIGITS[ b&0x0f ] );
			
		}
		return sb.toString();
	}
	
	/**
	 * Get message digest in String format as hex values
	 * @param s
	 * @return String
	 */
	public static String getHash(String s) {
		return hexEncode(hash(s));
	}
	
	private static void log(Object o) {
		System.out.println(String.valueOf(o));
	}
	
	public static void main(String[] args) {
		String s = getHash("gilbert");
		log(s);
		
		s = getHash("gilbert");
		log(s);
		
		s = getHash("gilbertronnetteiyelpaeng01234567890");
		log(s);
	}




}