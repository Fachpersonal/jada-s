package de.MissingNameException.Commands;

import java.io.IOException;

import de.MissingNameException.App;
import de.MissingNameException.ClientHandler;

public class HallOfFame implements Command{

	private String commandName = "halloffame";
	private String commandDescription = "Shows all supporting People!";
	private String commandPermission = "default"; // change if commands needs a permission!
	private String[] commandAlias = {"hof", "hall", "credits"};
	
	@Override
	public void crun(ClientHandler client,String...arg) throws IOException {
		App.printC(client.getClientSocket(),"The following persons helped me during the project [J]ust[A]nother[D]umb[A]ssistant" + App.nl 
				+ "Tester Jannik" 
				+ App.nl
				+ "Tester Marek" 
				+ App.nl 
				+ "Tester Flo"
				+ App.nl + App.nl
				+ "Very Special Persons!"
				+ App.nl
				+ "Alisa the silent Helper ^^"
				+ App.nl
				+ "Nina the special Helper ^^");
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
