package com.ggg.sn2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class Context {

	public static final String contextFileName = ApplicationConstants.DEFAULT_DATA_PATH + "/" + "sn.context";
	public static final String LAST_FILE_ACCESSED_KEY = "last_file_accessed";
	
	private static Context instance;
	private String lastFileAccessed;
	
	private Properties props;
	
	private Context() {
		try {
			readContextFile();
			lastFileAccessed = props.getProperty(LAST_FILE_ACCESSED_KEY);
		}
		catch(Exception e) {
			lastFileAccessed = "";
		}
	}
	
	public static Context getInstance() {
		synchronized(Context.class) {
			if(instance == null) {
				instance = new Context();
			}
			return instance;
		}
	}
	
	private void readContextFile() throws IOException {
		
		File file = new File(contextFileName);
		props = new Properties();
		
		if(file.exists()) {
			try {
				
				props.load(new FileInputStream(file));
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		else {
			OutputStream os = new FileOutputStream(file);
			props.setProperty(LAST_FILE_ACCESSED_KEY, "");
			props.store(os, null);
			os.close();
		}
	}
	
	
	
	public void saveContextFile() {
		OutputStream out = null;
		try {
			out = new FileOutputStream(contextFileName);
			props.setProperty(LAST_FILE_ACCESSED_KEY, lastFileAccessed);
			props.store(out, null);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(out != null) {
				try {
					out.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	public String getLastFileAccessed() {
		return lastFileAccessed;
	}

	public void setLastFileAccessed(String lastFileAccessed) {
		this.lastFileAccessed = lastFileAccessed;
	}

	
}
