package com.ggg.sn2;

import java.io.File;

import com.ggg.sn2.model.Binder;
import com.ggg.sn2.model.BinderInstantiationException;
import com.ggg.sn2.ui.console.ConsoleUtility;
import com.ggg.sn2.ui.console.MainMenu;

public class SecureNotes {
	
	public SecureNotes() {
		Context context = Context.getInstance();
		String fileName = context.getLastFileAccessed();
		if(fileName.length() > 0) {
			startApplication(fileName);
		}
		else {
			System.err.println("Usage: SecureNotes <fileName>");
			System.err.println("No file was specified and no file name is saved in the history file.");
			System.err.println("Succeeding invocation of the program will use the most recent file if no file is specified.");
		}
	}
	
	public SecureNotes(String fileName) {
		startApplication(fileName);
	}
	
	private void startApplication(String fileName) {
		
		try {
			
			Binder b = null;
			String pathAndFile = ApplicationConstants.DEFAULT_DATA_PATH + "/" + fileName;
			
			if(fileExists(pathAndFile)) {
				//ConsoleUtility.displayLine(pathAndFile + " exists!");
				String pp = ConsoleUtility.promptForPassword("Enter password for " + fileName + ": ");
				b = Binder.getInstance(pathAndFile , pp);
			}
			else {
				
				ConsoleUtility.displayLine(pathAndFile + " not found!");
				boolean bCreateNewFile = ConsoleUtility.confirmAction("Create new file: " + fileName + "? ");
				
				if(bCreateNewFile) {

					ConsoleUtility.displayLine("OK let's create new file " + fileName);
					if(isValidFileName(fileName)) {
						
						String pp1;
						String pp2;
						
						while(true) {
							
							while(true) {
								pp1 = ConsoleUtility.promptForPassword("Enter password: ");
								if(isValidPassword(pp1)) {
									break;
								}
								else {
									ConsoleUtility.displayLine("Invalid password. Password should be at least 8 characters long.");
								}
							}
							pp2 = ConsoleUtility.promptForPassword("Enter password again: ");
							
							if(pp1.equals(pp2)) {
								break;
							}
							else{
								ConsoleUtility.displayLine("Passwords do not match.");
							}
						}
						b = Binder.createNewInstance(pathAndFile , pp1);
					}
					else {
						System.err.println("Invalid filename. File name should begin with an alpha character. Only alphanumeric chracters are allowed except for the following: .,-, _." );
						System.err.println("The file extension if present can only have alpha characters. " );

						System.exit(100);
					}
				}
				else {
					ConsoleUtility.displayLine("Terminating program. Bye!");
					System.exit(100);
				}
			}
			
			
			// We have access to the file, save file name in context
			Context.getInstance().setLastFileAccessed(fileName);
			Context.getInstance().saveContextFile();
			
			MainMenu menu=new MainMenu(b);
			menu.start();
			
		}
		catch(BinderInstantiationException be) {
			ConsoleUtility.displayLine("SecureNotes file cannot be decrypted! Please enter correct password.");
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	private boolean fileExists(String fileName) {
		File file = new File(fileName);
		return file.exists();
	}

	private boolean isValidPassword(String s) {
		return s.matches(ApplicationConstants.PASSWORD_REGEX); 
	}
	
	private boolean isValidFileName(String s) {
		return s.matches(ApplicationConstants.FILENAME_REGEX); 
	}
	
	public static void main(String[] args) {
		
		String fileName;

		if (args.length > 0 ) {
			fileName = args[0];
			SecureNotes sn = new SecureNotes(fileName);
		}
		else {
			SecureNotes sn = new SecureNotes();
		}
	}
}
