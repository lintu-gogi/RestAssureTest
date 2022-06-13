package com.test.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class DeserializationPogo {

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonPropertyOrder({
	"status"})
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("status")
	public String getStatus() {
	return status;
	}

	@JsonProperty("status")
	public void setStatus(String status1) {
	this.status = status1;
	}
	
}
