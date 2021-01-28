package de.MissingNameException.Commands;

import java.io.IOException;

import de.MissingNameException.ClientHandler;

public class Shutdown implements Command{
	
	private String commandName = "shutdown";
	private String commandDescription = "Shutdowns the System!";
	private String commandPermission = "admin"; // change if commands needs a permission!
	private String[] commandAlias = {"sd", "exit", "stop"};
	
	@Override
	public void crun(ClientHandler client, String...arg) throws IOException{
		try {
			System.out.println("System is shutting down!");
			Thread.sleep(3000);
			System.exit(-1);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public String getCommandName() {return commandName;}

	@Override
	public String getCommandDescription() {return commandDescription;}

	@Override
	public String getCommandPermission() {return commandPermission;}
	
	@Override
	public String[] getCommandAlias() {return commandAlias;}
}
