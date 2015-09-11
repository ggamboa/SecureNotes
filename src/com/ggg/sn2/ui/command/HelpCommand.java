package com.ggg.sn2.ui.command;

import com.ggg.sn2.ui.console.ConsoleUtility;
import com.ggg.sn2.ui.exception.CommandExecutionException;

public class HelpCommand implements Command {

	public static final String COMMAND_NAME = "help";
	public static final String COMMAND_PURPOSE = "display list of valid commands";
	public static final String COMMAND_USAGE = "help";
	
	public static String[] HELP_LINES;

	public HelpCommand() {
		HELP_LINES = new String[8];
		HELP_LINES[0] = "Commands:";
		HELP_LINES[1] = "";
		HELP_LINES[2] = ListCommand.COMMAND_NAME + ": " + ListCommand.COMMAND_PURPOSE + " [usage: " + ListCommand.COMMAND_USAGE + "]";
		HELP_LINES[3] = AddCommand.COMMAND_NAME + ": " + AddCommand.COMMAND_PURPOSE + " [usage: " + AddCommand.COMMAND_USAGE + "]";
		HELP_LINES[4] = DeleteCommand.COMMAND_NAME + ": " + AddCommand.COMMAND_PURPOSE + " [usage: " + DeleteCommand.COMMAND_USAGE + "]";
		HELP_LINES[5] = DisplayCommand.COMMAND_NAME + ": " + AddCommand.COMMAND_PURPOSE + " [usage: " + DisplayCommand.COMMAND_USAGE + "]";
		HELP_LINES[6] = UpdateCommand.COMMAND_NAME + ": " + AddCommand.COMMAND_PURPOSE + " [usage: " + UpdateCommand.COMMAND_USAGE + "]";
		HELP_LINES[7] = HelpCommand.COMMAND_NAME + ": " + AddCommand.COMMAND_PURPOSE + " [usage: "  + HelpCommand.COMMAND_USAGE + "]";
	}

	@Override
	public void execute() throws CommandExecutionException {
		
		for(int i=0; i<HELP_LINES.length; i++) {
			ConsoleUtility.displayLine(HELP_LINES[i]);
		}
		
	}

}
