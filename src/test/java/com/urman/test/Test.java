package com.urman.test;

import com.urman.services.TestService;
import com.urman.util.ServiceConfiguration;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class Test extends Application<ServiceConfiguration>{

	
	
	public static void main(String[] args) {
		try {
			new Test().run(args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

/*	@Override
    public void initialize(Bootstrap<ServiceConfiguration> bootstrap) {

	}*/
	
	@Override
	public void run(ServiceConfiguration arg0, Environment env) throws Exception {

		env.jersey().register(new TestService());
		
	}
}
