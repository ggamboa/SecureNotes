package com.ggg.regex.samples;

public class TestRegex {

	public TestRegex() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
	
		String s = "bc-de_";
		System.out.println(s.matches("[A-Za-z][A-Za-z0-9\\-_]+(.([A-Za-z])+)*"));
		
		String s2= " 23&^_+a";
		System.out.println(s2.matches("(\\S| ){8,}"));
		
		System.out.print("hello world!");
		System.out.print("\r");
		System.out.print("world!");
		try {
			Runtime.getRuntime().exec( "command /c cls" ) ; 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
