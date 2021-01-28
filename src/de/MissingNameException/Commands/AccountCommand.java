package de.MissingNameException.Commands;

import java.io.IOException;

import de.MissingNameException.App;
import de.MissingNameException.ClientHandler;
import de.MissingNameException.Errors.ErrorManagerList;
import de.MissingNameException.Login.OldAccount;

public class AccountCommand implements Command{
	
	private String commandName = "account";
	private String commandDescription = "Allows to create Accounts!";
	private String commandPermission = "admin"; // change if commands needs a permission!
	private String[] commandAlias = {"acc"};
	
	@Override
	public void crun(ClientHandler client, String...arg) throws IOException {
		if(arg.length > 0) {
			if(arg[0].equalsIgnoreCase("show")) {
				//App.printC(client.getClientSocket(),, Account.accounts.size());
				String x = "";
				for (int i = 0; i < OldAccount.accounts.size(); i++) {
					x += (OldAccount.accounts.get(i).toString()) + App.nl;
				}
				App.printC(client.getClientSocket(), x);
			} else if(arg[0].equalsIgnoreCase("addperm")) {
				if(arg.length >= 3) {
					try {
						if(OldAccount.canExecute(arg[2], OldAccount.getSpecificAccount(arg[1]))) {
							App.printC(client.getClientSocket(), ErrorManagerList.print("e004"));;
						} else {
							String oldPerm = OldAccount.getSpecificAccount(arg[1]).getPermissions();
							OldAccount.getSpecificAccount(arg[1]).getPermission().add(arg[2]);
							String newPerm = OldAccount.getSpecificAccount(arg[1]).getPermissions();
							App.d.execute("UPDATE jada.accounts SET permissions='" + newPerm + "' WHERE permissions='" + oldPerm + "'");
							App.printC(client.getClientSocket(), "" + App.nl + oldPerm + App.nl + newPerm + App.nl + "Permission '" + arg[2] + "' added to '" + arg[1] + "'");
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
						if(OldAccount.canExecute(arg[2], OldAccount.getSpecificAccount(arg[1]))) {
							OldAccount acc = OldAccount.getSpecificAccount(arg[1]);
							String oldPerm = acc.getPermissions();
							acc.getPermission().remove(arg[2]);
							String newPerm = acc.getPermissions();
							App.d.execute("UPDATE jada.accounts SET permissions='" + newPerm + "' WHERE permissions='" + oldPerm + "'");
							App.printC(client.getClientSocket(), "" + App.nl + oldPerm + App.nl + newPerm + App.nl + "Permission '" + arg[2] + "' removed from '" + arg[1] + "'");
						} else {
							App.printC(client.getClientSocket(), ErrorManagerList.print("e003") + App.nl + "Given account doesn't have that permission!");
						}
					} catch(java.lang.NullPointerException e) {
						App.printC(client.getClientSocket(), ErrorManagerList.print("e005"));
					}
				} else {
					App.printC(client.getClientSocket(), ErrorManagerList.print("e001") + App.nl +"addperm {%account} {%perm} | removeperm {%account} {%perm} | show | edit {%account} {%var}");
				}
			} else if(arg[0].equalsIgnoreCase("edit")) {
				try {
					try {
						if(arg.length >= 4) {
							OldAccount.getSpecificAccount(arg[1]).edit(client.getClientSocket(), arg[2], arg[3]);
						} else {
							App.printC(client.getClientSocket(), ErrorManagerList.print("e001") + App.nl + "addperm {%account} {%perm} | removeperm {%account} {%perm} | show | edit {%account} {%var}");
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch(java.lang.NullPointerException e) {
					App.printC(client.getClientSocket(), ErrorManagerList.print("e005"));
				}
			} else {
				App.printC(client.getClientSocket(), ErrorManagerList.print("e001") + App.nl + "addperm {%account} {%perm} | removeperm {%account} {%perm} | show | edit {%account} {%var}");
			}
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
