package de.MissingNameException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.Socket;

import java.time.LocalDate;
import java.time.LocalTime;

import de.MissingNameException.Login.Login;

public class ClientHandler implements Runnable{

	private String accountName = null;
	
	private Socket client;
	
	public ClientHandler(Socket client) {
		this.client = client;
	}
	
	@Override
	public void run() {
		try {
			while(Login.login(this)) {
				printC("Sry but the given username does not have permission to use J.A.D.A.!" + App.nl + "For any support please conntact our [HeadDev] @MNException");
			}
			
			String clientMsg = "";
			String[] temp;
			while(true) { 
				App.log = new Log(LocalDate.now());
				clientMsg = App.getClientMsg(client);
				String x = "@" + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute();
				System.out.println(x + " Client '" + accountName + "' :: " + clientMsg);
				App.log.add(x + " Client '" + accountName + "' :: " + clientMsg);
//				System.out.println(x + " Client x :: " + clientMsg);
//				App.log.add(x + " Client x :: " + clientMsg);
				
				if(clientMsg != null) {
					temp = App.splitArg(clientMsg);
						if(temp == null) {
							App.runCommand(this, clientMsg);
						} else {
							App.runCommand(this,(clientMsg.split(" "))[0], temp);
						}
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void printC(String msg) throws IOException {
		PrintWriter pr = new PrintWriter(client.getOutputStream());
		pr.println(msg);
		pr.flush();
	}
	
	public String getClientMsg() throws IOException {
		 InputStreamReader in = new InputStreamReader(client.getInputStream());
		 BufferedReader bf = new BufferedReader(in);
		 
		 String str = bf.readLine();
		 return str;
	}
	
	public Socket getClientSocket() {return client;}
	
	public ClientHandler getClient() {return this;}
	
	public String getAccountName() {return accountName;}
}
