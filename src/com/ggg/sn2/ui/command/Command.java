package com.ggg.sn2.ui.command;

import com.ggg.sn2.ui.exception.CommandExecutionException;

public interface Command {

	public void execute() throws CommandExecutionException;

}
