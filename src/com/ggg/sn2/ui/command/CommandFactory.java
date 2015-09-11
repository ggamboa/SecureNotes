package com.ggg.sn2.ui.command;

import com.ggg.sn2.model.Binder;

public class CommandFactory {
	
	private Binder binder;
	
	public CommandFactory(Binder binder) {
		this.binder = binder;
	}
	
	public Command getCommand(String tCmd) throws Exception {
		Command command = null;
	
		try {
			String[] tokens = tCmd.trim().split(" +");
	
			if (tokens.length > 0) {
				
				String cmdName = tokens[0];
				
				if(cmdName.equalsIgnoreCase(AddCommand.COMMAND_NAME)) {
					command = new AddCommand(this.binder);
				}
				else if(cmdName.equalsIgnoreCase(DeleteCommand.COMMAND_NAME)) {
					command = new DeleteCommand(this.binder, tokens);
				}
				else if(tokens[0].equalsIgnoreCase(DisplayCommand.COMMAND_NAME)) {
					command = new DisplayCommand(this.binder, tokens);
				}
				else if(tokens[0].equalsIgnoreCase(UpdateCommand.COMMAND_NAME)) {
					command = new UpdateCommand(this.binder, tokens);
				}
				else if(tokens[0].equalsIgnoreCase(ListCommand.COMMAND_NAME)) {
					command = new ListCommand(this.binder);
				}
				else if(tokens[0].equalsIgnoreCase(HelpCommand.COMMAND_NAME)) {
					command = new HelpCommand();
				}
				else {
					command = new DisplayErrorCommand("Command not recognized!");
				}
			}
		}
		catch(Exception e) {
			command = new DisplayErrorCommand(e.getMessage());
		}
		
		return command;
	}
}
