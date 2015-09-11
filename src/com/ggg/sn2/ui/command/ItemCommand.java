package com.ggg.sn2.ui.command;

import com.ggg.sn2.model.Binder;
import com.ggg.sn2.ui.exception.InvalidCommandParameterException;

public abstract class ItemCommand {

	protected Binder binder;
	protected int index;
	
	public ItemCommand(Binder b, String[] commandTokens) throws Exception {
		
		this.binder = b;
				
		// commandTokens[0] is the command text itself, i.e. "delete"
		// we need to check if index is present in commandTokens[1]
		
		if (commandTokens.length > 1) {
			// parse the index as int from commandTokens[1], subtract 1 since ui is not zero-based
			try {
				this.index = Integer.parseInt(commandTokens[1]) - 1;
				if (this.index >= this.binder.size()) {
					// error -  index sent is out of bounds
					throw new InvalidCommandParameterException("Index is out of bounds. Number of notes in binder is: " + this.binder.size() + ".");
				}
			}
			catch(NumberFormatException e) {
				// error -  index sent is not a number
				throw new InvalidCommandParameterException("Index should be a number.");
			}
		}
		else {
			// error - index was not sent
			throw new InvalidCommandParameterException("Missing index.");
		}
	}

}
