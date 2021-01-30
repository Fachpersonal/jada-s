package de.MissingNameException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.MissingNameException.Commands.AccountCommand;
import de.MissingNameException.Commands.Changelog;
import de.MissingNameException.Commands.Date;
import de.MissingNameException.Commands.HallOfFame;
import de.MissingNameException.Commands.HappyBirthday;
import de.MissingNameException.Commands.Help;
import de.MissingNameException.Commands.Infos;
import de.MissingNameException.Commands.Janis;
import de.MissingNameException.Commands.Jannik;
import de.MissingNameException.Commands.Shutdown;
import de.MissingNameException.Commands.TODO;
import de.MissingNameException.Commands.Time;

import de.MissingNameException.Errors.ErrorManagerList;
//import de.MissingNameException.Errors.FixErrors;

public class App {
	
	// Created by missingnameexception!
	
	public static String sysHome = "/home/phil/Documents/JADA/";
	
	public static Driver d;
	public static Log log;
	
	public static ArrayList<ClientHandler> clients = new ArrayList<>();	
	
	public static String nl = "<>";
	
	private static ExecutorService pool = Executors.newFixedThreadPool(4);
	
	public static void main(String[] args) throws IOException {
		
		try {
			try {
//				d = new Driver("jdbc:mysql://192.168.1.14:3306/jada", "jada", "jdbc14.2");
				d = new Driver("jdbc:mysql://localhost:3306/jada", "jada", "jdbcl");
			} catch(Exception e) {
//				System.out.println("POMMES!");
				e.printStackTrace();
			}
			ServerSocket ss = null;
			try {
				ss = new ServerSocket(8105);
			} catch (Exception e) {
				e.printStackTrace();
//				System.out.println("SAD");
				System.exit(0);
			}
			
			log = new Log(LocalDate.now());
			
			System.out.println("Server started!");
			log.add("@" + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + " Server started!");
			
//			printC("Connected!");
			try {
//				while(Login.login()) {
//					printC("Sry but the given username does not have permission to use J.A.D.A.!" + nl + "For any support please conntact our [HeadDev] @MNException");
//				}
				
				//Window w = new Window();
				
				//	############################################## /-/ COMMANDS \-\ ##############################################
				CommandManager.clear();
				
				CommandManager.add(new AccountCommand());
				
//				CommandManager.add(new Calendar());
				CommandManager.add(new Changelog());
				
				CommandManager.add(new Date());
				
				CommandManager.add(new ErrorManagerList());
				
//				CommandManager.add(new FixErrors());
				
				CommandManager.add(new HallOfFame());
				CommandManager.add(new HappyBirthday());
				CommandManager.add(new Help());
				
				CommandManager.add(new Infos());
				
				CommandManager.add(new Janis());
				CommandManager.add(new Jannik());
				
				CommandManager.add(new Shutdown());
				
				CommandManager.add(new Time());
				CommandManager.add(new TODO());
				
				
				//	############################################## /-/ COMMANDS \-\ ##############################################
				
				//	############################################## /-/ Accounts \-\ ##############################################
				//AccountManager.add(new Admin());
				//	############################################## /-/ Accounts \-\ ##############################################
			
				while(true) {
					Socket s = ss.accept();
					ClientHandler client = new ClientHandler(s);
					clients.add(client);
					
					pool.execute(client);
				
					System.out.println("Client connected!");
					log.add("@" + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + " Client connected!");
				}
				
//				String clientMsg = "";
//				String[] temp;
//				while(true) { 
//					log = new Log(LocalDate.now());
//					clientMsg = getClientMsg();
//					String x = "@" + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute();
//					System.out.println(x + " Client :: " + clientMsg);
//					log.add(x + " Client :: " + clientMsg);
//					if(clientMsg != null) {
//						temp = splitArg(clientMsg);
//							if(temp == null) {
//								runCommand(clientMsg);
//							} else {
//								runCommand((clientMsg.split(" "))[0], temp);
//							}
//					 }
//				}
			} catch(SocketException e) {
				System.out.println("Client disconnected!");
				log.add("@" + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + " Client disconnected!");
				System.exit(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	public static String[] splitArg(String cmd) {
		String[] x = cmd.split(" ");
		if(x.length == 1) {
			return null;
		}
		String[] result = new String[x.length - 1];
		for (int i = 0; i < x.length - 1; i++) {
			result[i] = x[i + 1];
		}
		return result;
	}
	
	public static void runCommand(ClientHandler client,String cmd, String...arg) throws IOException {
		boolean found = false;
		/*
		if(arg.length > 0 || arg != null) {
			for(int i = 0; i < arg.length; i++) {
				System.out.println("Window.args = " + arg[i]);
			}
		}
		*/
		for (int i = 0; i < CommandManager.commands.size(); i++) {
			if(CommandManager.commands.get(i).getCommandName().equals(cmd.toLowerCase()) || CommandManager.aliasExists(cmd, CommandManager.commands.get(i))) {
//				System.out.println(client.getAccountName());
//				System.out.println(d.StringSELECT("SELECT * FROM jada.accounts", "permissions").get(0));
				if(canRun(CommandManager.commands.get(i).getCommandPermission(), client)) {
//					System.out.println("ZUP");
					if(arg.length > 0) {
						CommandManager.commands.get(i).crun(client, arg);
					} else {
						CommandManager.commands.get(i).crun(client);
					}
					found = true;
//					System.out.println("Point 3");
				}
//				else {
//					System.out.println("NOP");
//				}
			}
		}
		if(!found) {
			printC(client.getClientSocket(), ErrorManagerList.print("e000"));
		}
	}
	
	public static boolean canRun(String permission, ClientHandler client) {
		String x = d.StringSELECT("SELECT * FROM jada.accounts WHERE accountName='" + client.getAccountName() + "'", "permissions").get(0);
//		System.out.println(x + "REQUEST + " + permission);
		if(x.contains(",")) {
			String[] split = x.split(",");
//			System.out.println("OMS");
			for (int i = 0; i < split.length; i++) {
				System.out.println(split[i]);
				if(split[i].equals(permission) || split[i].equals("*")) {
//					System.out.println("TUR");
					return true;
				}
			}
		} else {
			if(x.equals(permission) || x.equals("*")) {
				return true;
			}
		}
		return false;
	}
	
	public static void printC(Socket s,String msg) throws IOException {
		PrintWriter pr = new PrintWriter(s.getOutputStream());
		pr.println(msg);
		pr.flush();
	}
	
	public static String getClientMsg(Socket s) throws IOException {
		 InputStreamReader in = new InputStreamReader(s.getInputStream());
		 BufferedReader bf = new BufferedReader(in);
		 
		 String str = bf.readLine();
		 return str;
	}
}
