package com.omor.digital.sdk;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omor.digital.sdk.model.MacProductDetails;
import com.omor.digital.sdk.model.OrderStatus;
import com.omor.digital.sdk.model.SEOROrder;

public class ProductSDK extends BaseSDK {

	public ProductSDK() {
		super();
	}

	public MacProductDetails getMacProducts(String productType) {
		MacProductDetails productsDetails = null;
		try {
			productsDetails = ddbTableMapper.load(MacProductDetails.class, productType);
		} catch (Exception e) {
			System.out.println("Error while loading mac products [ProductSDK.java]: " + e.getMessage());
		}
		return productsDetails;
	}

	public boolean submitOrder(SEOROrder order) {
		order.setStatus(OrderStatus.CUSTOMER_SUBMITTED.toString());
		System.out.println("|-------------Submit order ProductSDK-------------|");

		Table table = db.getTable("trt-customer-order");
		Item item = new Item().withPrimaryKey("order_id", order.getOrder_id())
				.withString("customer_name", order.getCustomer_name())
				.withString("shipping_address", order.getShipping_address()).withString("email", order.getEmail())
				.withString("phone", order.getPhone()).withString("timestamp", order.getTimestamp())
				.withDouble("total_product_cost", order.getTotal_product_cost())
				.withDouble("discount_amount", order.getDiscount_amount())
				.withDouble("shipping_cost", order.getShipping_cost()).withString("status", order.getStatus())
				.withJSON("order", order.getOrder());

		try {
			System.out.println("Inserting ORDER in dynamo db ");
			table.putItem(item);
			System.out.println("Insertion complete for ORDER");
			return true;
		} catch (Exception e) {
			System.out.println("Something went wrong : " + e);
		}

		return false;

	}

}
