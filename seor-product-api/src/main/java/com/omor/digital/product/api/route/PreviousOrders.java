package com.omor.digital.product.api.route;

import java.util.List;

import com.amazonaws.services.kinesisfirehose.model.InvalidArgumentException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omor.digital.sdk.ProductSDK;
import com.omor.digital.sdk.model.SEOROrder;
import com.omor.digital.sdk.utils.Utils;

import spark.Request;
import spark.Response;

public class PreviousOrders {
	
	private ProductSDK productSDK = null;
	public PreviousOrders() {
		productSDK = new ProductSDK();
	}
	
	
	public List<SEOROrder> setUpPostRoute(Request req, Response res) {
		String requestBody = null;
		try {
			requestBody = req.body();
		} catch (Exception e) {
			System.out.println("Invalid request : "+e.getMessage());
			throw new InvalidArgumentException("Invalid request");
		}
		
		JsonNode jsonNode = null;
		String email = null;
		try {
			jsonNode = new ObjectMapper().readTree(requestBody);
			email = jsonNode.get("email").textValue();
		} catch (Exception e) {
			throw new InvalidArgumentException("Invalid request : "+e.getMessage());
		}
		
		List<SEOROrder> prevOrder = this.productSDK.getPreviousOrders(email);
		Utils.throwIfNullObject(prevOrder, "Error occured getting prev order PreviousOrders.java");
		
		return prevOrder;
	}

}
