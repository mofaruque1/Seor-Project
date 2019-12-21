package com.omor.eclipsetest;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@DynamoDBTable(tableName = "trt-mac-products")
public class ProductDetails {
	private String product_type;
	private String creation_timestamp;
	private String[] product_url;
	private float[] product_price;
	private float[] product_adjusted_price;
	private float[] product_price_converted_to_taka;
	private String[] product_name;
	private String[] product_large_image_url;
	private String[] product_size;
	private String[] product_shade;
	private String[] product_sku;
	private String[] product_category_name;
	private String[] product_short_desc;
	private String[] product_sku_small_image_url;


	@JsonIgnore
	@DynamoDBHashKey(attributeName="productType")
	public String getProduct_type() {
		return product_type;
	}

	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
	
	@JsonIgnore
	@DynamoDBAttribute(attributeName="creation_timestamp")
	public String getCreation_timestamp() {
		return creation_timestamp;
	}

	public void setCreation_timestamp(String creation_timestamp) {
		this.creation_timestamp = creation_timestamp;
	}
	

	@JsonProperty("product_impression_url")
	@DynamoDBAttribute(attributeName="product_url") 
	public String[] getProduct_url() {
		return product_url;
	}

	public void setProduct_url(String[] product_url) {
		this.product_url = product_url;
	}

	@JsonProperty("product_impression_price")
	@DynamoDBAttribute(attributeName="product_price") 
	public float[] getProduct_price() {
		return product_price;
	}

	public void setProduct_price(float[] product_price) {
		setProduct_adjusted_price(product_price);
		this.product_price = product_price;
	}

	@JsonProperty("product_impression_name")
	@DynamoDBAttribute(attributeName="product_name") 
	public String[] getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String[] product_name) {
		this.product_name = product_name;
	}

	@DynamoDBAttribute(attributeName="product_adjusted_price") 
	public float[] getProduct_adjusted_price() {
		return product_adjusted_price;
	}

	private void setProduct_adjusted_price(float[] price) {
		int length = price.length;
		float[] adjusted_price = new float[length];
		for (int i = 0; i < length; i++) {
			adjusted_price[i] = price[i] + 15;
		}
		setproduct_price_converted_to_taka(adjusted_price);
		this.product_adjusted_price = adjusted_price;
	}

	@DynamoDBAttribute(attributeName="product_price_converted_to_taka") 
	public float[] getproduct_price_converted_to_taka() {
		return product_price_converted_to_taka;
	}

	/**
	 * returns converted price in TAKA
	 * Conversion rate 1$ = 64tk
	 */
	public void setproduct_price_converted_to_taka(float[] price) {
		int length = price.length;
		float[] converted_price = new float[length];
		for (int i = 0; i < length; i++) {
			converted_price[i] = (float) Math.ceil(price[i] * 64);
		}
		this.product_price_converted_to_taka = converted_price;
	}

	@JsonProperty("product_impression_sku_large_image_url")
	@DynamoDBAttribute(attributeName="product_large_image_url") 
	public String[] getProduct_large_image_url() {
		return product_large_image_url;
	}

	public void setProduct_large_image_url(String[] product_large_image_url) {
		this.product_large_image_url = product_large_image_url;
	}

	@JsonProperty("product_impression_size")
	@DynamoDBAttribute(attributeName="product_size") 
	public String[] getProduct_size() {
		return product_size;
	}

	public void setProduct_size(String[] product_size) {
		this.product_size = product_size;
	}

	@JsonProperty("product_impression_shade")
	@DynamoDBAttribute(attributeName="product_shade") 
	public String[] getProduct_shade() {
		return product_shade;
	}

	public void setProduct_shade(String[] product_shade) {
		this.product_shade = product_shade;
	}

	@JsonProperty("product_impression_sku")
	@DynamoDBAttribute(attributeName="product_sku") 
	public String[] getProduct_sku() {
		return product_sku;
	}

	public void setProduct_sku(String[] product_sku) {
		this.product_sku = product_sku;
	}

	@JsonProperty("product_impression_category_name")
	@DynamoDBAttribute(attributeName="product_category_name") 
	public String[] getProduct_category_name() {
		return product_category_name;
	}

	public void setProduct_category_name(String[] product_category_name) {
		this.product_category_name = product_category_name;
	}

	@JsonProperty("product_impression_short_desc")
	@DynamoDBAttribute(attributeName="product_short_desc") 
	public String[] getProduct_short_desc() {
		return product_short_desc;
	}

	public void setProduct_short_desc(String[] product_short_desc) {
		this.product_short_desc = product_short_desc;
	}

	@JsonProperty("product_impression_sku_small_image_url")
	@DynamoDBAttribute(attributeName="product_sku_small_image_url") 
	public String[] getProduct_sku_small_image_url() {
		return product_sku_small_image_url;
	}

	public void setProduct_sku_small_image_url(String[] product_sku_small_image_url) {
		this.product_sku_small_image_url = product_sku_small_image_url;
	}
}
