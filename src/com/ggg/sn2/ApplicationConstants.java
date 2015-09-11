package com.ggg.sn2;

public class ApplicationConstants {

	public static final String DEFAULT_DATA_PATH = "./etc";
	
	// Valid chars are non white-space characters. A space between words is allowed. 
	// Minimum length is 8.
	// Examples: "Abc!123*" "one hundred years of solitude" 
	public static final String PASSWORD_REGEX = "(\\S| ){8,}";   
	
	// Must begin with an alphabetic character. Numbers, hyphen, underscore are allowed after the first character. 
	// An extension comprised of alphabetic characters is optional. 
	// E.g. File123.data, File-123_jan31
	public static final String FILENAME_REGEX = "[A-Za-z][A-Za-z0-9\\-_]+(.([A-Za-z])+)*"; 

}
