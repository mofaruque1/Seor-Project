package com.omor.digital.product.api.model;

public class MacProduct {
	private String product_brand;
	private String name; // product_name
	private double price; // product_price_converted_to_taka
	private String shade; // product_shade
	private String sku; // product_sku
	private String description; // product_short_desc
	private String size;// product_size
	private String small_image_url;// product_sku_small_image_url
	private String large_image_url;// product_large_image_url
	private String product_url;// product_url
	private String product_type_in_ddb;// productType
	private String product_category;// product_category_name
	private String[] quick_view_images;

	public String[] getQuick_view_images() {
		return quick_view_images;
	}

	public void setQuick_view_images(String[] quick_view_images) {
		this.quick_view_images = quick_view_images;
	}

	public String getProduct_brand() {
		return product_brand;
	}

	public void setProduct_brand(String product_brand) {
		this.product_brand = product_brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getShade() {
		return shade;
	}

	public void setShade(String shade) {
		this.shade = shade;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getSmall_image_url() {
		return small_image_url;
	}

	public void setSmall_image_url(String small_image_url) {
		this.small_image_url = small_image_url;
	}

	public String getLarge_image_url() {
		return large_image_url;
	}

	public void setLarge_image_url(String large_image_url) {
		this.large_image_url = large_image_url;
	}

	public String getProduct_url() {
		return product_url;
	}

	public void setProduct_url(String product_url) {
		this.product_url = product_url;
	}

	public String getProduct_type_in_ddb() {
		return product_type_in_ddb;
	}

	public void setProduct_type_in_ddb(String product_type_in_ddb) {
		this.product_type_in_ddb = product_type_in_ddb;
	}

	public String getProduct_category() {
		return product_category;
	}

	public void setProduct_category(String product_category) {
		this.product_category = product_category;
	}

}
