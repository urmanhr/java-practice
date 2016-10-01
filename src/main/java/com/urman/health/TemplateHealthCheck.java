package com.urman.health;

import org.springframework.stereotype.Component;

import com.codahale.metrics.health.HealthCheck;

@Component
public class TemplateHealthCheck extends HealthCheck{

	private String template;
	
	public TemplateHealthCheck(String template) {
        this.template = template;
    }
	
	public TemplateHealthCheck() {
    }

	@Override
	protected Result check() throws Exception {
		final String saying = String.format(template, "TEST");
        if (!saying.contains("TEST")) {
            return Result.unhealthy("template doesn't include a name");
        }
        return Result.healthy();
	}

	public String getTemplate() {
		return template;
	}
	
	

}
