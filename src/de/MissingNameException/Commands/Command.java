package de.MissingNameException.Commands;

import java.io.IOException;

import de.MissingNameException.ClientHandler;

public interface Command {
	
	public void crun(ClientHandler client, String...arg) throws IOException;
	
	public String getCommandName();
	public String getCommandDescription();
	public String getCommandPermission();
	public String[] getCommandAlias();
}
