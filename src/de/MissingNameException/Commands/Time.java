package de.MissingNameException.Commands;

import java.io.IOException;

import java.time.LocalTime;

import de.MissingNameException.App;
import de.MissingNameException.ClientHandler;

public class Time implements Command{
	private String commandName = "time";
	private String commandDescription = "Shows the current time!";
	private String commandPermission = "default"; // change if commands needs a permission!
	private String[] commandAlias = null;
	
	@Override
	public void crun(ClientHandler client, String...arg) throws IOException {
		LocalTime time = LocalTime.now();
		App.printC(client.getClientSocket(), "The current time is " + time.getHour() + ":" + time.getMinute() + "");
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
