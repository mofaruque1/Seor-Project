package com.omor.digital.auth.api;

import com.omor.digital.auth.api.model.AuthResponse;
import com.omor.digital.auth.api.route.CustomerRegister;
import com.omor.digital.base.api.JsonTransformer;
import com.omor.digital.base.api.RequestStreamLambdaHandler;
import static spark.Spark.get;
import static spark.Spark.post;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticationLambdaHandler extends RequestStreamLambdaHandler {

	static Logger logger = LoggerFactory.getLogger(AuthenticationLambdaHandler.class);

	@Override
	public void defineResources() {
		get("/urlone", (req, res) -> {

			res.status(200);
			return "{ \"color\" : \"Black\", \"type\" : \"BMWWW\" }";
		});

		post("/register", (req, res) -> {

			AuthResponse response = new CustomerRegister().setupPostRoute(req, res);
			return new JsonTransformer().render(response);

		});

	}


}
