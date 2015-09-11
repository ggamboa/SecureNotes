package com.ggg.sn2.ui.command;

import com.ggg.sn2.model.Binder;
import com.ggg.sn2.ui.console.ConsoleUtility;
import com.ggg.sn2.ui.exception.CommandExecutionException;

public class DeleteCommand extends ItemCommand implements Command {

	public static final String COMMAND_NAME = "delete";
	public static final String COMMAND_PURPOSE = "delete a note";
	public static final String COMMAND_USAGE = "delete <index of note>. E.g. delete 2";
	
	public DeleteCommand(Binder b, String[] commandTokens) throws Exception {
		super(b, commandTokens);
	}
	
	@Override
	public void execute() throws CommandExecutionException {

		String title = binder.getNote(index).getTitle();
		
		try {
			boolean confirm = ConsoleUtility.confirmAction("Delete note \"" + title + "\"? ");
			
			if (confirm) {
				binder.deleteNote(this.index);
				binder.save();
				ConsoleUtility.displayLine("Note deleted!");
			}
		}
		catch(Exception e) {
			throw new CommandExecutionException(e);
		}
	}
}
