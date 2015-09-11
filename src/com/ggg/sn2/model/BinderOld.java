package com.ggg.sn2.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class BinderOld {
	
	private String name;
	private List<Note> notes;
	
	public BinderOld(String name) {
		this.name = name;
		notes = new ArrayList<Note>(100);
	}
	
	public void addNote(Note note) {
		notes.add(note);
	}
	
	public List<Note> getNotes() {
		return notes;
	}
	
	public List<String> getNoteTitles() {
		List<String> list = new ArrayList<String>(100);
		for(Note note : notes) {
			list.add(note.getTitle());
		}
		return list;
	}
	
	public int getSize() {
		return notes.size();
	}
	
	public byte[] toByteArray() {
		
		byte[] buffer = new byte[1];
		try{
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(baos);
			
			List<Note> notes = this.getNotes();

			dos.writeInt(this.getSize());
			
			for(Note note : notes) {
				
				dos.writeUTF(note.getTitle());
				dos.writeUTF(note.getBody());
				
			}
			
			
			dos.close();
			
			System.out.println(baos.toByteArray().length);
			
			buffer = baos.toByteArray();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return buffer;
	}

	public void save(byte[] buffer) {
		
		try{
			
			DataOutputStream dos = new DataOutputStream(new FileOutputStream("etc/binderFromByteArray.dat"));
			
			dos.write(buffer);
			
			dos.close();
			
			System.out.println("\nCreated data file binderFromByteArray.dat!");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void save(DataOutputStream dos) {
		
		try{
			
			List<Note> notes = this.getNotes();

			dos.writeInt(this.getSize());
			
			for(Note note : notes) {
				dos.writeUTF(note.getTitle());
				dos.writeUTF(note.getBody());
			}
			
			dos.close();
			
			System.out.println("\nCreated data file binder.dat!");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void save(String fileName) {
		
		try{
			
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName));
			
			List<Note> notes = this.getNotes();

			dos.writeInt(this.getSize());
			
			for(Note note : notes) {
				dos.writeUTF(note.getTitle());
				dos.writeUTF(note.getBody());
			}
			
			dos.close();
			
			System.out.println("\nCreated data file " + fileName + "!");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void load(byte[] buffer) {
		
		try {
			DataInputStream dis = new DataInputStream(new ByteArrayInputStream(buffer));
			load(dis);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void load(String fileName) {
		
		try {
			DataInputStream dis = new DataInputStream(new FileInputStream(fileName));
			load(dis);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void load() {
		
		try {
			DataInputStream dis = new DataInputStream(new FileInputStream("etc/binderFromByteArray.dat"));
			
			String title;
			String body;
			
			int numberOfNotes = dis.readInt();
			
			System.out.println("Number of notes: " + numberOfNotes);
			
			for(int i=0; i < numberOfNotes; i++) {
				title = dis.readUTF();
				body = dis.readUTF();
				
				System.out.println(title);
				System.out.println(body);
			}
			
			dis.close();
			
			System.out.println("Done.");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void load(DataInputStream dis) {
		
		try {

			String title;
			String body;
			
			int numberOfNotes = dis.readInt();
			
			System.out.println("Number of notes: " + numberOfNotes);
			
			for(int i=0; i < numberOfNotes; i++) {
				title = dis.readUTF();
				body = dis.readUTF();
				
				System.out.println(title);
				System.out.println(body);
			}
			
			dis.close();
			
			System.out.println("Done.");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return "Binder [name=" + name + ", notes=" + notes + "]";
	}

	public static void main(String[] args) {
		
		BinderOld b = new BinderOld("My Notes");
		
		//Note note1 = new Note("Citibank Mastercard");
		//note1.setBody("userid: gilbert\npassword: cantguessthis123\nacctnum: 1234567890");
		
		//Note note2 = new Note("Wifi Router");
		//note2.setBody("passcode: 53987593475");
		
		//Note note3 = new Note("Centos Box Upstairs");
		//note3.setBody("root password: rhinosaurus21!\nuserid: gulbert\npassword: tableandchair345\nip address: 192.168.1.240");
		
		//b.addNote(note1);
		//b.addNote(note2);
		//b.addNote(note3);
		
		/**
		System.out.println(b);
		System.out.println("");
		
		List<String> noteTitles = b.getNoteTitles();
		
		for (String s : noteTitles) {
			System.out.println(s);
		}
		*/

		//b.save("etc/binder2.dat");
		b.load("etc/binder2.dat");
		b.load(b.toByteArray());
		//b.save(b.toByteArray());
	}
}
