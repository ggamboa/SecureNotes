package com.ggg.sn2.ui.console;


import com.ggg.sn2.model.Binder;
import com.ggg.sn2.ui.command.Command;
import com.ggg.sn2.ui.command.CommandFactory;

public class MainMenu {
	
	private static final int  DEFAULT_TIME_TO_DISPLAY = 5;
	private static final String[] banner =
		{	
			"",
			"Welcome to SecureNotes v1.0",
			"(c) 2015 - Gilbert Gamboa",
			"",
			"Type help to view list of commands...",
			""
		};
	
	private static final String PROMPT = "sn> ";
	
	private static final String EXIT_APP = "exit";
	
	private CommandFactory factory;
	
	public MainMenu(Binder b) {
		this.factory = new CommandFactory(b);
	}
	
	public void start() throws Exception {
		
		for(int j=0; j<40; j++) ConsoleUtility.displayEmptyLine();
		
		for(int i=0; i < banner.length; i++) {
			ConsoleUtility.displayLine(banner[i]);
		}
		
		while(true) {
			String sCmd = ConsoleUtility.getLine(PROMPT);
			if(sCmd.equalsIgnoreCase(EXIT_APP)) {
				ConsoleUtility.displayLine("bye!");
				break;
			}
			else {
				Command cmd = factory.getCommand(sCmd);
				cmd.execute();
			}
		}
	}


	public static void selfDestruct(int numSeconds) {

		for (int i=0; i<3; i++) {
			try
	        {
	            Thread.sleep(500);
	            System.out.print("wait\r");
	        }
	        catch(Exception e)
	        {
	        }
			
			try
	        {
	            Thread.sleep(500);
	            System.out.print("    \r");
	        }
	        catch(Exception e)
	        {
	        }
		}

		waitAndClear(numSeconds, "self destruct in: ");

		System.out.println("kaboom!\n");
	}
	
	public static void waitAndClear() {
		waitAndClear(DEFAULT_TIME_TO_DISPLAY, "clearing screen... ");
	}

	
	public static void waitAndClear(int numSeconds, String waitPrompt) {
		
		if(numSeconds == 0) {
			numSeconds = DEFAULT_TIME_TO_DISPLAY;
		}
		
		for (int i=numSeconds; i>-1; i--) {
			try
	        {
	            Thread.sleep(1000);
	            System.out.print(waitPrompt + i + "     \r");
	        }
	        catch(Exception e)
	        {
	        }
		}
		
		for(int j=0; j<40; j++) ConsoleUtility.displayEmptyLine();
	}
	
}
