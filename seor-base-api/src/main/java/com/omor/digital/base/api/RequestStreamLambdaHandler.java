package com.omor.digital.base.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spark.SparkLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.omor.digital.base.api.exception.CustomerExistException;
import com.omor.digital.base.api.exception.NotFoundException;
import com.omor.digital.base.api.model.ErrorResponse;

import spark.Request;
import spark.Response;
import spark.Spark;

import static spark.Spark.before;
import static spark.Spark.after;
import static spark.Spark.exception;

public abstract class RequestStreamLambdaHandler implements RequestStreamHandler {

	private static SparkLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
	static Logger logger = LoggerFactory.getLogger(RequestStreamLambdaHandler.class);
	private boolean isInitialized = false;

	@Override
	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {

		if (!isInitialized) {
			isInitialized = true;
			try {
				logger.info("Initializing");
				handler = SparkLambdaContainerHandler.getAwsProxyHandler();
				defineBaseResources();
				defineResources();
				Spark.awaitInitialization();
				logger.info("Initialization completed");
			} catch (ContainerInitializationException e) {
				logger.error("Cannot initialize Spark application", e);
				return;
			}

		}

		handler.proxyStream(input, output, context);
	}

	public abstract void defineResources();

	public void defineBaseResources() {
		logger.info("Defining base spark resources");
		before("/*", this::setBeforeFilter);
		after("/*", this::setAfterFilter);

		// Exception filters
		exception(NotFoundException.class, this::setNotFoundExceptionFilter);
		exception(CustomerExistException.class, this::setAlreadyExistExceptionFilter);
		exception(Exception.class, this::setUnhandledExceptionFilter);

	}

	protected void setBeforeFilter(Request req, Response res) throws Exception {
		String method = req.requestMethod();
		logger.info("Request URL : {}, Method : {}", req.url(), method);

		if (method.equalsIgnoreCase("POST")) {
			logger.info("Request Body : {}", req.body());
		}
		res.type("application/json");
		res.header("access-control-allow-headers",
				"Content-Type,X-Amz-Date,Authorization,X-Api-Key,X-Amz-Security-Token");
		res.header("access-control-allow-methods", "POST,GET,PUT,DELETE,OPTIONS");
		res.header("access-control-allow-origin", "*");

	}

	protected void setAfterFilter(Request req, Response res) throws Exception {
		logger.info("Response: {}", res.body());
	}

	protected void setNotFoundExceptionFilter(NotFoundException exception, Request req, Response res) {
		logger.error("Not found exception", exception);
		ErrorResponse err = new ErrorResponse(404, exception);
		res.status(404);
		res.body(new JsonTransformer().render(err));
	}

	protected void setAlreadyExistExceptionFilter(CustomerExistException exception, Request req, Response res) {
		logger.error("Customer exist exception", exception);
		ErrorResponse err = new ErrorResponse(404, exception);
		res.status(404);
		res.body(new JsonTransformer().render(err));
	}

	protected void setUnhandledExceptionFilter(Exception exception, Request req, Response res) {
		logger.info("Exception", exception);
		ErrorResponse err = new ErrorResponse(500, exception);
		res.status(500);
		res.body(new JsonTransformer().render(err));

	}

}
