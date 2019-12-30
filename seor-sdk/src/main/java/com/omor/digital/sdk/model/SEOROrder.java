package com.omor.digital.sdk.model;

import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SEOROrder {

	@JsonProperty("customer_name")
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	
	@JsonProperty("shipping_address")
	public String getShipping_address() {
		return shipping_address;
	}
	public void setShipping_address(String shipping_address) {
		this.shipping_address = shipping_address;
	}
	
	@JsonProperty("email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
		setOrder_id(email);
	}
	
	@JsonProperty("order_id")
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	@JsonProperty("phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@JsonProperty("timestamp")
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	@JsonProperty("total_product_cost")
	public double getTotal_product_cost() {
		return total_product_cost;
	}
	public void setTotal_product_cost(double total_product_cost) {
		this.total_product_cost = total_product_cost;
	}
	
	@JsonProperty("discount_amount")
	public double getDiscount_amount() {
		return discount_amount;
	}
	public void setDiscount_amount(double discount_amount) {
		this.discount_amount = discount_amount;
	}
	
	@JsonProperty("shipping_cost")
	public double getShipping_cost() {
		return shipping_cost;
	}
	public void setShipping_cost(double shipping_cost) {
		this.shipping_cost = shipping_cost;
	}
	
	@JsonProperty("status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonProperty("payment_status")
	public String getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}
	
	@JsonProperty("order")
	public JsonNode getOrder() {
		return order;
	}
	public void setOrder(JsonNode order) {
		this.order = order;
	}
	
	@JsonProperty("bkash_transaction_id")
	public String getBkash_transaction_id() {
		return bkash_transaction_id;
	}
	public void setBkash_transaction_id(String bkash_transaction_id) {
		this.bkash_transaction_id = bkash_transaction_id;
	}
	
	@JsonProperty("payement_processing_corp")
	public String getPayement_processing_corp() {
		return payement_processing_corp;
	}
	public void setPayement_processing_corp(String payement_processing_corp) {
		this.payement_processing_corp = payement_processing_corp;
	}
	
	public static SEOROrder createObjectFromjsonString(String jsonString) {
		System.out.println(jsonString.toString());
		SEOROrder prevOrder = null;
		try {
			prevOrder = new ObjectMapper().readValue(jsonString, SEOROrder.class);
		} catch (Exception e) {
			throw new IllegalArgumentException("Couldnot map ddb response to SEOROrder. "+e.getMessage());
		}
		return prevOrder;
	}
	
	public SEOROrder() {
		this.phone = "n/a";
		this.customer_name = "n/a";
		this.discount_amount = 0;
		this.timestamp = new Date().toString();
		this.bkash_transaction_id = "no id entered";
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
	
	

	private String bkash_transaction_id;
	private String payement_processing_corp;
	
	

	private String status;
	private String payment_status;
	private JsonNode order;
}
