package com.omor.digital.auth.api.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SigninRequest {
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String emailString) {
		this.email = emailString;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public static SigninRequest createObjectFromRequest(String jsonString) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(jsonString, SigninRequest.class);
	}
	
	private String email;
	private String password;

}
