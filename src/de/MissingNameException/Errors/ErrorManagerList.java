package de.MissingNameException.Errors;

import java.io.IOException;

import de.MissingNameException.App;
import de.MissingNameException.ClientHandler;
import de.MissingNameException.Commands.Command;

public class ErrorManagerList implements Command{
	
	private String commandName = "errormanagerlist";
	private String commandDescription = "Shows all infos about the errorcode!";
	private String commandPermission = "default"; // change if commands needs a permission!
	private String[] commandAlias = {"eml"};
	
	// #####################  /* ERRORS *\  ####################################	
	public static Error e000 = new Error("Command could not be found! ", "e000");
	public static Error e001 = new Error("No defined arguments! ", "e001");
	public static Error e002 = new Error("Account already exists", "e002");
	public static Error e003 = new Error("Missing Permissions!", "e003");
	public static Error e004 = new Error("Account already has that permission!", "e004");
	public static Error e005 = new Error("Account does not exists!", "e005");
	public static Error e006 = new Error("Changelog file could not be found!", "e006");
	
	
	private static Error[] errors = {e000, e001, e002, e003, e004, e005};
	// #####################  /* ERRORS *\  ####################################
	
	
	@Override
	public void crun(ClientHandler client, String...arg) throws IOException {
		String x = "";
		for (int i = 0; i < errors.length; i++) {
			x += errors[i].toString() + App.nl;
		}
		App.printC(client.getClientSocket(), x);
	}
	
	public static String print(String e) throws IOException  {
		for (int i = 0; i < errors.length; i++) {
			if(e.equals(errors[i].getErrorName())) {
				return (errors[i].toString());
			}
		}
		return null;
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
