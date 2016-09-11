package com.urman.util;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;

public class ServiceConfiguration extends Configuration{
	
	 	@NotEmpty
	    private String template;

	    @NotEmpty
	    private String defaultName = "Stranger";

	    @JsonProperty
	    public String getTemplate() {
	        return template;
	    }

	    @JsonProperty
	    public void setTemplate(String template) {
	        this.template = template;
	    }

	    @JsonProperty
	    public String getDefaultName() {
	        return defaultName;
	    }

	    @JsonProperty
	    public void setDefaultName(String name) {
	        this.defaultName = name;
	    }

}
