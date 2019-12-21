package com.omor.digital.sdk;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.omor.digital.sdk.model.User;
import com.omor.digital.sdk.utils.Utils;

public class CustomerSDK {

	private SEORClient client = null;
	private AmazonDynamoDB dynamoDB = null;
	private User user = null;
	private DynamoDBMapper ddbTableMapper = null;

	

	public CustomerSDK() {
		this.client = getSeorClient();
		Utils.throwIfNullObject(this.client, "SEORClient object cannot be null [CustomerSDK.java]");
		this.dynamoDB = this.client.getDynamoDB();
		Utils.throwIfNullObject(this.dynamoDB, "AmazonDynamoDB reference object cannot be NULL! [CustomerSDK.java]");
		ddbTableMapper = new DynamoDBMapper(this.dynamoDB);

	}

	public boolean registerNewCustomer(User user) {
		User existingUser = ddbTableMapper.load(User.class, user.getEmail());
		if (Utils.checkIfNullObject(existingUser)) {
			ddbTableMapper.save(user);
			return true;
		}
		return false;
	}
	
	public User signinCustomer(String email, String password) {
		User existingUser = ddbTableMapper.load(User.class, email);
		if (!Utils.checkIfNullObject(existingUser) && existingUser.getPassword().contentEquals(password)) {
			return existingUser;
		}
		return null;
	}
	
	public DynamoDBMapper getMapper() {
		return ddbTableMapper;
	}
	
	private SEORClient getSeorClient() {
		return new SEORClient.Builder().build();
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
