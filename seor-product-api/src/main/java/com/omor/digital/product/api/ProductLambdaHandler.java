package com.omor.digital.product.api;

import static spark.Spark.get;
import static spark.Spark.post;

import com.omor.digital.base.api.JsonTransformer;
import com.omor.digital.base.api.RequestStreamLambdaHandler;
import com.omor.digital.product.api.route.MacProductShow;
import com.omor.digital.product.api.route.OrderSubmit;
import com.omor.digital.product.api.route.PreviousOrders;

public class ProductLambdaHandler extends RequestStreamLambdaHandler {

	private MacProductShow macProductShow = null;
	private ProductLambdaHandlerHelper helper = null;
	private OrderSubmit orderSubmit = null;
	private PreviousOrders previousOrders = null;
	
	static class ProductLambdaHandlerHelper {
		MacProductShow getMacProductShowRoutes() {
			return new MacProductShow();
		}
		
		OrderSubmit getOrderSubmitRoutes() {
			return new OrderSubmit();
		}
		
		PreviousOrders getPreviousOrders() {
			return new PreviousOrders();
		}
	}

	public ProductLambdaHandler() {
		super();
		this.helper = new ProductLambdaHandlerHelper();
		this.macProductShow = this.helper.getMacProductShowRoutes();
		this.orderSubmit = this.helper.getOrderSubmitRoutes();
		this.previousOrders = this.helper.getPreviousOrders();
	}

	@Override
	public void defineResources() {

		get("/mac/:type", this.macProductShow::getMacProducts, new JsonTransformer());
		
		post("/order", this.orderSubmit::submitOrder, new JsonTransformer());
		post("/prev-order", this.previousOrders::setUpPostRoute, new JsonTransformer());
	}

	

}
