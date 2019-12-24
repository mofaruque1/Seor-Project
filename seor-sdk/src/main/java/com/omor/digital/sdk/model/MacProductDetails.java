package com.omor.digital.sdk.model;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="trt-mac-products")
public class MacProductDetails {
	
	private String creation_timestamp;
	private String product_type;
	private List<String> product_url;
	private List<Double> product_price;
	private List<Double> product_adjusted_price;
	private List<Double> product_price_converted_to_taka;
	private List<String> product_name;
	private List<String> product_large_image_url;
	private List<String> product_size;
	private List<String> product_shade;
	private List<String> product_sku;
	private List<String> product_category_name;
	private List<String> product_short_desc;
	private List<String> product_sku_small_image_url;
	
	
	@DynamoDBAttribute(attributeName="CreationTime") 
	public String getCreation_timestamp() {
		return creation_timestamp;
	}
	public void setCreation_timestamp(String creation_timestamp) {
		this.creation_timestamp = creation_timestamp;
	}
	
	@DynamoDBHashKey(attributeName="productType")
	public String getProduct_type() {
		return product_type;
	}
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
	
	@DynamoDBAttribute(attributeName="product_url")
	public List<String> getProduct_url() {
		return product_url;
	}
	public void setProduct_url(List<String> product_url) {
		this.product_url = product_url;
	}
	
	@DynamoDBAttribute(attributeName="product_price")
	public List<Double> getProduct_price() {
		return product_price;
	}
	public void setProduct_price(List<Double> product_price) {
		this.product_price = product_price;
	}
	
	@DynamoDBAttribute(attributeName="product_adjusted_price")
	public List<Double> getProduct_adjusted_price() {
		return product_adjusted_price;
	}
	public void setProduct_adjusted_price(List<Double> product_adjusted_price) {
		this.product_adjusted_price = product_adjusted_price;
	}
	
	@DynamoDBAttribute(attributeName="product_price_converted_to_taka")
	public List<Double> getProduct_price_converted_to_taka() {
		return product_price_converted_to_taka;
	}
	public void setProduct_price_converted_to_taka(List<Double> product_price_converted_to_taka) {
		this.product_price_converted_to_taka = product_price_converted_to_taka;
	}
	
	@DynamoDBAttribute(attributeName="product_name")
	public List<String> getProduct_name() {
		return product_name;
	}
	public void setProduct_name(List<String> product_name) {
		this.product_name = product_name;
	}
	
	@DynamoDBAttribute(attributeName="product_large_image_url")
	public List<String> getProduct_large_image_url() {
		return product_large_image_url;
	}
	public void setProduct_large_image_url(List<String> product_large_image_url) {
		this.product_large_image_url = product_large_image_url;
	}
	
	@DynamoDBAttribute(attributeName="product_size")
	public List<String> getProduct_size() {
		return product_size;
	}
	public void setProduct_size(List<String> product_size) {
		this.product_size = product_size;
	}
	
	@DynamoDBAttribute(attributeName="product_shade")
	public List<String> getProduct_shade() {
		return product_shade;
	}
	public void setProduct_shade(List<String> product_shade) {
		this.product_shade = product_shade;
	}
	
	@DynamoDBAttribute(attributeName="product_sku")
	public List<String> getProduct_sku() {
		return product_sku;
	}
	public void setProduct_sku(List<String> product_sku) {
		this.product_sku = product_sku;
	}
	
	@DynamoDBAttribute(attributeName="product_category_name")
	public List<String> getProduct_category_name() {
		return product_category_name;
	}
	public void setProduct_category_name(List<String> product_category_name) {
		this.product_category_name = product_category_name;
	}
	
	@DynamoDBAttribute(attributeName="product_short_desc")
	public List<String> getProduct_short_desc() {
		return product_short_desc;
	}
	public void setProduct_short_desc(List<String> product_short_desc) {
		this.product_short_desc = product_short_desc;
	}
	
	@DynamoDBAttribute(attributeName="product_sku_small_image_url")
	public List<String> getProduct_sku_small_image_url() {
		return product_sku_small_image_url;
	}
	public void setProduct_sku_small_image_url(List<String> product_sku_small_image_url) {
		this.product_sku_small_image_url = product_sku_small_image_url;
	}


}
