package ru.miet.toeat.model;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ru.miet.toeat.tools.Tools;

public class Meal extends Nutrition{
	private static final long serialVersionUID = 1L;

	private String name = "noname";
	private ArrayList<Ingredient> ingredients = new ArrayList<>();
	private ArrayList<String> categories = new ArrayList<>();
	private String type = "notype";
	private float rating = 0;


	private Date dateOfLastDispense = new Date();
	private String image="";
	private String recipeURL = "nourl";
	private String description = "no descr";
	public Meal() {
		super();
	}
	public Meal(String name, String type, float rating, Date dateOfLastDispense, String recipeURL) throws FormatException {
		super();
		setName(name);
		setType(type);
		setRating(rating);
		updateDateOfLastDispense();
		setRecipeURL(recipeURL);
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
	public void setDateOfLastDispense(Date dateOfLastDispense) {
		this.dateOfLastDispense = dateOfLastDispense;
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
		@SuppressLint("SimpleDateFormat")
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Date today = new Date();
		Date todayWithZeroTime = null;

		try {
			todayWithZeroTime = formatter.parse(formatter.format(today));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dateOfLastDispense = todayWithZeroTime;
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
	public String getRecipeURL() {
		return recipeURL;
	}
	public void setRecipeURL(String recipeURL) throws FormatException {
		if(Tools.isCorrectFormat(recipeURL, ""))
			this.recipeURL = recipeURL;
		else
			throw new FormatException("Wrong set name in Meal");
	}

	public boolean equals(Meal compare){
		boolean ret = true;
		////бесполезно: 1 url = 1 рецепт
//		ret = name.equals(compare.name);
//		if(!ingredients.equals(compare.ingredients)){
//			ret = false;
//		}
//		if(!categories.equals(compare.categories)){
//			ret = false;
//		}
//		if(!type.equals(compare.type)){
//			ret = false;
//		}
//		if(rating != compare.rating){
//			ret = false;
//		}
//		if(!dateOfLastDispense.equals(compare.dateOfLastDispense)){
//			ret = false;
//		}
		if(!recipeURL.equals(compare.recipeURL)) {
			ret = false;
		}
		return ret;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		if(description.length()==0)
			this.description=name;
		else
			this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}


}