package com.iktpreobuka.elektronskidnevnik.entities.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRegisterDto {

	@JsonProperty("name") 
	private String name; 
	
	@JsonProperty("email") 
	private String email;

	public UserRegisterDto(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

	public UserRegisterDto() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
