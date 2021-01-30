package de.MissingNameException;

import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Reminder {

	private String reminderID;
	private String reminderLabel;	
	private String reminderDescription;
	
	private LocalDate reminderDate;
	
	private ClientHandler client;
	
	public Reminder(String reminderID ,String reminderLabel, String reminderDescription, LocalDate reminderDate, ClientHandler client) throws IOException {
		this.reminderLabel = reminderLabel;
		this.reminderDescription = reminderDescription;
		this.reminderDate = reminderDate;
		this.client = client;
		this.reminderID = reminderID;
		App.d.execute("INSERT INTO jada.reminder (id, label, description, date, user) VALUE ('" + reminderID + "','" + reminderLabel + "','" + reminderDescription + "','" + reminderDate.getYear() + "-" + reminderDate.getMonthValue() + "-" + reminderDate.getDayOfMonth() + "','" + client.getAccountName() + "')");
		App.log.add("@" + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + " Client 'SYSTEM' :: Created reminder '" + reminderLabel + "'");
	}

	public static String showReminders(ClientHandler client) throws IOException {
		ArrayList<String> reminder = App.d.StringSELECT("SELECT * FROM jada.reminder WHERE user='" + client.getAccountName() + "'", "id", "label" + "description" + "date"); 
		String x = "    :: REMINDER :: " + App.nl + App.nl + "";
		for (int i = 0; i < reminder.size(); i+=5) {
			x += reminder.get(i) + " | " + reminder.get(i+1) + " | " + reminder.get(i+2) + " | " + reminder.get(i+3) + " | " + reminder.get(i+4) + App.nl;
		}
		return x;
	}
	
	public static void removeReminder(int reminderID) throws IOException {
		App.d.execute("DELETE FROM jada.reminder WHERE id = " + reminderID);
		App.log.add("@" + LocalTime.now().getHour() + ":" + LocalTime.now().getMinute() + " Client 'SYSTEM' :: Reminder removed (=ID)'" + reminderID + "'");
	}
	
	public String getReminderID() {return reminderID;}
	public String getReminderLabel() {return reminderLabel;}
	public String getReminderDescription() {return reminderDescription;}
	
	public LocalDate getReminderDate() {return reminderDate;}
	
	public ClientHandler getClient() {return client;}
	
}
