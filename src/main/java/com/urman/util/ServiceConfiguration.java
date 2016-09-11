package com.urman.util;

import javax.validation.Valid;

import io.dropwizard.Configuration;

public class ServiceConfiguration extends Configuration{
	
	@Valid
	private MessageConfiguration messages;

	public MessageConfiguration getMessages() {
		return messages;
	}

	public void setMessages(MessageConfiguration messages) {
		this.messages = messages;
	}
	
	

}
