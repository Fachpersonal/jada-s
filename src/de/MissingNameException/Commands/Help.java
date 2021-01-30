package de.MissingNameException.Commands;

import java.io.IOException;

import de.MissingNameException.App;
import de.MissingNameException.ClientHandler;
import de.MissingNameException.CommandManager;



public class Help implements Command{
	
	private String commandName = "help";
	private String commandDescription = "Shows this menu!";
	private String commandPermission = "default"; // change if commands needs a permission!
	private String[] commandAlias = {"h", "?"};
	
	@Override
	public void crun(ClientHandler client,String...arg) throws IOException {
		String result = "";
		for (int i = 0; i < CommandManager.commands.size(); i++) {
			if(App.canRun(CommandManager.commands.get(i).getCommandPermission(), client)) {
				result += CommandManager.commands.get(i).getCommandName() + " | " + CommandManager.commands.get(i).getCommandDescription() + App.nl;
			}
		}
		App.printC(client.getClientSocket(), result);
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
