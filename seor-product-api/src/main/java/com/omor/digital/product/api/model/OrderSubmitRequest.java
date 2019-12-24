package com.omor.digital.product.api.model;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderSubmitRequest {

	@JsonProperty
	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	@JsonProperty
	public String getShipping_address() {
		return shipping_address;
	}

	public void setShipping_address(String shipping_address) {
		this.shipping_address = shipping_address;
	}

	@JsonProperty
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@JsonProperty
	public double getTotal_product_cost() {
		return total_product_cost;
	}

	public void setTotal_product_cost(double total_product_cost) {
		this.total_product_cost = total_product_cost;
	}

	@JsonProperty
	public double getDiscount_amount() {
		return discount_amount;
	}

	public void setDiscount_amount(double discount_amount) {
		this.discount_amount = discount_amount;
	}

	@JsonProperty
	public double getShipping_cost() {
		return shipping_cost;
	}

	public void setShipping_cost(double shipping_cost) {
		this.shipping_cost = shipping_cost;
	}


	public static OrderSubmitRequest createObjectFromJson(String jsonString) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		OrderSubmitRequest request = mapper.readValue(jsonString, OrderSubmitRequest.class);
		return request;
	}
	
	public OrderSubmitRequest() {
		this.phone = "n/a";
		this.customer_name = "n/a";
		this.discount_amount = 0;
	}

	private String customer_name;
	private String shipping_address;
	private String email;
	private String phone;
	private double total_product_cost;
	private double discount_amount;
	private double shipping_cost;

}
