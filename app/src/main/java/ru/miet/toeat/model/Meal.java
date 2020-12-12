package ru.miet.toeat.model;

import java.util.ArrayList;
import java.util.Date;

import ru.miet.toeat.tools.Tools;

public class Meal extends Nutrition{
	private static final long serialVersionUID = 1L;

	private String name = "noname";
	private ArrayList<Ingredient> ingredients = new ArrayList<>();
	private ArrayList<String> categories = new ArrayList<>();;
	private String type = "notype";
	private float rating = 0;
	private Date dateOfLastDispense = new Date();
	private String image="";
	private String link_to_recept="";

	public Meal() {
		super();
	}
	public Meal(String name, String type, float rating, Date dateOfLastDispense) throws FormatException {
		super();
		setName(name);
		setType(type);
		setRating(rating);
		updateDateOfLastDispense();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) throws FormatException {
		if(Tools.isCorrectFormat(name, ""))
			this.name = name;
		else
			throw new FormatException("Wrong set name in Meal");
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
	public void setType(String type) throws FormatException {
		if(Tools.isCorrectFormat(type, ""))
			this.type = type;
		else
			throw new FormatException("Wrong set type in Meal");
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) throws FormatException {
		if(Tools.isInRange(rating, 0, 5))
			this.rating = rating;
		else
			throw new FormatException("Wrong set rating in Meal");
	}
	public Date getDateOfLastDispense() {
		return dateOfLastDispense;
	}
	public void updateDateOfLastDispense() {
		//sets current date
		dateOfLastDispense = new Date();
	}

	public void addСategory(String name) {
		categories.add(name);
	}
	public void removeСategory(String name) {
		for(String c : categories) {
			if(c.equals(name)) {
				categories.remove(c);
			}
		}
	}

	public boolean equals(Meal compare){
		boolean ret = true;
		if(!name.equals(compare.name)) {
			ret = false;
		}
		if(!ingredients.equals(compare.ingredients)){
			ret = false;
		}
		if(!categories.equals(compare.categories)){
			ret = false;
		}
		if(!type.equals(compare.type)){
			ret = false;
		}
		if(rating != compare.rating){
			ret = false;
		}
		if(!dateOfLastDispense.equals(compare.dateOfLastDispense)){
			ret = false;
		}

		return ret;
	}

	public String getLink_to_recept() {
		return link_to_recept;
	}
	public void setLink_to_recept(String link_to_recept) {
		this.link_to_recept = link_to_recept;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}