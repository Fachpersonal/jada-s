package de.MissingNameException.Commands;

import java.io.IOException;

import de.MissingNameException.App;
import de.MissingNameException.ClientHandler;

public class Janis implements Command{
	
	private String commandName = "janis";
	private String commandDescription = "Idk man just kill me";
	private String commandPermission = "default"; // change if commands needs a permission!
	private String[] commandAlias = {"j", "depression"};
	
	@Override
	public void crun(ClientHandler client, String...arg) throws IOException {
		App.printC(client.getClientSocket(), "Janis has severe anxiety lmao");
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
