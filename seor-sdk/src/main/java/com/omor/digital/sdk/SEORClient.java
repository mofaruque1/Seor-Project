package com.omor.digital.sdk;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class SEORClient {

	private AmazonDynamoDB dynamoDB = null;

	private SEORClient() {}

	private void initialize() {
		this.dynamoDB = AmazonDynamoDBClientBuilder.standard().build();
	}

	public AmazonDynamoDB getDynamoDB() {
		return dynamoDB;
	}

	public static class Builder {

		private SEORClient client = null;

		public Builder() {}

		public SEORClient build() {
			if (this.client == null) {
				this.client = new SEORClient();
				this.client.initialize();
			}
			return this.client;
		}

	}

}
