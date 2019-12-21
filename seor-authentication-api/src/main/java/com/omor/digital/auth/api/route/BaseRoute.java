package com.omor.digital.auth.api.route;

import com.omor.digital.auth.api.model.AuthResponse;
import com.omor.digital.sdk.CustomerSDK;
import spark.Request;
import spark.Response;

abstract public class BaseRoute {

	protected CustomerSDK customerSDK = null;
	protected AuthResponse response = null;

	public BaseRoute() {

		customerSDK = getCustomerSDK();
		response = new AuthResponse();
	}

	protected CustomerSDK getCustomerSDK() {
		return new CustomerSDK();
	}

	public abstract AuthResponse setupPostRoute(Request req, Response res);

}
