package com.omor.digital.sdk;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.omor.digital.sdk.model.User;
import com.omor.digital.sdk.utils.Utils;

public class CustomerSDK {

	private SEORClient client = null;
	private AmazonDynamoDB dynamoDB = null;
	private User user = null;
	private DynamoDBMapper mapper = null;

	public CustomerSDK(SEORClient client) {

		Utils.throwIfNullObject(client, "SEORClient object cannot be null [CustomerSDK.java]");
		this.client = client;
		this.dynamoDB = this.client.getDynamoDB();
		Utils.throwIfNullObject(this.dynamoDB, "AmazonDynamoDB reference object cannot be NULL! [CustomerSDK.java]");
		mapper = new DynamoDBMapper(this.dynamoDB);

	}

	public boolean registerNewCustomer(User user) {
		User existingUser = mapper.load(User.class, user.getEmail());
		if (Utils.checkIfNullObject(existingUser)) {
			mapper.save(user);
			return true;
		}
		return false;
	}
	
	public User signinCustomer(String email, String password) {
		User existingUser = mapper.load(User.class, email);
		if (!Utils.checkIfNullObject(existingUser) && existingUser.getPassword().contentEquals(password)) {
			return existingUser;
		}
		return null;
	}

	public SEORClient getClient() {
		return client;
	}

	public void setClient(SEORClient client) {
		this.client = client;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
