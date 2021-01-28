package de.MissingNameException.Login;

import java.io.IOException;

import de.MissingNameException.App;
import de.MissingNameException.ClientHandler;
import de.MissingNameException.Driver;

public class Login {
	
	public static boolean login(ClientHandler client) throws IOException {
		String input, input2;
		String vers = "v0.6 indev";
		String logo = "                              ;                              " + App.nl +
				"                              ED.                            " + App.nl +
				"                              E#Wi                           " + App.nl +
				"  itttttttt                   E###G.                         " + App.nl +
				"  fDDK##DDi             ..    E#fD#W;                  ..    " + App.nl +
				"     t#E               ;W,    E#t t##L                ;W,    " + App.nl +
				"     t#E              j##,    E#t  .E#K,             j##,    " + App.nl +
				"     t#E             G###,    E#t    j##f           G###,    " + App.nl +
				"     t#E           :E####,    E#t    :E#K:        :E####,    " + App.nl +
				"     t#E          ;W#DG##,    E#t   t##L         ;W#DG##,    " + App.nl +
				"   jfL#E         j###DW##,    E#t .D#W;         j###DW##,    " + App.nl +
				"   :K##E        G##i,,G##,    E#tiW#G.         G##i,,G##,    " + App.nl +
				"     G#E      :K#K:   L##,    E#K##i         :K#K:   L##,    " + App.nl +
				"      tE     ;##D.    L##,    E##D.         ;##D.    L##,    " + App.nl +
				"       .    .,,,      .,,     E#t          .,,,      .,,     " + App.nl +
				"                              L:                             " + App.nl;
		
		App.printC(client.getClientSocket(), "Connected" + App.nl + App.nl + logo + App.nl + "    " + vers +" created by @MNException" + App.nl + " " + App.nl + " " + App.nl + " " + App.nl + "username :: ");
		
		input = App.getClientMsg(client.getClientSocket());
		
		App.printC(client.getClientSocket(), " " + App.nl + "password for " + input + " :: ");
				
		input2 = App.getClientMsg(client.getClientSocket());
		
		Driver d = App.d;
		
		for (int i = 0; i < d.StringSELECT("SELECT * FROM jada.accounts", "accountName").size(); i++) {
			if(d.StringSELECT("SELECT * FROM jada.accounts", "accountName").get(i).equals(input)) {
				if(d.StringSELECT("SELECT accountPassword FROM jada.accounts WHERE accountName='" + input + "'", "accountPassword").get(0).equals(input2)) {
					client.setClientAcc(new Account(input, (d.StringSELECT("SELECT permissions FROM jada.accounts WHERE accountName='" + input + "'", "permissions").get(0)).split(", ")));					
					String x = "" + App.nl;
					for (int j = 0; j < 40; j++) {
						x += " " + App.nl;
					}
					x += "Successfully logged in!" + App.nl + "    Logged in as '" + input + "' " + App.nl;
					App.printC(client.getClientSocket(), x);
					Account.updateAccounts();
					return false;
				}
			}
		}
		return true;
	}
}
