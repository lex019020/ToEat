package ru.miet.toeat.model;

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

	//TODO: implement this
	public void calcNutrition() {
		proteins = 1;
		fat = 1;
		carbs = 1;
		calories = 1;
	}

	public boolean equals(Menu compare){
		boolean ret = true;
		if(!breakfast.equals(compare.breakfast)) {
			ret = false;
		}
		if(!tiffin.equals(compare.tiffin)){
			ret = false;
		}
		if(!dinner.equals(compare.dinner)){
			ret = false;
		}
		if(!anSnack.equals(compare.anSnack)){
			ret = false;
		}
		if(supper != compare.supper){
			ret = false;
		}
		if(!snack.equals(compare.snack)){
			ret = false;
		}
		return ret;
	}
}
