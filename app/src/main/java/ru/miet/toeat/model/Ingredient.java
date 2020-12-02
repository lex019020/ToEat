package ru.miet.toeat.model;

import java.io.Serializable;
import ru.miet.toeat.tools.Tools;

public class Ingredient implements Serializable {
	private static final long serialVersionUID = 1L;

	private float amount = 0;
	private Product product = new Product();
	private String category = "nocategory";

	public Ingredient() {
		super();
	}
	public Ingredient(float amount, Product product, String category) throws FormatException {
		super();
		setAmount(amount);
		setProduct(product);
		setCategory(category);
	}

	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) throws FormatException {
		if(Tools.isInRange(amount, 0, Float.MAX_VALUE))
			this.amount = amount;
		else
			throw new FormatException("Wrong set amount in Ingredient");
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
	public void setCategory(String category) throws FormatException {
		if(Tools.isCorrectFormat(category, ""))
			this.category = category;
		else
			throw new FormatException("Wrong set category in Ingredient");
	}
	public boolean equals(Ingredient compare){
		boolean ret = true;
		if(amount != compare.amount){
			ret = false;
		}
		if(!product.equals(compare.product)){
			ret = false;
		}
		if(!category.equals(compare.category)){
			ret = false;
		}
		return ret;
	}
}
