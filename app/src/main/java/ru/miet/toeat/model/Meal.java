package ru.miet.toeat.model;

import java.util.ArrayList;
import java.util.Date;

public class Meal extends Nutrition{
	private static final long serialVersionUID = 1L;
	
	private String name = "noname";
	private ArrayList<Ingredient> ingredients = new ArrayList<>();
	private ArrayList<String> categories = new ArrayList<>();
	private String type = "notype";
	private float rating = 0;
	private Date dateOfLastDispense = new Date();
	
	public Meal() {
		super();
	}
	public Meal(String name, String type, float rating, Date dateOfLastDispense) {
		super();
		this.name = name;
		this.type = type;
		this.rating = rating;
		this.dateOfLastDispense = dateOfLastDispense;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public ArrayList<String> getCategories() {
		return categories;
	}
	public void setCategories(ArrayList<String> categories) {
		this.categories = categories;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public Date getDateOfLastDispense() {
		return dateOfLastDispense;
	}
	public void updateDateOfLastDispense() {
		//sets current date
		dateOfLastDispense = new Date();
	}
	
	public void addCategory(String name) {
		categories.add(name);
	}
	public void removeCategory(String name) {
		for(String c : categories) {
			if(c.equals(name)) {
				categories.remove(c);
			}
		}
	}
}

