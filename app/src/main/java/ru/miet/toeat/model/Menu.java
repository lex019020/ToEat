package ru.miet.toeat.model;

import java.lang.reflect.Field;

import ru.miet.toeat.model.Meal;
import ru.miet.toeat.model.Nutrition;

public class Menu extends Nutrition {
	private static final long serialVersionUID = 1L;

	private Meal breakfast = new Meal();
	private Meal tiffin = new Meal();//same as lunch
	private Meal dinner = new Meal();
	private Meal anSnack = new Meal();
	private Meal supper = new Meal();
	private Meal snack = new Meal();

	public Menu() {
		super();
	}
	public Menu(Meal breakfast, Meal tiffin, Meal dinner, Meal anSnack, Meal supper, Meal snack) {
		super();
		setBreakfast(breakfast);
		setTiffin(tiffin);
		setDinner(dinner);
		setSnack(anSnack);
		setSupper(supper);
		setSnack(snack);
	}

	public Meal getBreakfast() {
		return breakfast;
	}
	public void setBreakfast(Meal breakfast) {
		this.breakfast = breakfast;
	}
	public Meal getTiffin() {
		return tiffin;
	}
	public void setTiffin(Meal tiffin) {
		this.tiffin = tiffin;
	}
	public Meal getDinner() {
		return dinner;
	}
	public void setDinner(Meal dinner) {
		this.dinner = dinner;
	}
	public Meal getAnSnack() {
		return anSnack;
	}
	public void setAnSnack(Meal anSnack) {
		this.anSnack = anSnack;
	}
	public Meal getSupper() {
		return supper;
	}
	public void setSupper(Meal supper) {
		this.supper = supper;
	}
	public Meal getSnack() {
		return snack;
	}
	public void setSnack(Meal snack) {
		this.snack = snack;
	}

	//TODO: check and think about exceptions
	public void calcNutrition() /*throws IllegalAccessException*/ {
		proteins = breakfast.proteins + tiffin.proteins + dinner.proteins + snack.proteins +
				supper.proteins + anSnack.proteins;
		fat = breakfast.fat + tiffin.fat + dinner.fat + snack.fat + supper.fat + anSnack.fat;
		calories = breakfast.calories + tiffin.calories + dinner.calories +
				snack.calories + supper.calories + anSnack.calories;
		carbs = breakfast.carbs + tiffin.carbs + dinner.carbs + snack.carbs + supper.carbs +
				anSnack.carbs;
	}
}
