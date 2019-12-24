package com.omor.digital.product.api.route;

import com.amazonaws.services.kinesisfirehose.model.InvalidArgumentException;
import com.omor.digital.product.api.model.MacProduct;
import com.omor.digital.sdk.ProductSDK;
import com.omor.digital.sdk.model.MacProductDetails;
import com.omor.digital.sdk.utils.Utils;

import spark.Request;
import spark.Response;

public class MacProductShow {

	private ProductSDK productSDK = null;
	
	public MacProductShow() {
		this.productSDK = new ProductSDK();
	}

	public MacProduct[] getMacProducts(Request req, Response res) {
		
		String productType = null;
		try {
			productType = req.params(":type");
		} catch (Exception e) {
			System.out.println("Invalid request parameter" + e.getMessage());
			throw new InvalidArgumentException("Invalid request parameter " + e.getMessage());
		}

		MacProductDetails productDetails = this.productSDK.getMacProducts(productType);
		Utils.throwIfNullObject(productDetails, "No such product type");
		int length = productDetails.getProduct_name().size();
		MacProduct[] macProducts = new MacProduct[length];
		MacProduct tempMacProduct = null;
		try {
			for (int i = 0; i < length; i++) {
				tempMacProduct = new MacProduct();
				tempMacProduct.setProduct_brand("MAC");
				tempMacProduct.setDescription(productDetails.getProduct_short_desc().get(i));
				tempMacProduct.setLarge_image_url(productDetails.getProduct_large_image_url().get(i));
				tempMacProduct.setName(productDetails.getProduct_name().get(i));
				tempMacProduct.setPrice(productDetails.getProduct_price_converted_to_taka().get(i));
				tempMacProduct.setProduct_category(productDetails.getProduct_category_name().get(i));
				tempMacProduct.setProduct_type_in_ddb(productDetails.getProduct_type());
				tempMacProduct.setProduct_url(productDetails.getProduct_url().get(i));
				tempMacProduct.setShade(productDetails.getProduct_shade().get(i));
				tempMacProduct.setSku(productDetails.getProduct_sku().get(i));
				tempMacProduct.setSize(productDetails.getProduct_size().get(i));
				tempMacProduct.setSmall_image_url(productDetails.getProduct_sku_small_image_url().get(i));
				macProducts[i] = tempMacProduct;
			}
			res.status(200);
		} catch (Exception e) {
			res.status(500);
			throw new InvalidArgumentException("Something went wrong : " + e.getMessage());
		}

		return macProducts;
	}
}
