package com.ggg.sn2.ui.command;

import com.ggg.sn2.ui.console.ConsoleUtility;
import com.ggg.sn2.ui.exception.CommandExecutionException;

public class DisplayErrorCommand implements Command {

	private String errorText;
	
	public DisplayErrorCommand(String s) {
		this.errorText = s;
	}

	@Override
	public void execute() throws CommandExecutionException {
		ConsoleUtility.displayLine(errorText);
	}

}
