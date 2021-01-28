package de.MissingNameException;

import java.time.LocalDate;

import java.util.ArrayList;

public class Reminder {

	private String reminderLabel;
	
	private String reminderDescription;
	
	private LocalDate reminderDate;
	
	private ClientHandler client;
	
	public static ArrayList<Reminder> reminders = new ArrayList<Reminder>();
	
	public Reminder(String reminderLabel, String reminderDescription, LocalDate reminderDate, ClientHandler client) {
		this.reminderLabel = reminderLabel;
		this.reminderDescription = reminderDescription;
		this.reminderDate = reminderDate;
		this.client = client;
		reminders.add(this);
	}

	public static void showReminders() {
		for (int i = 0; i < reminders.size(); i++) {
			System.out.println(reminders.get(i).getReminderLabel() + "," + reminders.get(i).getReminderDescription() + "," + reminders.get(i).getReminderDate().getDayOfMonth() + "." + reminders.get(i).getReminderDate().getMonthValue() + "." + reminders.get(i).getReminderDate().getYear() + "," + reminders.get(i).getClient());
		}
	}
	
	public String getReminderLabel() {return reminderLabel;}
	public String getReminderDescription() {return reminderDescription;}
	
	public LocalDate getReminderDate() {return reminderDate;}
	
	public ClientHandler getClient() {return client;}
	
}
