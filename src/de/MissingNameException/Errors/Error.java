package de.MissingNameException.Errors;

public class Error {
	
	private String ErrorName;
	private String ErrorMessage;
	
	public Error(String ErrorMessage, String ErrorName) {
		this.ErrorMessage = ErrorMessage;
		this.ErrorName = ErrorName;
	}
	
	public String getErrorName() {return ErrorName;}
	public String getErrorMessage() {return ErrorMessage;}
	
	public String toString() {return "Error : " + ErrorName + " >>  " + ErrorMessage;}
}
