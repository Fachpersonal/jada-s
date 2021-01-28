package de.MissingNameException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;

public class Log{
	
//	String dir = "C:\\Users\\Nutzer\\Documents\\JADA-Log\\"; //WINDOWS
	String dir = App.sysHome + "JADA-Log/"; //LINUX
	
	File file;
	
	public Log(LocalDate date) throws IOException {
		file = new File(dir + date.getDayOfMonth() + "-" + date.getMonthValue() + "-" + date.getYear() + ".log");
		if(file.createNewFile()) {
			System.out.println("New Log file created!");
			add(date.getDayOfMonth() + "-" + date.getMonthValue() + "-" + date.getYear() + ".log");
		}
	}
	
	public void add(String log) throws IOException {
		BufferedWriter fw = new BufferedWriter(new FileWriter(file, true));
		fw.write(log);
		fw.newLine();
		fw.close();
	}
	
	public File getLog() {return file;}
}
