package com.chatting.project.websocket;

public class HelloMessage {
	private String name;
	
	public HelloMessage() {
	}

	public HelloMessage(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}