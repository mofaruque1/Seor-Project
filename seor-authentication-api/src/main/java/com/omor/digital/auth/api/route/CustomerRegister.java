package com.omor.digital.auth.api.route;

import java.io.IOException;

import com.omor.digital.auth.api.model.AuthResponse;
import com.omor.digital.base.api.exception.CustomerExistException;
import com.omor.digital.sdk.model.User;

import spark.Request;
import spark.Response;

public class CustomerRegister extends BaseRoute {

	public CustomerRegister() {
		super();
	}

	public AuthResponse setupPostRoute(Request req, Response res) {
		System.out.println("Register api is getting called");

		String requestBodyString = null;
		try {
			requestBodyString = req.body();
		} catch (Exception e) {
			System.out.println("Invalid request " + e.getMessage());
			throw new IllegalArgumentException("Invalid request " + e.getMessage());
		}
		User user = null;

		try {
			user = User.createObjectFromJson(requestBodyString);
		} catch (IOException e) {
			System.out.println("Invalid request payload. " + e.getMessage());
			throw new IllegalArgumentException("Invalid request payload. " + e.getMessage());
		}

		String tempEmail = user.getEmail();
		String tempPassword = user.getPassword();
		if (tempEmail == null || tempEmail.equals("") || tempPassword == null || tempPassword.equals("")) {
			System.out.println("User email or password missing");
			throw new IllegalArgumentException("User email or password missing. ");
		}
		boolean newUser = customerSDK.registerNewCustomer(user);

		if (!newUser) {
			throw new CustomerExistException("Username already exist");
		} else {
			response.setEmail(user.getEmail());
			response.setName(user.getName());
			response.setStatus("success");
			res.status(200);
			return response;
		}

	}

}
