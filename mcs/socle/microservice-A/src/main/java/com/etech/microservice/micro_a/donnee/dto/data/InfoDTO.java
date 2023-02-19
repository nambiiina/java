package com.etech.microservice.micro_a.donnee.dto.data;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class InfoDTO{
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String label;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
