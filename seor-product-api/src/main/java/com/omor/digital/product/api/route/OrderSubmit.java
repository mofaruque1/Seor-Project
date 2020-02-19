package com.omor.digital.product.api.route;

import com.amazonaws.services.kinesisfirehose.model.InvalidArgumentException;
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
		SEOROrder seorOrder = null;

		try {
			seorOrder = SEOROrder.createObjectFromjsonString(requestBody);
		} catch (Exception e) {
			System.out.printf("invalid request payload.", e);
			throw new InvalidArgumentException("invalid request payload. " + e.getMessage());
		}

		if (seorOrder.getEmail() == null || seorOrder.getEmail().trim().equals("")) {
			seorOrder.setEmail("n/a");
			seorOrder.setOrder_id(seorOrder.getPhone());
		}

		if (seorOrder.getOrder_notes() == null || seorOrder.getOrder_notes().trim().equals("")) {
			seorOrder.setOrder_notes("n/a");
		}

		if (seorOrder.getShipping_address() == null || seorOrder.getShipping_address().trim().equals("")) {
			System.out.printf("Customer email or address is missing.");

			if (seorOrder.isCustomer_pickup()) {
				seorOrder.setShipping_address("n/a");
			} else {
				throw new InvalidArgumentException("Customer Email or address is missing");
			}
			
		}

		if (seorOrder.getPhone() == null || seorOrder.getPhone().trim().equals("")) {
			System.out.printf("Customer phone no is missing.");
			throw new InvalidArgumentException("Customer phone no is missing");
		}

//		if (seorOrder.getPayment_option()==null || seorOrder.getPayment_option().trim().equals("")) {
//			throw new InvalidArgumentException("Payment status missing");
//		}

		boolean orderSubmitted = productSDK.submitOrder(seorOrder);

		if (!orderSubmitted) {
			throw new InvalidArgumentException("Something went wrong while submitting order");
		}

		OrderSubmitResponse response = new OrderSubmitResponse();
		response.setStatus("success");
		response.setOrderId(seorOrder.getOrder_id());
		response.setTimeStamp(seorOrder.getTimestamp());

		return response;
	}

}
