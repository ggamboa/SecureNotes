package com.ggg.sn2.ui.exception;

public class InvalidCommandParameterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6633490780516245227L;

	public InvalidCommandParameterException() {
		super("Invalid command parameter was passed.");
	}

	public InvalidCommandParameterException(String message) {
		super("Invalid command parameter. " + message);
	}

	
}
