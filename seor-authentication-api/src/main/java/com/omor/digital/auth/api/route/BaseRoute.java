package com.omor.digital.auth.api.route;

import com.omor.digital.auth.api.model.AuthResponse;
import com.omor.digital.sdk.CustomerSDK;
import com.omor.digital.sdk.SEORClient;

import spark.Request;
import spark.Response;

abstract public class BaseRoute {
	private SEORClient client = null;
	protected CustomerSDK customerSDK = null;
	protected AuthResponse response = null;

	public BaseRoute() {
		client = getSeorClient();
		customerSDK = getCustomerSDK(client);
		response = new AuthResponse();
	}

	protected SEORClient getSeorClient() {
		return new SEORClient.Builder().build();
	}

	protected CustomerSDK getCustomerSDK(SEORClient client) {
		return new CustomerSDK(client);
	}

	public abstract AuthResponse setupPostRoute(Request req, Response res);

}
