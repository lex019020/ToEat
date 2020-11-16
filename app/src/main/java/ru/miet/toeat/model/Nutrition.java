package ru.miet.toeat.model;

import java.io.Serializable;

public class Nutrition implements Serializable{
	private static final long serialVersionUID = 1L;
	
	float proteins = 0;
	float fat = 0;
	float carbs = 0;
	float calories = 0;
	
	public Nutrition() {
		super();
	}
	public Nutrition(float proteins, float fat, float carbs, float calories) {
		super();
		this.proteins = proteins;
		this.fat = fat;
		this.carbs = carbs;
		this.calories = calories;
	}

	public float getProteins() {
		return proteins;
	}
	public void setProteins(float proteins) {
		this.proteins = proteins;
	}
	public float getFat() {
		return fat;
	}
	public void setFat(float fat) {
		this.fat = fat;
	}
	public float getCarbs() {
		return carbs;
	}
	public void setCarbs(float carbs) {
		this.carbs = carbs;
	}
	public float getCalories() {
		return calories;
	}
	public void setCalories(float calories) {
		this.calories = calories;
	}
}
