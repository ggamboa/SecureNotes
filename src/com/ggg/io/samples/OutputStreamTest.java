package com.ggg.io.samples;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class OutputStreamTest {
	
	public static void main(String[] args) {
		
		try{
			OutputStream fos = new BufferedOutputStream(new FileOutputStream("etc/fos.txt"));
			int i = 1;
			String s = "3308 Longmeadow  Pl Dublin CA 94568";

			fos.write(i); // will write the 8 low-order bits (1 byte)
			fos.write(s.getBytes());
			fos.close();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
