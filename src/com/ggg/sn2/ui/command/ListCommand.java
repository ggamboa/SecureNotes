package com.ggg.sn2.ui.command;

import java.util.List;

import com.ggg.sn2.model.Binder;
import com.ggg.sn2.model.Note;
import com.ggg.sn2.ui.console.ConsoleUtility;
import com.ggg.sn2.ui.exception.CommandExecutionException;

public class ListCommand implements Command {

	public static final String COMMAND_NAME = "list";
	public static final String COMMAND_PURPOSE = "list all notes in file";
	public static final String COMMAND_USAGE = "list";

	private Binder binder;
	
	public ListCommand(Binder binder) {
		this.binder = binder;
	}
	
	@Override
	public void execute() throws CommandExecutionException {
		
		List<Note> list = binder.getNotes();
		int i = 1;
		for(Note note : list) {
			ConsoleUtility.displayLine(Integer.toString(i++) + " - " + note.getTitle());
		}
		
	}

}
