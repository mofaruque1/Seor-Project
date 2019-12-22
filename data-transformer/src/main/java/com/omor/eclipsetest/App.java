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

		//objectToJson(productDetails);
		storeInDynamoWithArrayList(productDetails);

	}


	public static void storeInDynamoWithArrayList(ProductDetailsV2 productDetails) {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
		DynamoDB dynamoDB = new DynamoDB(client);

		Table table = dynamoDB.getTable("trt-mac-products");
		Item item = new Item()
			      .withPrimaryKey("productType", "assorted")
			      .withList("product_name", Arrays.asList(productDetails.getProduct_name()))
			      .withList("product_price", Arrays.asList(productDetails.getProduct_price()))
			      .withList("product_adjusted_price", Arrays.asList(productDetails.getProduct_adjusted_price()))
			      .withList("product_price_converted_to_taka", Arrays.asList(productDetails.getproduct_price_converted_to_taka()))
			      .withList("product_size", Arrays.asList(productDetails.getProduct_size()))
			      .withList("product_category_name", Arrays.asList(productDetails.getProduct_category_name()))
			      .withList("product_shade", Arrays.asList(productDetails.getProduct_shade()))
			      .withList("product_url", Arrays.asList(productDetails.getProduct_url()))
			      .withList("product_sku", Arrays.asList(productDetails.getProduct_sku()))
			      .withList("product_short_desc", Arrays.asList(productDetails.getProduct_short_desc()))
			      .withList("product_sku_small_image_url", Arrays.asList(productDetails.getProduct_sku_small_image_url()))
			      .withList("product_large_image_url", Arrays.asList(productDetails.getProduct_large_image_url()))
			      .withString("CreationTime", productDetails.getCreation_timestamp());
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

}
