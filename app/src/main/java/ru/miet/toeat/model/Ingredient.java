package ru.miet.toeat.model;

import java.io.Serializable;

public class Ingredient implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private float amount = 0;
	private Product product = new Product();
	private String category = "nocategory";
	
	public Ingredient() {
		super();
	}
	public Ingredient(float amount, Product product) {
		super();
		this.amount = amount;
		this.product = product;
	}
	
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
