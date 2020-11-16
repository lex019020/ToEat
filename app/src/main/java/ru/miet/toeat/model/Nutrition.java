package ru.miet.toeat.model;

import java.io.Serializable;

import ru.miet.toeat.tools.Tools;

public class Nutrition implements Serializable{
	private static final long serialVersionUID = 1L;

	float proteins = 0;
	float fat = 0;
	float carbs = 0;
	float calories = 0;

	public Nutrition() {
		super();
	}
	public Nutrition(float proteins, float fat, float carbs, float calories) throws FormatException {
		super();
		setProteins(proteins);
		setFat(fat);
		setCarbs(carbs);
		setCalories(calories);
	}

	public float getProteins() {
		return proteins;
	}
	public void setProteins(float proteins) throws FormatException {
		if(Tools.isInRange(proteins, 0, Float.MAX_VALUE))
			this.proteins = proteins;
		else
			throw new FormatException("Wrong set proteins in Nutrition");
	}
	public float getFat() {
		return fat;
	}
	public void setFat(float fat) throws FormatException {
		if(Tools.isInRange(fat, 0, Float.MAX_VALUE))
			this.fat = fat;
		else
			throw new FormatException("Wrong set fat in Nutrition");
	}
	public float getCarbs() {
		return carbs;
	}
	public void setCarbs(float carbs) throws FormatException {
		if(Tools.isInRange(carbs, 0, Float.MAX_VALUE))
			this.carbs = carbs;
		else
			throw new FormatException("Wrong set carbs in Nutrition");
	}
	public float getCalories() {
		return calories;
	}
	public void setCalories(float calories) throws FormatException {
		if(Tools.isInRange(calories, 0, Float.MAX_VALUE))
			this.calories = calories;
		else
			throw new FormatException("Wrong set calories in Nutrition");
	}
}
