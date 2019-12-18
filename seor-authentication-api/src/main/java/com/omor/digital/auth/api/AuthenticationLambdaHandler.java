package com.omor.digital.auth.api;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.omor.digital.auth.api.model.User;
import com.omor.digital.base.api.RequestStreamLambdaHandler;
import com.omor.digital.sdk.SEORClient;

import static spark.Spark.get;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticationLambdaHandler extends RequestStreamLambdaHandler {

	static Logger logger = LoggerFactory.getLogger(AuthenticationLambdaHandler.class);
	@Override
	public void defineResources() {
		get("/urlone", (req, res) -> {
			
			
			logger.info("In the Authentication Lambda handler");
			insertInDynamo();
			logger.info("After inserting the data");
			res.status(200);
			return "{ \"color\" : \"Black\", \"type\" : \"BMWWW\" }";
		});
		
		

	}
	
	
	// Need to work with post register api
	private void insertInDynamo() {
		User user = new User();
		user.setName("Omor Faruque");
		user.setAddress("8 corvette avenue");
		user.setEmail("abc@gmail.com");
		user.setPassword("12345678");
		user.setPhoneNo("6477716627");
		
		SEORClient client = new SEORClient.Builder().build();
		DynamoDBMapper mapper = new DynamoDBMapper(client.getDynamoDB());
		mapper.save(user);
		
		
		
		
		
	}

}