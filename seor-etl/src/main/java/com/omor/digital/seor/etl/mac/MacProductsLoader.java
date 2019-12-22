package com.omor.digital.seor.etl.mac;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omor.digital.seor.etl.Utils;

public class MacProductsLoader {
	
	private AmazonDynamoDB client = null;
	private DynamoDB dynamoDB = null;
	private Table table = null;
	
	
	
	public MacProductsLoader(String tableName) {
		client = AmazonDynamoDBClientBuilder.standard().build();
		dynamoDB = new DynamoDB(client);
		table = dynamoDB.getTable(tableName);
		
	}

	public void processMacProducts(String filePath, String productType) throws IOException {
		String fileContent = Utils.getFileContentAsString(filePath);
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		JsonNode actualObj = mapper.readTree(fileContent);
		JsonNode dataNode = actualObj.at("/0/result/data/dataLayer");
		ProductDetails productDetails = mapper.readValue(dataNode.toString(), ProductDetails.class);
		productDetails.setCreation_timestamp(new Date().toString());
		storeInDynamoDb(productDetails, productType);
	}

	private void storeInDynamoDb(ProductDetails productDetails, String productType) {
		
		Item item = new Item().withPrimaryKey("productType", productType)
				.withList("product_name", Arrays.asList(productDetails.getProduct_name()))
				.withList("product_price", Arrays.asList(productDetails.getProduct_price()))
				.withList("product_adjusted_price", Arrays.asList(productDetails.getProduct_adjusted_price()))
				.withList("product_price_converted_to_taka",
						Arrays.asList(productDetails.getproduct_price_converted_to_taka()))
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
			System.out.println("Inserting in dynamo db for : "+productType);
			table.putItem(item);
			System.out.println("Insertion complete for : "+productType+"\n");
		} catch (Exception e) {
			System.out.println("Something went wrong : " + e);
		}
	}

}
