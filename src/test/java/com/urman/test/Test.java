package com.urman.test;

import com.urman.health.TemplateHealthCheck;
import com.urman.services.TestService;
import com.urman.util.ServiceConfiguration;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class Test extends Application<ServiceConfiguration>{

	
	
	public static void main(String[] args) throws Exception {
        new Test().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<ServiceConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(ServiceConfiguration configuration,
                    Environment environment) {
        // nothing to do yet
    	final TestService service=new TestService();
    	final TemplateHealthCheck healthCheck =
    	        new TemplateHealthCheck(configuration.getTemplate());
    	    environment.healthChecks().register("template", healthCheck);
    	environment.jersey().register(service);
    }
}
