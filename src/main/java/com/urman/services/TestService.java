package com.urman.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.urman.model.Model;

@Path("u/form")
public class TestService{

	@GET
	@Path("/get1")
	@Produces(MediaType.APPLICATION_JSON)
	public Model testMethod(){
		
		Model model=new Model("urman","ratneshwar",25);
		
		return model;
	}

}
