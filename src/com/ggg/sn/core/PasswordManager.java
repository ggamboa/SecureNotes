package com.ggg.sn.core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PasswordManager {
	
	private static final String FILE_NAME = "notes.data";
	
	public PasswordManager() {
		
	}

	public void saveToDisk(Notes n) throws IOException {
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
		os.writeObject(n);
		os.close();
	}
	
	public void dump() throws IOException, ClassNotFoundException {
		ObjectInputStream is = new ObjectInputStream(new FileInputStream(FILE_NAME));
		Notes notes = (Notes) is.readObject();
		is.close();
		System.out.println(notes);
	}
	
	public static void main(String[] args) {
		Notes notes = new Notes();
		notes.add(new Credential("gg2712", "chair"));
		notes.add(new Credential("ab1234", "table"));
		
		
		try {
			PasswordManager pm = new PasswordManager();
			pm.saveToDisk(notes);
			//pm.dump();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
