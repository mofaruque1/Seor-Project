package com.omor.digital.auth.api.route;

import java.io.IOException;
import com.omor.digital.auth.api.model.AuthResponse;
import com.omor.digital.auth.api.model.SigninRequest;
import com.omor.digital.sdk.model.User;
import com.omor.digital.sdk.utils.Utils;
import spark.Request;
import spark.Response;

public class CustomerSignin extends BaseRoute {


	public CustomerSignin(){
		super();
	}

	public AuthResponse setupPostRoute(Request req, Response res) {
		System.out.println("Signin api is getting called");
		
		String requestBody = null;
		try {
			requestBody = req.body();
		} catch (Exception e) {
			System.out.println("Invalid request "+e.getMessage());
			throw new IllegalArgumentException("Invalid request "+ e.getMessage());
		}
		
		SigninRequest signinRequest = null;
		try {
			signinRequest = SigninRequest.createObjectFromRequest(requestBody);
			
		} catch (IOException e) {
			System.out.println("Invalid request payload. "+e.getMessage());
			throw new IllegalArgumentException("Invalid request payload. "+ e.getMessage());
		}
		
		String email = signinRequest.getEmail();
		String password = signinRequest.getPassword();

		
		User user = customerSDK.signinCustomer(email, password);
		Utils.throwIfNullObject(user, "Wrong username or password");
		
		response.setEmail(email);
		response.setName(user.getName());
		response.setStatus("success");
		
		return response;
	}
}
