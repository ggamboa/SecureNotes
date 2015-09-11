package com.ggg.io.samples;

public class PrintStringBytes {
	
	public static void main(String[] args) {
		
		
		
		int i = 1;
		String s = "3308 Longmeadow  Pl DUblin CA 94568";
		
		byte[] bytes = s.getBytes();
		
		System.out.write(i);
		System.out.write('\n');

		try{
			System.out.write(bytes);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
