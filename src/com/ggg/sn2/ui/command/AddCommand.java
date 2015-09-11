package com.ggg.sn2.ui.command;

import java.io.IOException;

import com.ggg.sn2.model.Binder;
import com.ggg.sn2.model.Note;
import com.ggg.sn2.ui.console.ConsoleUtility;
import com.ggg.sn2.ui.console.MainMenu;
import com.ggg.sn2.ui.console.MultiLineInput;
import com.ggg.sn2.ui.console.MultiLineReader;
import com.ggg.sn2.ui.exception.CommandExecutionException;

public class AddCommand implements Command {

	private Binder binder;
	public static final String COMMAND_NAME = "add";
	public static final String COMMAND_PURPOSE = "add a new note";
	public static final String COMMAND_USAGE = "add";
	
	public AddCommand(Binder binder) {
		this.binder = binder;
	}
	
	@Override
	public void execute() throws CommandExecutionException {
		
		try {

			String title = ConsoleUtility.getLine("Title: ");
			if (title.trim().length() == 0) title = "New Note";
						
			MultiLineInput mli = new MultiLineReader();
			
			ConsoleUtility.displayLine("Write your notes below. Enter \"*done\" on its own line when done.");
			String body = mli.getText();
			
			boolean confirm = ConsoleUtility.confirmAction("Save? ");
			
			if (confirm) {
				binder.addNote(new Note(title, body));
				binder.save();
				ConsoleUtility.displayLine("Note added!");
			}
			
			MainMenu.waitAndClear();
		}
		catch(IOException e) {
			e.printStackTrace();
			throw new CommandExecutionException("Error executing AddCommand", e);
		}
	}

}
