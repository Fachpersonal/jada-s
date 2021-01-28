package de.MissingNameException.Commands;

import java.io.IOException;

import java.util.ArrayList;

import de.MissingNameException.App;

import de.MissingNameException.ClientHandler;
import de.MissingNameException.CommandManager;
import de.MissingNameException.Errors.ErrorManagerList;

public class AccountCommand implements Command{
	
	private String commandName = "account";
	private String commandDescription = "Allows to create Accounts!";
	private String commandPermission = "admin"; // change if commands needs a permission!
	private String[] commandAlias = {"acc"};
	
	@Override
	public void crun(ClientHandler client, String...arg) throws IOException {
		if(arg.length > 0) {
			ArrayList<String> x = App.d.StringSELECT("SELECT * FROM jada.accounts", "accountName", "permissions");
			if(arg[0].equalsIgnoreCase("show")) {
				//App.printC(client.getClientSocket(),, Account.accounts.size());
				String temp = "";
				for (int i = 0; i < x.size(); i+=2) {
					temp += x.get(i) + " | " + x.get(i+1) + App.nl;
				}
				App.printC(client.getClientSocket(), temp);
			} else if(arg[0].equalsIgnoreCase("addperm")) {
				if(arg.length >= 3) {
					try {
						if(x.contains(arg[1])) {
							for (int i = 0; i < x.size(); i+=2) {
								if(x.get(i).equals(arg[1])) {
									String[] perms = x.get(i+1).split(",");
									for (int j = 0; j < perms.length; j++) {
										if(perms[j].equals(CommandManager.commands.get(i).getCommandPermission())) {
											App.printC(client.getClientSocket(), ErrorManagerList.print("e004"));
											return;
										} else {
											String oldPerm = x.get(i+1);
											String newPerm = oldPerm + "," + arg[2];
											App.d.execute("UPDATE jada.accounts SET permissions='" + newPerm + "' WHERE permissions='" + oldPerm + "'");
											App.printC(client.getClientSocket(), "" + App.nl + oldPerm + App.nl + newPerm + App.nl + "Permission '" + arg[2] + "' added to '" + arg[1] + "'");
										}
									}
								}	
							}
						}
					} catch(java.lang.NullPointerException e) {
						App.printC(client.getClientSocket(), ErrorManagerList.print("e005"));;
					} catch(Exception e) {
						e.printStackTrace();
					}
				} else {
					App.printC(client.getClientSocket(), ErrorManagerList.print("e001") + App.nl +"addperm {%account} {%perm} | removeperm {%account} {%perm} | show | edit {%account} {%var}");
				}
			} else if(arg[0].equalsIgnoreCase("removeperm")) {
				if(arg.length >= 3) {
					try {
						if(x.contains(arg[1])) {
							for (int i = 0; i < x.size(); i+=2) {
								if(x.get(i).equals(arg[1])) {
									String[] perms = x.get(i+1).split(",");
									for (int j = 0; j < perms.length; j++) {
										if(perms[j].equals(CommandManager.commands.get(i).getCommandPermission())) {
											String oldPerm = x.get(i+1);
											oldPerm.replace("," + arg[2], "");
											String newPerm = oldPerm;
											App.d.execute("UPDATE jada.accounts SET permissions='" + newPerm + "' WHERE permissions='" + oldPerm + "'");
											App.printC(client.getClientSocket(), "" + App.nl + oldPerm + App.nl + newPerm + App.nl + "Permission '" + arg[2] + "' removed from '" + arg[1] + "'");
											return;
										} else {
											App.printC(client.getClientSocket(), ErrorManagerList.print("e003") + App.nl + "Given account doesn't have that permission!");
										}
									}
								}	
							}
						}
					} catch(java.lang.NullPointerException e) {
						App.printC(client.getClientSocket(), ErrorManagerList.print("e005"));
					}
				} else {
					App.printC(client.getClientSocket(), ErrorManagerList.print("e001") + App.nl +"addperm {%account} {%perm} | removeperm {%account} {%perm} | show | edit {%account} {%var}");
				}
			}
//			else if(arg[0].equalsIgnoreCase("edit")) {
//				try {
//					try {
//						if(arg.length >= 4) {
//							
//						} else {
//							App.printC(client.getClientSocket(), ErrorManagerList.print("e001") + App.nl + "addperm {%account} {%perm} | removeperm {%account} {%perm} | show | edit {%account} {%var}");
//						}
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				} catch(java.lang.NullPointerException e) {
//					App.printC(client.getClientSocket(), ErrorManagerList.print("e005"));
//				}
//			} else {
//				App.printC(client.getClientSocket(), ErrorManagerList.print("e001") + App.nl + "addperm {%account} {%perm} | removeperm {%account} {%perm} | show | edit {%account} {%var}");
//			}
		} else {
			App.printC(client.getClientSocket(), ErrorManagerList.print("e001") + App.nl + "addperm {%account} {%perm} | removeperm {%account} {%perm} | show | edit {%account} {%var}");
		}
	} // account addperm user perm
	// account edit user pssw neupssw

	@Override
	public String getCommandName() {return commandName;}

	@Override
	public String getCommandDescription() {return commandDescription;}

	@Override
	public String getCommandPermission() {return commandPermission;}
	
	@Override
	public String[] getCommandAlias() {return commandAlias;}
}
