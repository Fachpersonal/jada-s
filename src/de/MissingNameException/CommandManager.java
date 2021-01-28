package de.MissingNameException;

import java.util.ArrayList;

import de.MissingNameException.Commands.Command;

public class CommandManager {
	
	public static ArrayList<Command> commands = new ArrayList<Command>();
	
	public static void add(Command c) {
		if(!App.d.StringSELECT("SELECT * FROM jada.commands", "cmd").contains(c.getCommandName().toLowerCase())) {
			App.d.execute("INSERT INTO jada.commands (cmd, description, permission) VALUE ('" + c.getCommandName().toLowerCase() + "', '" + c.getCommandDescription() + "', '" + c.getCommandPermission() + "')");
		}
		commands.add(c);
	}
	
	public static void clear() {
		App.d.execute("DELETE FROM jada.commands");
	}
	
	public static boolean isCommandExsistend(String command) {
		ArrayList<String> allCMD = App.d.StringSELECT("SELECT * FROM jada.commands ORDER BY cmd", "cmd");
		if(allCMD.contains(command)) {
			return true;
		}
		return false;
	}
	
	public static boolean aliasExists(String alias, Command cmd) {
		try {
			for(int i = 0; i < cmd.getCommandAlias().length; i++) {
				if(alias.equalsIgnoreCase(cmd.getCommandAlias()[i])) {
					return true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
}
