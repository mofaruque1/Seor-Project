package com.omor.digital.base.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {

	static Logger logger = LoggerFactory.getLogger(RequestStreamLambdaHandler.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public String render(Object model) {
		try {
			return mapper.writeValueAsString(model);
		} catch (JsonProcessingException e) {
			logger.error("Cannot serialize object", e);
			return null;
		}
	}

}
