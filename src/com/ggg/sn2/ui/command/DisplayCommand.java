package com.ggg.sn2.ui.command;

import com.ggg.sn2.model.Binder;
import com.ggg.sn2.model.Note;
import com.ggg.sn2.ui.console.ConsoleUtility;
import com.ggg.sn2.ui.console.MainMenu;
import com.ggg.sn2.ui.exception.CommandExecutionException;

public class DisplayCommand extends ItemCommand implements Command {

	public static final String COMMAND_NAME = "view";
	public static final String COMMAND_PURPOSE = "view a note";
	public static final String COMMAND_USAGE = "view <index of note> {-t(timeInSeconds)}. E.g. view 2 -> use default duration time to display note; view 2 -t12 -> specify duration to display, 12 seconds";
	
	private int timeToDisplay = 10;

	public DisplayCommand(Binder b, String[] commandTokens) throws Exception {
		super(b, commandTokens);
		
		if(commandTokens.length > 2) {
			if(commandTokens[2].startsWith("-t")) {
				try {
					timeToDisplay = Integer.parseInt(commandTokens[2].substring(2, commandTokens[2].length()));
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void execute() throws CommandExecutionException {
		
		Note note = binder.getNote(index); // ui displays items numbered 1 to n
		ConsoleUtility.displayLine(note.toString());
		
		MainMenu.selfDestruct(timeToDisplay);
		
	}

}
