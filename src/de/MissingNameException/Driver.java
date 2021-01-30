package de.MissingNameException;

import java.io.IOException;

import java.sql.*;

import java.util.ArrayList;

public class Driver {
	
	@SuppressWarnings("unused")
	private String url;
	@SuppressWarnings("unused")
	private String user;
	@SuppressWarnings("unused")
	private String pssw;
	
	private Connection myConn;
	private Statement myStmt;
	private ResultSet myRs;
	
	public Driver(String url, String user, String pssw) throws IOException {
		this.url = url;
		this.user = user;
		this.pssw = pssw;
		try {
			//1. Get connection to database
			myConn = DriverManager.getConnection(url, user, pssw);
			//2. Create Statement
			myStmt = myConn.createStatement();
		} catch (Exception e) {
//			App.printC(s, "J.A.D.A. Failed to launch due to offline database or internet, for support please contact [HeadDev] @MNException");
			System.exit(0);
		}
	}
	
	public void execute(String cmd) {
		try {
			myStmt.executeUpdate(cmd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<String> StringSELECT(String cmd, String... strings) {
		ArrayList<String> result = new ArrayList<String>();
		try {
			myRs = myStmt.executeQuery(cmd);
			while(myRs.next()) {
				for(int i = 0; i < strings.length; i++) {
					result.add(myRs.getString(strings[i]));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
