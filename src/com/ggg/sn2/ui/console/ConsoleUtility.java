package com.ggg.sn2.ui.console;

import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.ggg.sn2.model.Binder;
import com.ggg.sn2.model.Note;

public class ConsoleUtility {

	private static final int DEFAULT_SIZE = 1024;
	public static InputStreamReader reader = new InputStreamReader(System.in);
	
	public ConsoleUtility() {
	}

	public static boolean confirmAction(String prompt) throws IOException {
		
		InputStreamReader isr = new InputStreamReader(System.in);
		boolean bool = false;
		char[] cbuf = new char[DEFAULT_SIZE];
				
		isr = new InputStreamReader(System.in);
		
		while(true) {
		
			System.out.print(prompt + "[y/n] ");
			isr.read(cbuf);
			
			if(cbuf[0] == 'Y' || cbuf[0] == 'y') {
				bool = true;
				break;
			}
			else if (cbuf[0] == 'N' || cbuf[0] == 'n') {
				bool = false;
				break;
			}
		}
		return bool;
	}
	
	public static List<String> breakUpIntoLines(String content) {
		
		List<String> list = new ArrayList<String>(100);
		
		char[] cbuf = content.toCharArray();
		
		int pos=0;
		int len=0;
		
		for(int i=0; i < cbuf.length; i++) {
			
			
			if(cbuf[i] == Constants.NEW_LINE || cbuf[i] == Constants.CARRIAGE_RETURN) {

				if (((i + 1) < cbuf.length) && (cbuf[i+1] == Constants.NEW_LINE || cbuf[i+1] == Constants.CARRIAGE_RETURN))  {
					continue;
				}

				len = i - pos -1;
				
				String line = new String(cbuf, pos, len);
				list.add(line);
				pos = i + 1;
			}
			if (i == cbuf.length - 1) {
				if(i >= pos) {
					String line = new String(cbuf, pos, i - pos + 1);
					list.add(line);
				}
			}
		}

		return list;
	}
	
	public static String promptForLineInputOrig(String prompt) throws IOException {
		
		char[] cbuf = new char[DEFAULT_SIZE];
		System.out.print(prompt);
		int len = reader.read(cbuf);
		return new String(cbuf, 0, len);
	}
	
	public static String promptForLineInput(String prompt) throws IOException {
		
		Console console = System.console();
		return console.readLine("%s",  prompt);
	}
	
	public static String promptForPassword(String prompt) throws IOException {
		
		Console console = System.console();
		char[] cbuf = console.readPassword("%s", prompt);
		return new String(cbuf);
	}
	
	public static void display(String s) {
		System.out.print(s);
	}
	
	public static void displayLine(String s) {
		System.out.println(s);
	}
	
	public static void displayEmptyLine() {
		System.out.println("");
	}
	
	public static void displayDivider(char c, int length) {
		char[] cbuf = new char[length];
		for (int i=0; i < length; i++) {
			cbuf[i] = c;
		}
		System.out.println(new String(cbuf));
	}
	
	public static String getLine(String prompt) throws IOException  {
		char[] cbuf = new char[DEFAULT_SIZE];
		System.out.print(prompt);
		int len = reader.read(cbuf);
		
		// Strip new line and/or carriage return character from the title if present - both may be present so do twice
		if((len > 0) && ( (cbuf[len-1] == Constants.NEW_LINE) || (cbuf[len-1] == Constants.CARRIAGE_RETURN) )) --len;
		if((len > 0) && ( (cbuf[len-1] == Constants.NEW_LINE) || (cbuf[len-1] == Constants.CARRIAGE_RETURN) )) --len;
					
		return new String(cbuf, 0, len);
	}
	
	public static String getLine() throws IOException {
		return getLine(">");
	}
	
	public static List<String> getLines(String prompt) {
		List<String> list = new ArrayList<String>(DEFAULT_SIZE);
		
		return list;
	}
	
	public static void main(String[] args) {
		
		try {
			/**
			Binder b = Binder.getInstance("C:\\Users\\gg2712\\workspaces-luna-gg\\Expense-R\\SecureNotes\\etc\\binder.dat", "ang huling el bimbo");
			Note note = b.getNote(9);
			List<String> list = ConsoleUtility.breakUpIntoLines(note.getBody());
			for(String s : list) {
				System.out.print(s);
			}*/
			
			String s1 = getLine();
			String s2 = getLine();
			
			display(s1 + s2);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
