package com.urman.util;

import javax.validation.constraints.NotNull;

public class MessageConfiguration {
	
	@NotNull
	private String hello;

	public String getHello() {
		return hello;
	}

	public void setHello(String hello) {
		this.hello = hello;
	}
	
	

}
