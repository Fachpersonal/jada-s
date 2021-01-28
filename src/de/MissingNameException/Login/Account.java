package de.MissingNameException.Login;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import de.MissingNameException.App;
import de.MissingNameException.Commands.Command;
import de.MissingNameException.Errors.ErrorManagerList;

public class Account {
	
	private String accountName;
	private ArrayList<String> permission = new ArrayList<String>();
	
	public static ArrayList<Account> accounts = new ArrayList<Account>();
	
	public Account(String accountName, ArrayList<String> strings) {
		this.accountName = accountName;
		permission = strings;
	}
	
	public Account(String accountName, String[] strings) {
		this.accountName = accountName;
		for (int i = 0; i < strings.length; i++) {
			permission.add(strings[i]);
		}
	}
	
	public static void updateAccounts() {
		String name = null;
		ArrayList<String> perm = new ArrayList<String>();
		for (int i = 0; i < App.d.StringSELECT("SELECT * FROM jada.accounts", "accountName").size(); i++) {
			name = App.d.StringSELECT("SELECT * FROM jada.accounts", "accountName").get(i);
			perm = App.d.StringSELECT("SELECT * FROM jada.accounts WHERE accountName='" + name + "'", "permissions");
			accounts.add(new Account(name, perm));
		}
	}

	public static Account getSpecificAccount(String accountName) {
		Account wanted = null;
		for (int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).getAccountName().equalsIgnoreCase(accountName)) {
				wanted = accounts.get(i);
			}
		}
		return wanted;
	}
	
	public static boolean hasPermission(Socket s, Command cmd, Account acc) throws IOException {
		for(int i = 0; i < acc.getPermission().size(); i++) {
			if(acc.getPermission().get(i).equalsIgnoreCase(cmd.getCommandPermission())) {
				return true;
			} else if(acc.getPermission().get(i).equalsIgnoreCase("*")){
				return true;
			} else {
				App.printC(s, ErrorManagerList.print("e003"));
			}
		}
		return false;
	}

	public static boolean canExecute(String string, Account acc) {
		for (int i = 0; i < acc.getPermission().size(); i++) {
			if(acc.getPermission().get(i).equalsIgnoreCase(string)) {
				return true;
			} else if(acc.getPermission().get(i).equalsIgnoreCase("*")){
				return true;
			}
		}
		return false;
	}
	
	public String getAccountName() {return accountName;}
	
	public ArrayList<String> getPermission() {return permission;}
	
	public String getPermissions() {
		String result = "";
		for (int i = 0; i < permission.size(); i++) {
			result += permission.get(i) + " ";
		}
		return result;
	}
	
	public void edit(Socket s, String var, String value) throws IOException {
		try {
			if(var.equalsIgnoreCase("username")) {
				App.d.execute("UPDATE jada.accounts SET accountName='" + value + "' WHERE accountName='" + accountName + "'");
				App.printC(s, "AccountName updated!");
				accountName = value;
			} else if(var.equalsIgnoreCase("password")) {
				App.d.execute("UPDATE jada.accounts SET accountPassword='" + value + "' WHERE accountPassword='" + App.d.StringSELECT("SELECT * FROM jada.accounts WHERE accountName='" + accountName + "'", "accountPassword").get(0) + "'");
				App.printC(s, "AccountPassword updated!");
			} else {
				App.printC(s, ErrorManagerList.print("e001") + App.nl + "addperm {%account} {%perm} | removeperm {%account} {%perm} | show | edit {%account} {%var}");
			}
		} catch(java.lang.NullPointerException e) {
			App.printC(s, ErrorManagerList.print("e005"));
		}
	}
	
	public String toString() {
		String result = accountName + " | " + getPermissions();
		return result;
	}
	
}
