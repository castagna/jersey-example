package com.talis.labs.jersey;

public class Message {

	private final String message;
	
	public Message() {
		message = "Hello World!";
	}
	
	public Message(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
	
}
