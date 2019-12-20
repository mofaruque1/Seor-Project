package com.omor.digital.auth.api.route;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.omor.digital.auth.api.model.AuthResponse;
import com.omor.digital.base.api.exception.CustomerExistException;
import com.omor.digital.sdk.CustomerSDK;
import com.omor.digital.sdk.SEORClient;
import com.omor.digital.sdk.model.User;

import spark.Request;
import spark.Response;

public class CustomerRegister {
	
	public AuthResponse setupPostRoute(Request req, Response res) {
		System.out.println("Register api is getting called");
		AuthResponse response = new AuthResponse();
		String requestBodyString = req.body();
		User user = null;
		boolean newUser;
		try {
			user = User.createObjectFromJson(requestBodyString);
			SEORClient client = new SEORClient.Builder().build();
			CustomerSDK customerSDK = new CustomerSDK(client);
			newUser = customerSDK.registerNewCustomer(user);
		} catch (JsonParseException e) {
			throw new IllegalArgumentException("Json Parse exception");
		} catch (JsonMappingException e) {
			
			throw new IllegalArgumentException("Json Mapping exception");
		} catch (Exception e) {
			
			throw new IllegalArgumentException("Something went wrong");
		}

		if (!newUser) {
			throw new CustomerExistException("Username already exist");
		}
		response.setEmail(user.getEmail());
		response.setName(user.getName());
		response.setStatus("success");
		res.status(200);
		return response;
		
	}

}
