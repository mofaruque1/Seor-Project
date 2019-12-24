package com.omor.digital.product.api.route;

import com.amazonaws.services.kinesisfirehose.model.InvalidArgumentException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omor.digital.product.api.model.OrderSubmitRequest;
import com.omor.digital.product.api.model.OrderSubmitResponse;
import com.omor.digital.sdk.ProductSDK;
import com.omor.digital.sdk.model.SEOROrder;
import com.omor.digital.sdk.utils.Utils;

import spark.Request;
import spark.Response;

public class OrderSubmit {

	private ProductSDK productSDK = null;

	public OrderSubmit() {
		productSDK = new ProductSDK();
	}

	public OrderSubmitResponse submitOrder(Request req, Response res) {

	

		Utils.throwIfNullObject(req, "Empty or missing order submit request parameter(s)");
		String requestBody = null;
		try {
			requestBody = req.body();
		} catch (Exception e) {
			throw new InvalidArgumentException("Invalid request : " + e.getMessage());
		}
		System.out.println("Order submit request received . request.body: ");
		System.out.println(requestBody);
		OrderSubmitRequest request = null;

		try {
			request = OrderSubmitRequest.createObjectFromJson(requestBody);
		} catch (Exception e) {
			System.out.printf("invalid request payload.", e);
			throw new InvalidArgumentException("invalid request payload. " + e.getMessage());
		}
		if (request.getEmail() == null || request.getEmail().trim().equals("") || request.getShipping_address() == null
				|| request.getShipping_address().trim().equals("")) {
			System.out.printf("Customer email or address is missing.");
			throw new InvalidArgumentException("Customer Email or address is missing");
		}

		JsonNode productNode = null;
		JsonNode orderNode = null;
		try {
			productNode = new ObjectMapper().readTree(requestBody);
			orderNode = productNode.get("order");
		} catch (Exception e) {
			System.out.println("Error occured : " + e.getMessage());
		}

		SEOROrder seorOrder = new SEOROrder();
		seorOrder.setCustomer_name(request.getCustomer_name());
		seorOrder.setDiscount_amount(request.getDiscount_amount());
		seorOrder.setEmail(request.getEmail());
		seorOrder.setOrder(orderNode);
		seorOrder.setOrder_id(request.getEmail());
		seorOrder.setPhone(request.getPhone());
		seorOrder.setShipping_address(request.getShipping_address());
		seorOrder.setShipping_cost(request.getShipping_cost());
		seorOrder.setTotal_product_cost(request.getTotal_product_cost());
		
		boolean orderSubmitted = productSDK.submitOrder(seorOrder);
		
		if (!orderSubmitted) {
			throw new InvalidArgumentException("Something went wrong while submitting order");
		}

		OrderSubmitResponse response = new OrderSubmitResponse();
		response.setStatus("success");

		return response;
	}

}