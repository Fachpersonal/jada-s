package de.MissingNameException.Commands;

import java.io.IOException;

import java.util.ArrayList;

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
		ArrayList<String> cmd = App.d.StringSELECT("SELECT * FROM jada.commands ORDER BY cmd", "cmd");
		ArrayList<String> description = App.d.StringSELECT("SELECT * FROM jada.commands ORDER BY cmd", "description");
		String result = "";
		for (int i = 0; i < cmd.size(); i++) {
			if(App.canRun(CommandManager.commands.get(i).getCommandPermission(), client)){
				result += cmd.get(i) + " | " + description.get(i) + App.nl;
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
