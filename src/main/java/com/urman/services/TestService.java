package com.urman.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.urman.model.Model;
import com.urman.util.ServiceConfiguration;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class TestService{

	@GET
	@Path("u/form")
	@Produces(MediaType.APPLICATION_JSON)
	public Model testMethod(){
		
		Model model=new Model();
		
		model.setName("urman");
		model.setSurName("ratneshwar");
		model.setAge(25);
		
		return model;
	}

}
