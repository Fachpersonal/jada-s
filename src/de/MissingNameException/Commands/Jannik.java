package de.MissingNameException.Commands;

import java.io.IOException;

import de.MissingNameException.App;
import de.MissingNameException.ClientHandler;

public class Jannik implements Command{

	private String commandName = "jannik";
	private String commandDescription = "Es ist einfach Jannik!";
	private String commandPermission = "default"; // change if commands needs a permission!
	private String[] commandAlias = null;
	
	@Override
	public void crun(ClientHandler client, String...arg) throws IOException {
		App.printC(client.getClientSocket(), "JANNIK IST SUPER FETT???");
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
