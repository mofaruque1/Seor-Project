package com.omor.digital.seor.etl.mac;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		
		//removing duplicate products
		productDetails = removeDuplicateProducts(productDetails);
		
		productDetails.setCreation_timestamp(new Date().toString());
		storeInDynamoDb(productDetails, productType);
	}

	
	public ProductDetails removeDuplicateProducts(ProductDetails productDetails) {

		String[] productSku = productDetails.getProduct_sku();
		int length = productSku.length;
		Set<String> skuSet = new HashSet<String>();
		List<Integer> indexes = new ArrayList<Integer>();

		for (int i = 0; i < length; i++) {
			if (!skuSet.contains(productSku[i])) {
				skuSet.add(productSku[i]);
				indexes.add(i);
			}
		}

		int filteredLength = indexes.size();
		String[] product_url = new String[filteredLength];
		Double[] product_price = new Double[filteredLength];
		Double[] product_adjusted_price = new Double[filteredLength];
		Double[] product_price_converted_to_taka = new Double[filteredLength];
		String[] product_name = new String[filteredLength];
		String[] product_large_image_url = new String[filteredLength];
		String[] product_size = new String[filteredLength];
		String[] product_shade = new String[filteredLength];
		String[] product_sku = new String[filteredLength];
		String[] product_category_name = new String[filteredLength];
		String[] product_short_desc = new String[filteredLength];
		String[] product_sku_small_image_url = new String[filteredLength];

		for (int j = 0; j < filteredLength; j++) {
			product_url[j] = productDetails.getProduct_url()[indexes.get(j)];
			product_price[j] = productDetails.getProduct_price()[indexes.get(j)];
			
			product_adjusted_price[j] = productDetails.getProduct_adjusted_price()[indexes.get(j)];
			product_price_converted_to_taka[j] = productDetails.getproduct_price_converted_to_taka()[indexes.get(j)];
			product_name[j] = productDetails.getProduct_name()[indexes.get(j)];
			product_large_image_url[j] = productDetails.getProduct_large_image_url()[indexes.get(j)];
			product_size[j] = productDetails.getProduct_size()[indexes.get(j)];
			product_shade[j] = productDetails.getProduct_shade()[indexes.get(j)];
			product_sku[j] = productDetails.getProduct_sku()[indexes.get(j)];
			product_category_name[j] = productDetails.getProduct_category_name()[indexes.get(j)];
			product_short_desc[j] = productDetails.getProduct_short_desc()[indexes.get(j)];
			product_sku_small_image_url[j] = productDetails.getProduct_sku_small_image_url()[indexes.get(j)];
		}

		ProductDetails details = new ProductDetails();
		details.setProduct_url(product_url);
		details.setProduct_price(product_price);
		details.setProduct_name(product_name);
		details.setProduct_large_image_url(product_large_image_url);
		details.setProduct_size(product_size);
		details.setProduct_shade(product_shade);
		details.setProduct_sku(product_sku);
		details.setProduct_category_name(product_category_name);
		details.setProduct_short_desc(product_short_desc);
		details.setProduct_sku_small_image_url(product_sku_small_image_url);

		
		return details;

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
			System.out.println("Inserting in dynamo db for : " + productType);
			table.putItem(item);
			System.out.println("Insertion complete for : " + productType + "\n");
		} catch (Exception e) {
			System.out.println("Something went wrong : " + e);
		}
	}

}
