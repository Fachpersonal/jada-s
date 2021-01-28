package de.MissingNameException.Commands;

import java.io.IOException;

import de.MissingNameException.App;
import de.MissingNameException.ClientHandler;

public class Infos implements Command{
	
	private String commandName = "infos";
	private String commandDescription = "Shows the infos about the Creator!";
	private String commandPermission = "default"; // change if commands needs a permission!
	private String[] commandAlias = {"i"};
	
	@Override
	public void crun(ClientHandler client, String...arg) throws IOException {
		App.printC(client.getClientSocket(), "[J]ust[A]nother[D]umb[A]ssistant - Created by @MNException");
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
