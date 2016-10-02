package com.urman.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Model {

	
	private String name;
	
	private String surName;
	
	private Integer age;
	
	public Model(){
		
	}
	
	public Model(String name, String surName, Integer age) {
		super();
		this.name = name;
		this.surName = surName;
		this.age = age;
	}

	@JsonProperty
	public String getName() {
		return name;
	}

	@JsonProperty
	public String getSurName() {
		return surName;
	}
	
	@JsonProperty
	public Integer getAge() {
		return age;
	}

	@Override
	public String toString() {
		return "Model [name=" + name + ", surName=" + surName + ", age=" + age + "]";
	}

	
	
	
	
	
}
