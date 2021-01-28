package de.MissingNameException.Commands;

import java.util.ArrayList;
import java.util.Scanner;

import de.MissingNameException.App;
import de.MissingNameException.ClientHandler;
import de.MissingNameException.CommandManager;
import de.MissingNameException.Driver;
import de.MissingNameException.Errors.ErrorManagerList;

import java.io.IOException;

import java.time.LocalDate;

public class TODO implements Command{
	
	private String commandName = "todo";
	private String commandDescription = "TODO-Program";
	private String commandPermission = "default"; // change if commands needs a permission!
	private String[] commandAlias = null;
	
	private String label;
	private String description;
	private String creationDate;
	private String expirationDate;

	@Override
	public void crun(ClientHandler client, String...arg) throws IOException {
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
					
					String reminder = "0";
					
					App.printC(client.getClientSocket(), "Add reminder in days >> ");
					
					reminder = App.getClientMsg(client.getClientSocket());
					
					if(Integer.valueOf(reminder) > 365) {
						reminder = "365";
					} else if(Integer.valueOf(reminder) < 0) {
						reminder = "0";
					}
					
					App.printC(client.getClientSocket(), "" + App.nl + App.nl + "TODO ::" + App.nl +
							"Label > " + label + App.nl +
							"Description > " + description + App.nl +
							"Creation Date > " + creationDate + App.nl +
							"Expiration Date > " + expirationDate + App.nl +
							"Created by > " + client.getClientAcc().getAccountName() + App.nl +
							"Remind (in days) > " + reminder + App.nl +
							"-------------------------------------------" + App.nl +
							"Do you want to commit this Task? >> |[Y]ES| |[N]O|" + App.nl +
							"  >> ");
					tempArg = App.getClientMsg(client.getClientSocket());
					
					if(tempArg.equalsIgnoreCase("y")) {
						App.d.execute("INSERT INTO jada.todos (label, description, creationdate, expirationdate, user, reminder) VALUE ('" + label + "', '" + description + "', '" + creationDate + "', '" + expirationDate + "', '" + client.getClientAcc().getAccountName() + "', '" + reminder + "')");
						App.printC(client.getClientSocket(), "Todo successfully created!");
					} else if(tempArg.equalsIgnoreCase("n")) {
						App.printC(client.getClientSocket(), "Todo successfully canceled!");
					}
				} else {
					App.printC(client.getClientSocket(), ErrorManagerList.print("e001") + App.nl + "create {%labelname} | remove {%id} | show ");
				}
			} else if(arg[0].equalsIgnoreCase("remove")) {
				if(arg.length >= 2) {
					App.d.execute("DELETE FROM jada.todos WHERE id= " + Integer.valueOf(arg[1]));
					App.printC(client.getClientSocket(), "Task successfully removed!");
				} else {
					App.printC(client.getClientSocket(), ErrorManagerList.print("e001") + App.nl + "create {%labelname} | remove {%id} | show ");
				}
			} else if(arg[0].equalsIgnoreCase("show")) {
				ArrayList<String> id = App.d.StringSELECT("SELECT * FROM jada.todos", "id");
				ArrayList<String> label = App.d.StringSELECT("SELECT * FROM jada.todos", "label");
				ArrayList<String> description = App.d.StringSELECT("SELECT * FROM jada.todos", "description");
				ArrayList<String> creationdate = App.d.StringSELECT("SELECT * FROM jada.todos", "creationdate");
				ArrayList<String> expirationdate = App.d.StringSELECT("SELECT * FROM jada.todos", "expirationdate");
				ArrayList<String> user  = App.d.StringSELECT("SELECT * FROM jada.todos", "user");
				ArrayList<String> reminder = App.d.StringSELECT("SELECT * FROM jada.todos", "reminder");
				String x = "";
				for (int i = 0; i < id.size(); i++) {
					x += id.get(i) + " | " + label.get(i) + " | " + description.get(i) + " | " + creationdate.get(i) + " | " + expirationdate.get(i) + " | " + user.get(i) + " | " + reminder.get(i) + App.nl;
				}
				App.printC(client.getClientSocket(), x);
			}	
		} else {
			App.printC(client.getClientSocket(), ErrorManagerList.print("e001") + App.nl + "create {%labelname} | remove {%id} | show ");
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
