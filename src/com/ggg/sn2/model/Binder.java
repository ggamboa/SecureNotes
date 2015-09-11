package com.ggg.sn2.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ggg.sn2.security.PassPhraseEncryptionUtility;


public class Binder {
	
	private final int INITIAL_SIZE = 50;
	private final String fileName;
	private String pp;
	
	private List<Note> notes;
	
	private Binder(String fileName) {
		this.fileName = fileName;
		notes = new ArrayList<Note>(INITIAL_SIZE);
	}
	
	public int size() {
		return notes.size();
	}
	
	public void addNote(Note note) {
		notes.add(note);
	}
	
	public void deleteNote(int index) {
		notes.remove(index);
	}
	
	public List<Note> getNotes() {
		return notes;
	}
	
	public void updateNote(int index, Note note) throws Exception {
		if (index > this.notes.size()) {
			throw new Exception("Index out of bounds.");
		}
		this.notes.set(index, note);
	}
			
	public Note getNote(int idx) {
		Note note = null;
		
		if (idx < notes.size()) {
			note = notes.get(idx);
		}
		return note;
	}
	

	/**
	 * Load existing Binder object from a file. 
	 * @param fileName, passPhrase
	 * @return
	 */
	public static Binder getInstance(String fileName, String passPhrase) throws FileNotFoundException, BinderInstantiationException {
		
		PassPhraseEncryptionUtility ppeu = new PassPhraseEncryptionUtility(passPhrase);
		
		Binder b = null;
		
		try {
			byte[] buffer = getBytesFromFile(fileName);
			b = load(ppeu.decrypt(buffer));
			b.pp = passPhrase;
		}
		catch(FileNotFoundException fe) {
			System.out.println("File " + fileName + " not found! Use createNewInstance() instead.");
			throw fe;
		}
		catch(Exception e) {
			throw new BinderInstantiationException("Error loading or creating Binder.", e);
		}
		
		return b;
	}
	
	/**
	 * Create a new Binder object from a file. 
	 * @param fileName, passPhrase
	 * @return
	 */
	public static Binder createNewInstance(String fileName, String passPhrase) throws BinderInstantiationException {
		Binder b = new Binder(fileName);
		b.pp = passPhrase;
		return b;
	}
	
	
	private static byte[] getBytesFromFile(String fileName) throws FileNotFoundException, IOException {
		
		DataInputStream dis = new DataInputStream(new FileInputStream(fileName));

		byte[] buffer = new byte[dis.available()];
		dis.readFully(buffer);
		dis.close();
		
		return buffer;
	}
	
	private static Binder load(byte[] buffer) throws IOException {
		
		Binder b;
		
		try {

			DataInputStream dis = new DataInputStream(new ByteArrayInputStream(buffer));
			
			String fileName = dis.readUTF();

			b = new Binder(fileName);
			
			int numberOfNotes = dis.readInt();
	
			String title;
			String body;
	
			for(int i=0; i < numberOfNotes; i++) {
				title = dis.readUTF();
				body = dis.readUTF();
				b.addNote(new Note(title, body));
			}
			
			dis.close();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
			throw ioe;
		}
		
		return b;
	}
	
	public void save() {
		
		PassPhraseEncryptionUtility ppeu = new PassPhraseEncryptionUtility(this.pp);

		try {
			
			byte[] encryptedBytes = ppeu.encrypt(toByteArray());
			write(encryptedBytes);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private byte[] toByteArray() {
		
		byte[] buffer = new byte[1];
		
		try{
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(baos);
			
			dos.writeUTF(fileName);
			
			// write the number of notes
			dos.writeInt(size());
			
			for(Note note : notes) {
				dos.writeUTF(note.getTitle());
				dos.writeUTF(note.getBody());
			}
			
			dos.close();
			buffer = baos.toByteArray();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return buffer;
	}
	
	private void write(byte[] buffer) {
		
		try{
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName));
			dos.write(buffer);
			dos.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
