package de.MissingNameException.Commands;

import java.io.IOException;

import java.time.LocalDate;

import de.MissingNameException.App;
import de.MissingNameException.ClientHandler;

public class Date implements Command{
	private String commandName = "date";
	private String commandDescription = "Shows the current date!";
	private String commandPermission = "default"; // change if commands needs a permission!
	private String[] commandAlias = null;
	
	@Override
	public void crun(ClientHandler client, String...arg) throws IOException {
		LocalDate date = LocalDate.now();
		App.printC(client.getClientSocket(), "The current date is " + date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear() + "");
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
