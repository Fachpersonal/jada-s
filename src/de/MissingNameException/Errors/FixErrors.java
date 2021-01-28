package de.MissingNameException.Errors;

import de.MissingNameException.App;
import de.MissingNameException.ClientHandler;
import de.MissingNameException.Commands.Command;

import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;

public class FixErrors implements Command{
	
	private String commandName = "fixerrors";
	private String commandDescription = "Error-List";
	private String commandPermission = "dev"; // change if commands needs a permission!
	private String[] commandAlias = {"dev", "d", "errors"};
	
	private String label;
	private String description;
	private String creationDate;
	private String expirationDate;
	
	@Override
	public void crun(ClientHandler client,String...arg) throws IOException {
		String tempArg;
		label = "";
		description = "";
		creationDate = "";
		expirationDate = "";

		if (arg.length > 0){
			if(arg[0].toLowerCase().equals("create")) {
				if(arg.length >= 2) {
					for(int i = 1; i < arg.length; i++) {
						label += arg[i] + " ";
					}
					creationDate = (LocalDate.now()).toString();
					App.printC(client.getClientSocket(), "Description (can be empty) >> ");
					tempArg = App.getClientMsg(client.getClientSocket());
					if(tempArg == null) {
						description = "NULL";
					} else {
						description = tempArg;
					}
					
					App.printC(client.getClientSocket(), "Expiration Date (YYYY-MM-DD)(can be empty) >> ");
					
					tempArg = App.getClientMsg(client.getClientSocket());
					
					if(tempArg == null) {
						expirationDate = "NULL";
					} else {
						expirationDate = tempArg;
					}
					
					App.printC(client.getClientSocket(), "" + App.nl + "Error ::" + App.nl +
							"Label > " + label + App.nl +
							"Description > " + description + App.nl +
							"Creation Date > " + creationDate + App.nl +
							"Expiration Date > " + expirationDate + App.nl +
							"-------------------------------------------" + App.nl +
							"Do you want to commit this Error? >> |[Y]ES| |[N]O|" + App.nl +
							"  >> ");
					tempArg = App.getClientMsg(client.getClientSocket());
					
					if(tempArg.equalsIgnoreCase("y")) {
						App.d.execute("INSERT INTO jada.errors (label, description, creationdate, expirationdate) VALUE ('" + label + "', '" + description + "', '" + creationDate + "', '" + expirationDate + "')");
						App.printC(client.getClientSocket(), "Error successfully created!");
					} else if(tempArg.equalsIgnoreCase("n")) {
						App.printC(client.getClientSocket(), "Error successfully canceled!");
					}
				} else {
					App.printC(client.getClientSocket(), ErrorManagerList.print("e001") + App.nl + "create {%labeame} | remove {%id} | show | edit}");
				}
			} else if(arg[0].equalsIgnoreCase("remove")) {
				if(arg.length >= 2) {
					App.d.execute("DELETE FROM jada.errors WHERE id= " + Integer.valueOf(arg[1]));
					App.printC(client.getClientSocket(), "Error successfully removed!");
				} else {
					App.printC(client.getClientSocket(), ErrorManagerList.print("e001") + App.nl +"create {%labeame} | remove {%id} | show | edit}");
				}
			} else if(arg[0].equalsIgnoreCase("show")) {
				ArrayList<String> id = App.d.StringSELECT("SELECT * FROM jada.errors", "id");
				ArrayList<String> label = App.d.StringSELECT("SELECT * FROM jada.errors", "label");
				ArrayList<String> description = App.d.StringSELECT("SELECT * FROM jada.errors", "description");
				ArrayList<String> creationdate = App.d.StringSELECT("SELECT * FROM jada.errors", "creationdate");
				ArrayList<String> expirationdate = App.d.StringSELECT("SELECT * FROM jada.errors", "expirationdate");
				String x = "";
				for (int i = 0; i < id.size(); i++) {
					x += id.get(i) + " | " + label.get(i) + " | " + description.get(i) + " | " + creationdate.get(i) + " | " + expirationdate.get(i) + App.nl;
				}
				App.printC(client.getClientSocket(), x);
			} else if(arg[0].equalsIgnoreCase("edit")) {
				App.printC(client.getClientSocket(), "Currently not available!");
			}	
		} else {
			App.printC(client.getClientSocket(), ErrorManagerList.print("e001") + App.nl + "create {%labeame} | remove {%id} | show | edit}");
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
