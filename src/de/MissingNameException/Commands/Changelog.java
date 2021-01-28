package de.MissingNameException.Commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

import de.MissingNameException.App;
import de.MissingNameException.ClientHandler;
import de.MissingNameException.Errors.ErrorManagerList;

public class Changelog implements Command{
	
	private String commandName = "changelog";
	private String commandDescription = "Shows system history!";
	private String commandPermission = "default"; // change if commands needs a permission!
	private String[] commandAlias = {"ch", "log"};
	
	@Override
	public void crun(ClientHandler client, String...arg) throws IOException {
		
		URL url = new URL("http://localhost/CHANGELOGS.CL");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {

            String line;
            
            String x = "";
            
            while ((line = br.readLine()) != null) {
            	x += line + App.nl;
            }
            
            App.printC(client.getClientSocket(), x);
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
