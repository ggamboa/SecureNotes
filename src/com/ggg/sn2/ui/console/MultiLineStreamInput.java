package com.ggg.sn2.ui.console;

import java.io.InputStream;

public class MultiLineStreamInput implements MultiLineInput {
	
	private int MAX_SIZE = 10 * 1024;
	private InputStream inputStream; 
	
	public MultiLineStreamInput(InputStream is) {
		inputStream = is;
	}
	
	public String getText() {
		
		String output = "";
		
		try {

			int inputByte = 0;
			int i = 0;
			byte[] buffer = new byte[MAX_SIZE];
			
			while(inputByte != -1) {
				inputByte = inputStream.read();
				if(inputByte != -1)	buffer[i++] = (byte) inputByte;
			}

			//calculate actual size of input
			int size = 0;
			for(int j=0; j < buffer.length; j++) {
				if(  buffer[j] == 00) { // 00 -> octal 0 or all bits is set to 0
					size = j;
					break;
				}
			}

			byte[] note = new byte[size];
			
			System.arraycopy(buffer, 0, note, 0, size);

			output = new String(note);
			
			//System.out.println("Text is: " + output);
			//System.out.println("Size is: " + size);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return output;
	}

}
