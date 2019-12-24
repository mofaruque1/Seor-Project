package com.omor.digital.sdk;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.omor.digital.sdk.utils.Utils;


public class BaseSDK {
	protected SEORClient client = null;
	protected AmazonDynamoDB dynamoDB = null;
	protected DynamoDBMapper ddbTableMapper = null;
	protected DynamoDB db = null;
	
	
	public BaseSDK() {
		this.client = getSeorClient();
		Utils.throwIfNullObject(this.client, "SEORClient object cannot be null [...SDK.java]");
		this.dynamoDB = this.client.getDynamoDB();
		Utils.throwIfNullObject(this.dynamoDB, "AmazonDynamoDB reference object cannot be NULL! [CustomerSDK.java]");
		ddbTableMapper = new DynamoDBMapper(this.dynamoDB);
		db = new DynamoDB(dynamoDB);
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




}
