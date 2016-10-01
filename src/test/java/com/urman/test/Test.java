package com.urman.test;

import java.util.EnumSet;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.ws.rs.Path;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.codahale.metrics.health.HealthCheck;
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

    	@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
    	Map<String,HealthCheck> healthChecks=context.getBeansOfType(HealthCheck.class);
    	for(Map.Entry<String,HealthCheck> entry : healthChecks.entrySet()) {
            environment.healthChecks().register("template", entry.getValue());
        }
    	Map<String,Object> resources = context.getBeansWithAnnotation(Path.class);
        for(Map.Entry<String,Object> entry : resources.entrySet()) {
            environment.jersey().register(entry.getValue());
        }
    	/*final TestService service=new TestService();
    	final TemplateHealthCheck healthCheck =
    	        new TemplateHealthCheck(configuration.getTemplate());
    	    environment.healthChecks().register("template", healthCheck);
    	environment.jersey().register(service);*/
    	// Enable CORS headers
        final FilterRegistration.Dynamic cors =
            environment.servlets().addFilter("cors", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }
}
