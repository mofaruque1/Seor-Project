package com.omor.digital.sdk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
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

	public List<SEOROrder> getPreviousOrders(String email) {
		Table table = db.getTable("trt-customer-order");

		
			QuerySpec spec = new QuerySpec().withKeyConditionExpression("order_id = :email")
					.withValueMap(new ValueMap().withString(":email", email));

			ItemCollection<QueryOutcome> items = table.query(spec);
			return processPrevOrderItem(items);
	}
	
	private List<SEOROrder> processPrevOrderItem(ItemCollection<QueryOutcome> items) {
		
		Iterator<Item> iterator = items.iterator();
		Item item = null;
		List<SEOROrder> prevOrders = new ArrayList<SEOROrder>();
		String itemString = null;
		while (iterator.hasNext()) {
			item = iterator.next();
			itemString = item.toJSONPretty();
			SEOROrder tempSeorOrder = SEOROrder.createObjectFromjsonString(itemString);
			prevOrders.add(tempSeorOrder);
		}
		return prevOrders;
		
	}
	

	public boolean submitOrder(SEOROrder order) {
		order.setStatus(OrderStatus.CUSTOMER_SUBMITTED.toString());
		// DynamoDB db is coming from BaseSDK
		Table table = db.getTable("trt-customer-order");
		Item item = new Item().withPrimaryKey("order_id", order.getOrder_id())
				.withString("customer_name", order.getCustomer_name())
				.withString("shipping_address", order.getShipping_address())
				.withString("email", order.getEmail())
				.withString("payement_processing_corp", order.getPayement_processing_corp())
				.withString("bkash_transaction_id", order.getBkash_transaction_id())
				.withString("phone", order.getPhone())
				.withString("timestamp", order.getTimestamp())
				.withDouble("total_product_cost", order.getTotal_product_cost())
				.withDouble("discount_amount", order.getDiscount_amount())
				.withDouble("shipping_cost", order.getShipping_cost())
				.withString("status", order.getStatus())
				.withString("payment_status", order.getPayment_status())
				.withJSON("order", order.getOrder().toString());

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
