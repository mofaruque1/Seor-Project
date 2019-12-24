package com.omor.digital.sdk.model;

import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "trt-customer-order")
public class SEOROrder {

	@DynamoDBAttribute(attributeName="customer_name")
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	
	@DynamoDBAttribute(attributeName="shipping_address")
	public String getShipping_address() {
		return shipping_address;
	}
	public void setShipping_address(String shipping_address) {
		this.shipping_address = shipping_address;
	}
	
	@DynamoDBAttribute(attributeName="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@DynamoDBHashKey(attributeName="order_id")
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	@DynamoDBAttribute(attributeName="phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@DynamoDBAttribute(attributeName="timestamp")
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	@DynamoDBAttribute(attributeName="total_product_cost")
	public double getTotal_product_cost() {
		return total_product_cost;
	}
	public void setTotal_product_cost(double total_product_cost) {
		this.total_product_cost = total_product_cost;
	}
	
	@DynamoDBAttribute(attributeName="discount_amount")
	public double getDiscount_amount() {
		return discount_amount;
	}
	public void setDiscount_amount(double discount_amount) {
		this.discount_amount = discount_amount;
	}
	
	@DynamoDBAttribute(attributeName="shipping_cost")
	public double getShipping_cost() {
		return shipping_cost;
	}
	public void setShipping_cost(double shipping_cost) {
		this.shipping_cost = shipping_cost;
	}
	
	@DynamoDBAttribute(attributeName="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@DynamoDBAttribute(attributeName="order")
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
	public SEOROrder() {
		this.timestamp = new Date().toString();
	}
	private String customer_name;
	private String shipping_address;
	private String email;
	private String order_id;
	private String phone;
	private String timestamp;
	private double total_product_cost;
	private double discount_amount;
	private double shipping_cost;
	private String status;
	private String order;
}
