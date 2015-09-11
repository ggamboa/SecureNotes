package com.ggg.sn2.ui.console;

import java.io.InputStreamReader;

public class MultiLineReader implements MultiLineInput {

	private InputStreamReader reader;
	
	public MultiLineReader()  {
		reader = new InputStreamReader(System.in);
	}

	@Override
	public String getText() {
		
		StringBuilder sb = new StringBuilder();
		String output = "";
		
		char[] cbufAll = new char[1024 * 10];
		char[] cbuf = new char[1024];
		
		int ctr = 0;
		int nextPos = 0;
		
		try {
			while (true) {
				
				ctr = reader.read(cbuf);
				System.arraycopy(cbuf, 0, cbufAll, nextPos, ctr);
				
				if(shouldTerminate(cbuf)) {
					output = new String(cbufAll, 0, nextPos);
					break;
				}
				
				// we don't want to include the last line which is "*done"
				nextPos = nextPos + ctr;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return output;
	}
	
	private boolean shouldTerminate(char[] cbuf) {
		boolean b = false;
		
		if	(	cbuf[0] == '*' 
				&& 	(cbuf[1] == 'D' || cbuf[1] == 'd') 
				&& 	(cbuf[2] == 'O' || cbuf[2] == 'o') 
				&& 	(cbuf[3] == 'N' || cbuf[3] == 'n') 
				&& 	(cbuf[4] == 'E' || cbuf[4] == 'e') 
			) {
			
			b = true;
		}
					
		return b; 
	}

	public static void main(String[] args) {
		
		MultiLineInput in = new MultiLineReader();
		String s = in.getText();
		System.out.println(s);
		
		
	}
}
