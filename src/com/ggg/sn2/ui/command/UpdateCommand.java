package com.ggg.sn2.ui.command;

import java.util.ArrayList;
import java.util.List;

import com.ggg.sn2.model.Binder;
import com.ggg.sn2.model.Note;
import com.ggg.sn2.ui.console.ConsoleUtility;
import com.ggg.sn2.ui.console.Constants;
import com.ggg.sn2.ui.console.MainMenu;
import com.ggg.sn2.ui.exception.CommandExecutionException;

public class UpdateCommand extends ItemCommand implements Command {

	public static final String COMMAND_NAME = "update";
	public static final String COMMAND_PURPOSE = "update a note";
	public static final String COMMAND_USAGE = "update <index of note>. E.g. update 2";


	public UpdateCommand(Binder b, String[] commandTokens) throws Exception {
		super(b, commandTokens);
	}

	@Override
	public void execute() throws CommandExecutionException {
		
		Note note = binder.getNote(this.index);
		
		try {
			
			String currentTitle = note.getTitle();
			String currentContent = note.getBody();
			
			ConsoleUtility.displayLine("What's currently on file:");
			ConsoleUtility.displayEmptyLine();
			
			ConsoleUtility.displayLine(note.toString());
			
			ConsoleUtility.displayLine("Hit enter if there is no change; type " + Constants.DELETE_LINE_MARKER + " to delete line: ");
			
			ConsoleUtility.displayEmptyLine();
			ConsoleUtility.displayLine("Old Title: " + currentTitle);
			String newTitle = ConsoleUtility.getLine("New Title: ");
			
			if(newTitle.trim().length() == 0) {
				newTitle = currentTitle;
			}
			
			ConsoleUtility.displayEmptyLine();
			
			List<String> lines = ConsoleUtility.breakUpIntoLines(currentContent);

			String nof = "/" + Integer.toString(lines.size()) + ": ";
			
			for(int idx=0; idx < lines.size(); idx++) {
				
				ConsoleUtility.displayLine("Old " + Integer.toString(idx+1) + nof + lines.get(idx));
				String newLine = ConsoleUtility.getLine("New " + Integer.toString(idx+1) + nof);
				
				if(newLine.trim().length() > 0) {
					lines.set(idx, newLine);
				}
			}
			
			ConsoleUtility.displayEmptyLine();
			
			List<String> newLines = new ArrayList<String>(lines.size());

			for(String line : lines) {
				if(!line.equalsIgnoreCase(Constants.DELETE_LINE_MARKER)) {
					newLines.add(line);
				}
			}
			
			ConsoleUtility.displayLine("Updated Note:");
			ConsoleUtility.displayEmptyLine();
			Note newNote = new Note(newTitle, newLines);
			ConsoleUtility.displayLine(newNote.toString());
			
			boolean confirm = ConsoleUtility.confirmAction("Save changes? ");
			
			if (confirm) {
				binder.updateNote(this.index, newNote);
				binder.save();
				ConsoleUtility.displayLine("Note updated!");
			}
			else {
				ConsoleUtility.displayLine("Change(s) discarded!");
			}
			
			MainMenu.waitAndClear();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new CommandExecutionException(e);
		}
		
		
	}

}
