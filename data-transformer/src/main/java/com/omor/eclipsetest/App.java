package com.omor.eclipsetest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) throws Exception {

		FileReader reader = new FileReader("/Users/mdomor.faruque/eclipse-workspace-2/data/mac/assorted.txt");
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line;
		StringBuilder stringBuilder = new StringBuilder();

		while ((line = bufferedReader.readLine()) != null) {
			stringBuilder.append(line);
		}
		bufferedReader.close();
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		JsonNode actualObj = mapper.readTree(stringBuilder.toString());

		JsonNode dataNode = actualObj.at("/0/result/data/dataLayer");
		//storeWithoutMapper(dataNode.toString());

		//ProductDetails productDetails = mapper.readValue(dataNode.toString(), ProductDetails.class);
		ProductDetailsV2 productDetails = mapper.readValue(dataNode.toString(), ProductDetailsV2.class);
		productDetails.setCreation_timestamp(new Date().toString());



		//storeInDynamo(product);
		storeInDynamoWithArrayList(productDetails);

		 //objectToJson(productDetails);

	}

	public static void storeInDynamo(Product product) {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
		DynamoDBMapper ddbMapper = new DynamoDBMapper(client);

		try {
			System.out.println("Inserting in dynamo db");
			ddbMapper.save(product);
			System.out.println("Insertion complete");
		} catch (Exception e) {
			System.out.println("Something went wrong :(" + e);
		}
	}

	public static void storeWithoutMapper(String jsonString) {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
		DynamoDB dynamoDB = new DynamoDB(client);

		Table table = dynamoDB.getTable("trt-mac-products");
		Item item = new Item()
				      .withPrimaryKey("productType", "assorted")
				      .withJSON("document", jsonString);
		
		try {
			System.out.println("Inserting in dynamo db");
			table.putItem(item);
			System.out.println("Insertion complete");
		} catch (Exception e) {
			System.out.println("Something went wrong :(" + e);
		}

	}

	public static void objectToJson(ProductDetailsV2 details) throws JsonProcessingException {
		ObjectMapper mapper2 = new ObjectMapper();
		String jsonInString = mapper2.writeValueAsString(details);

		System.out.println(jsonInString);

	}
	
	public static void storeInDynamoWithArrayList(ProductDetailsV2 productDetails) {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
		DynamoDB dynamoDB = new DynamoDB(client);

		Table table = dynamoDB.getTable("trt-mac-products");
		Item item = new Item()
			      .withPrimaryKey("productType", "assorted")
			      .withString("CreationTime", productDetails.getCreation_timestamp())
			      .withStringSet("product_url", new HashSet<String>(productDetails.getProduct_url()));
		try {
			System.out.println("Inserting in dynamo db");
			table.putItem(item);
			System.out.println("Insertion complete");
		} catch (Exception e) {
			System.out.println("Something went wrong :(" + e);
		}
	}

}
